import org.testng.Assert;
import org.testng.annotations.Test;
import triangle.Triangle;

import java.util.Random;

/**
 * Проверки метода getSquare() (площадь треугольника)
 */
public class tstGetSquare {
    private Triangle triangle;

    @Test
    void tstGetSquareByGeron() {
        double side = 6.3; //Произвольное корректное значение double для равностороннего треугольника
        double halfPerimeter = (side * 3) / 2;

        triangle = new Triangle(side, side, side);
        double square = Math.sqrt(halfPerimeter * (halfPerimeter - side) * (halfPerimeter - side) * (halfPerimeter - side));
        Assert.assertEquals(triangle.getSquare(), square);
    }

    //В программе применен метод Герона. Метод катетов для прямоугольных треугодьников дает другую точность.
    @Test
    void tstGetSquareByCatets() {
        Random random = new Random();
        double catet = random.nextDouble() * (1.0 - Double.MAX_VALUE) + Double.MAX_VALUE;
        double hypotenuse = Math.sqrt((Math.pow(catet, 2)) + (Math.pow(catet, 2)));
        double square = (catet * catet) / 2;
        triangle = new Triangle(catet, catet, hypotenuse);
        Assert.assertEquals(triangle.getSquare(), square);
    }


    //---Проверки вывода исключений при вводе некорректных данных---//

    @Test(expectedExceptions = IllegalArgumentException.class)
    void tstWrongTriangleSquare(){
        //Произвольные значения сторон для невозможного треугольника.
        double sideA = 10.5;
        double sideB = 1.2;
        double sideC = 1.1;
        triangle = new Triangle(sideA, sideB, sideC);
        triangle.getSquare();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void tstWrongTriangleSquareWithNull(){
        double correctSideA = 11.5;
        double incorrectSideB = 0;//Произвольные значения сторон и одно нулевое.
        double correctSideC = 10.1;
        triangle = new Triangle(correctSideA, incorrectSideB, correctSideC);
        triangle.getSquare();
    }
}
