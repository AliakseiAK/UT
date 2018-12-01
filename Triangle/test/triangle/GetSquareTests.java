package triangle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class GetSquareTests {
    private Triangle triangle;

    @DisplayName("Площадь треугольника методом Герона")
    @Test
    void getSquareByGeronTest() {
        double side = 6.3; //Произвольное корректное значение double для равностороннего треугольника
        double halfPerimeter = (side * 3) / 2;

        triangle = new Triangle(side, side, side);
        double square = Math.sqrt(halfPerimeter * (halfPerimeter - side) * (halfPerimeter - side) * (halfPerimeter - side));
        assertEquals(square, triangle.getSquare());
    }

    //В программе применен метод Герона. Метод катетов для прямоугольных треугодьников дает другую точность.
    @DisplayName("Площадь прямоугольного треугольника методом катетов")
    @Test
    void getSquareByCatetsTest() {
        Random random = new Random();
        double catet = random.nextDouble() * (1.0 - Double.MAX_VALUE) + Double.MAX_VALUE;
        double hypotenuse = Math.sqrt((Math.pow(catet, 2)) + (Math.pow(catet, 2)));
        double square = (catet * catet) / 2;
        triangle = new Triangle(catet, catet, hypotenuse);
        assertEquals(square, triangle.getSquare());
    }


    //---Проверки вывода исключений при вводе некорректных данных---//
    @DisplayName("Исключение для площади невозможного треугольника")
    @Test
    void wrongTriangleSquareTest(){
        //Произвольные значения сторон для невозможного треугольника.
        double sideA = 10.5;
        double sideB = 1.2;
        double sideC = 1.1;
        triangle = new Triangle(sideA, sideB, sideC);
        assertThrows(IllegalArgumentException.class, triangle::getSquare);
    }

    @DisplayName("Исключение для площади треугольника с нулевой стороной")
    @Test
    void wrongTriangleSquareWithNullTest(){
        double correctSideA = 11.5;
        double incorrectSideB = 0;
        double correctSideC = 10.1;
        triangle = new Triangle(correctSideA, incorrectSideB, correctSideC);
        assertThrows(IllegalArgumentException.class, triangle::getSquare);
    }
}