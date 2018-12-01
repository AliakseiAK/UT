package triangle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Проверки метода checkTriangle() (треугольника на существование)
 */
public class CheckTriangleTests {
    private Triangle triangle;

    @Tag("Positive")
    @DisplayName("Равносторонний треугольник, положительные числа")
    @Test
    void checkEqualiteralTriangleTest(){
        double side = 10d; //Корректное произвольное значение
        triangle = new Triangle(side, side, side);
        assertTrue(triangle.checkTriangle());
    }

    @Tag("Positive")
    @DisplayName("Прямоугольный треугольник, положительные числа")
    @Test
    void checkTriangleTestSP11(){
        double catetA = 10.0; //Корректные отличающиеся произвольные значения
        double catetB = 11.0;
        double hypotenuse = Math.sqrt((Math.pow(catetA, 2)) + (Math.pow(catetB, 2)));
        triangle = new Triangle(catetA, catetB, hypotenuse);
        assertTrue(triangle.checkTriangle());
    }



    @Tag("Positive")
    @DisplayName("Равнобедренные треугольники, положительные числа")
    @Test
    void checkTriangleTestSP2(){
        //Равнобедренные треугольники с разными произвольными вариантами расположения равных сторон,
        double sideA = 4.3; //Произвольные корректные double
        double sideB = 3.1;
        Triangle triangle1 = new Triangle(sideA, sideA, sideB);
        Triangle triangle2 = new Triangle(sideB, sideA, sideA);
        Triangle triangle3 = new Triangle(sideA, sideB, sideA);
        //Поскольку проверки отличаются только позициями значений, можно их объединить. При возникновении сбоя assertAll
        //показывает, в какой именно проверке он произошел.
        assertAll(() -> assertTrue(triangle1.checkTriangle()),
                () -> assertTrue(triangle2.checkTriangle()),
                () -> assertTrue(triangle3.checkTriangle()));
    }


    //---Варианты "Обычных" треугольников, групповой и отдельные тесты---//
    @Tag("Positive")
    @DisplayName("Обычный треугольник, положительные числа, разный порядок")
    @Test
    void checkTriangleTestSPG1(){
        //Произвольные корректные отличающиеся double
        double valueA = 3.2;
        double valueB = 4.0;
        double valueC = 5.1;
        Triangle triangle1 = new Triangle(valueA, valueB, valueC);
        Triangle triangle2 = new Triangle(valueA, valueC, valueB);
        Triangle triangle3 = new Triangle(valueB, valueA, valueC);
        Triangle triangle4 = new Triangle(valueB, valueC, valueB);
        Triangle triangle5 = new Triangle(valueC, valueA, valueB);
        Triangle triangle6 = new Triangle(valueC, valueB, valueA);
        //Поскольку проверки отличаются только позициями значений, можно их объединить. При возникновении сбоя assertAll
        //показывает, в какой именно проверке он произошел.
        assertAll(() -> assertTrue(triangle1.checkTriangle()),
                () -> assertTrue(triangle2.checkTriangle()),
                () -> assertTrue(triangle3.checkTriangle()),
                () -> assertTrue(triangle4.checkTriangle()),
                () -> assertTrue(triangle5.checkTriangle()),
                () -> assertTrue(triangle6.checkTriangle()));
    }

    //----------Проверки с некорректными дянными-------//
    @Tag("Negative")
    @DisplayName("Равнобедренный прямоугольный треугольник с выходом за пределы double")
    @Test
    void checkTriangleInfiniteExceptionTest(){
        double catet = Double.MAX_VALUE;
        double hypotenuse = Math.sqrt((Math.pow(catet, 2)) + (Math.pow(catet, 2)));
        Triangle triangle1 = new Triangle(catet, catet, hypotenuse);
        Triangle triangle2 = new Triangle(catet, hypotenuse, catet);
        Triangle triangle3 = new Triangle(hypotenuse, catet, catet);
        //Поскольку проверки отличаются только позициями значений, можно их объединить. При возникновении сбоя assertAll
        //показывает, в какой именно проверке он произошел. Возможно, решение объединить эти проверки в одном тесте спорное,
        // но они близки и связаны.

        //Выход за пределы double должен обрабатываться исключением
        assertAll(()-> assertThrows(InvalidParameterException.class, triangle1::checkTriangle),
                ()-> assertThrows(InvalidParameterException.class, triangle2::checkTriangle),
                ()-> assertThrows(InvalidParameterException.class, triangle3::checkTriangle));
        //В то же время, формально такие треугольники корректны
        assertAll(() -> assertTrue(triangle1.checkTriangle()),
                () -> assertTrue(triangle2.checkTriangle()),
                () -> assertTrue(triangle3.checkTriangle()));
    }

    @Tag("Negative")
    @DisplayName("Равнобедренный прямоугольный треугольник с выходом за пределы double")
    @Test
    void checkTriangleInfiniteTest(){
        double catet = Double.MAX_VALUE;
        double hypotenuse = Math.sqrt((Math.pow(catet, 2)) + (Math.pow(catet, 2)));
        Triangle triangle1 = new Triangle(catet, catet, hypotenuse);
        Triangle triangle2 = new Triangle(catet, hypotenuse, catet);
        Triangle triangle3 = new Triangle(hypotenuse, catet, catet);
        //Поскольку проверки отличаются только позициями значений, можно их объединить. При возникновении сбоя assertAll
        //показывает, в какой именно проверке он произошел.

        // Здесь происходит выход за пределы double. В то же время, формально такие треугольники корректны
        assertAll(() -> assertTrue(triangle1.checkTriangle()),
                () -> assertTrue(triangle2.checkTriangle()),
                () -> assertTrue(triangle3.checkTriangle()));
    }

    @Tag("Negative")
    @DisplayName("Ноль в значениях")
    @Test
    void checkTriangleTestSN1(){
        double nullValue = 0;
        double correctValueA = 4d;
        double correctValueB = 6d;
        Triangle triangle1 = new Triangle(nullValue, correctValueA, correctValueB);
        Triangle triangle2 = new Triangle(correctValueA, nullValue, correctValueB);
        Triangle triangle3 = new Triangle(correctValueA, correctValueB, nullValue);
        Triangle triangle4 = new Triangle(nullValue, nullValue, nullValue);
        //Поскольку проверки отличаются только позициями нулевого значения, можно их объединить.
        // При возникновении сбоя assertAll показывает, в какой именно проверке он произошел.
        assertAll(() -> assertFalse(triangle1.checkTriangle()),
                () -> assertFalse(triangle2.checkTriangle()),
                () -> assertFalse(triangle3.checkTriangle()),
                () -> assertFalse(triangle4.checkTriangle()));
    }

    @Tag("Negative")
    @DisplayName("Отрицательные значения")
    @Test
    void checkTriangleTestSN2(){
        //Произвольные отрицательные значения
        double sideA = -1.2;
        double sideB = -2.0;
        double sideC = 5.0;
        triangle = new Triangle(sideA, sideB, sideC);
        assertFalse(triangle.checkTriangle());
    }
}
