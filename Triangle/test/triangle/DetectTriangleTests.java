package triangle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Проверки метода detectTriangle() (тип треугольника)
 */

public class DetectTriangleTests {
    private final int TR_EQUILATERAL = 1; // равносторонний
    private final int TR_ISOSCELES = 2;   // равнобедренный
    private final int TR_ORDYNARY = 4;    // обычный
    private final int TR_RECTANGULAR = 8; // прямоугольный

    //DataProviders----------------------------------------------------------------------
    //Да, это реализуется не так, как в TestNG, но тоже работает и не лишено определенного изящества.
    private static Stream<Triangle> ordinaryTrianglesProvider() {
        Random random = new Random();
        int degree = random.nextInt() * (1 - 89) + 89;
        double sideB = random.nextDouble() * (1d - 10d) + 10d;
        double sideC = random.nextDouble() * (1d - 10d) + 10d;
        double sideA = Math.sqrt((sideB * sideB) + (sideC * sideC)
                - (2 * sideB * sideC)
                * Math.cos(Math.toRadians(degree)));
        Triangle triangle1 = new Triangle(sideA, sideB, sideC);
        Triangle triangle2 = new Triangle(sideA, sideC, sideB);
        Triangle triangle3 = new Triangle(sideB, sideA, sideC);
        Triangle triangle4 = new Triangle(sideB, sideC, sideA);
        Triangle triangle5 = new Triangle(sideC, sideB, sideA);
        Triangle triangle6 = new Triangle(sideC, sideA, sideB);
        return Stream.of(triangle1, triangle2, triangle3, triangle4, triangle5, triangle6);
    }

    private static Stream<Triangle> isoscelesTrianglesProvider() {
        //Гарантированно корректные double
        double sideA = 10d;
        double sideB = 6d;
        Triangle triangle1 = new Triangle(sideA, sideA, sideB);
        Triangle triangle2 = new Triangle(sideA, sideB, sideA);
        Triangle triangle3 = new Triangle(sideB, sideA, sideA);
        return Stream.of(triangle1, triangle2, triangle3);
    }

    private static Stream<Triangle> equilateralTriangleProvider() {
        Random random = new Random();
        double side = random.nextDouble() * (1d - Double.MAX_VALUE) + Double.MAX_VALUE;
        Triangle triangle = new Triangle(side, side, side);
        return Stream.of(triangle);
    }

    private static Stream<Triangle> rectangularTrianglesProvider() {
        Random random = new Random();
        double catet = random.nextDouble() * (1.0 - Double.MAX_VALUE) + Double.MAX_VALUE;
        double hypotenuse = Math.sqrt((Math.pow(catet, 2)) + (Math.pow(catet, 2)));
        Triangle triangle1 = new Triangle(catet, catet, hypotenuse);
        Triangle triangle2 = new Triangle(catet, hypotenuse, catet);
        Triangle triangle3 = new Triangle(hypotenuse, catet, catet);
        return Stream.of(triangle1, triangle2, triangle3);
    }
    //------------------------------------------------------------------------------------

    @Tag("Positive")
    @DisplayName("Определение равнобедренного треугольника")
    @Test
    void createIsoscelesTest() {
        //Произвольные корректные double
        double sideA = 5.1;
        double sideB = 3.4;
        Triangle triangle1 = new Triangle(sideA, sideA, sideB);
        Triangle triangle2 = new Triangle(sideA, sideB, sideA);
        Triangle triangle3 = new Triangle(sideA, sideB, sideB);
        //Поскольку проверки отличаются только позициями значений, можно объединить их в одну, тем более, что
        //assertAll сообщит, в какой конкретно проверке произошел сбой.
        assertAll(() -> assertEquals(TR_ISOSCELES, triangle1.detectTriangle()),
                () -> assertEquals(TR_ISOSCELES, triangle2.detectTriangle()),
                () -> assertEquals(TR_ISOSCELES, triangle3.detectTriangle()));
    }

    //Равносторонний (Equaliteral) треугольник. Поскольку существует отдельный индекс TR_EQUILATERAL, принимаем,
    // что он должен распознаваться именно так, а не как "равносторонний равнобедренный", но это может требовать
    // уточняющего вопроса об ожидаемом поведении программы.
    @Tag("Positive")
    @DisplayName("Определение равностороннего треугольника")
    @Test
    void createEqualiteralTest() {
        Random random = new Random();
        double randomDouble = random.nextDouble() * (1d - Double.MAX_VALUE) + Double.MAX_VALUE;
        Triangle triangle = new Triangle(randomDouble, randomDouble, randomDouble);
        assertEquals(TR_EQUILATERAL, triangle.detectTriangle());
    }

    //Если требуется распознавание прямоугольного треугольника как TR_RECTANGULAR
    @Tag("Positive")
    @DisplayName("Определение прямоугольного треугольника")
    @Test
    void createRectangularTest() {
        Random random = new Random();
        double catetA = random.nextDouble() * (1d - Double.MAX_VALUE) + Double.MAX_VALUE;
        double catetB = random.nextDouble() * (1d - Double.MAX_VALUE) + Double.MAX_VALUE;
        double hypotenuse = Math.sqrt((Math.pow(catetA, 2)) + (Math.pow(catetB, 2)));
        Triangle triangle = new Triangle(hypotenuse, catetA, catetB);
        assertEquals(TR_RECTANGULAR, triangle.detectTriangle());
    }

    @Tag("Positive")
    @DisplayName("Определение прямоугольного равнобедренного треугольника")
    @ParameterizedTest
    @MethodSource("rectangularTrianglesProvider")
    void detectTriangleTestSP2(Triangle triangle){
        assertEquals(TR_RECTANGULAR | TR_ISOSCELES, triangle.detectTriangle());
    }

    //Если требуется распознавание прямоугольного обычного треугольника
    @Tag("Positive")
    @DisplayName("Определение прямоугольного обычного треугольника")
    @Test
    void createEqualiteralIsoscelesTest() {
        //Произвольные отличающиеся корректные значения для катетов
        double catetA = 8d;
        double catetB = 7d;
        double hypotenuse = Math.sqrt((Math.pow(catetA, 2)) + (Math.pow(catetB, 2)));
        Triangle triangle = new Triangle(catetA, catetB, hypotenuse);
        assertEquals(TR_RECTANGULAR | TR_ORDYNARY, triangle.detectTriangle());
    }

    @Tag("Positive")
    @DisplayName("Определение обычного треугольника")
    @Test
    void createOrdinaryTest() {
        //Для сторон используются корректные, отличающиеся друг от друга double
        double sideA = 8d;
        double sideB = 5.12;
        double sideC = 6.71;
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        assertEquals(TR_ORDYNARY, triangle.detectTriangle());
    }

    @Tag("Positive")
    @DisplayName("Определение равностороннего треугольника")
    @ParameterizedTest
    @MethodSource("equilateralTriangleProvider")
    void detectEquilateralTriangleTest(Triangle triangle){
        //Равносторонний треугольник является равнобедренным
        assertEquals(TR_EQUILATERAL | TR_ISOSCELES, triangle.detectTriangle());
    }

    @Tag("Positive")
    @DisplayName("Определение равнобедренного треугольника")
    @ParameterizedTest
    @MethodSource("isoscelesTrianglesProvider")
    void detectTriangleTestSP4(Triangle triangle){
        assertEquals(TR_ISOSCELES, triangle.detectTriangle());
    }

    @Tag("Positive")
    @DisplayName("Определение обычного треугольника")
    @ParameterizedTest
    @MethodSource("ordinaryTrianglesProvider")
    void detectOrdinaryTriangleTest(Triangle triangle){
        assertEquals(TR_ORDYNARY, triangle.detectTriangle());
    }
}