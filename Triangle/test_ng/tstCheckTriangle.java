import org.testng.Assert;
import org.testng.annotations.*;
import triangle.Triangle;

import java.util.Random;

/**
 * Проверки треугольника на существование - метод checkTriangle().
 */
public class tstCheckTriangle {
    private Triangle triangle;

    //Data providers=======================================================

    @DataProvider(name = "equilateralTriangleProvider")
    public Object[][] createEquilateralTriangle() {
        //Равнобедренные треугольники с разными произвольными вариантами расположения равных сторон,
        double sideA = 4.3; //Произвольные корректные double
        double sideB = 3.1;
        return new Object[][] {
                { "triangle1", new Triangle(sideA, sideA, sideB) },
                { "triangle2", new Triangle(sideB, sideA, sideA)},
                { "triangle3", new Triangle(sideB, sideA, sideA)}
        };
    }

    @DataProvider(name = "ordynaryTrianglesProvider")
    public Object[][] createOrdynaryTriangle() {
        //Произвольные корректные отличающиеся double
        double valueA = 3.2;
        double valueB = 4.0;
        double valueC = 5.1;
        return new Object[][] {
                { "triangle1", new Triangle(valueA, valueB, valueC)},
                { "triangle2", new Triangle(valueA, valueC, valueB)},
                { "triangle3", new Triangle(valueB, valueA, valueC)},
                { "triangle4", new Triangle(valueB, valueC, valueB)},
                { "triangle5", new Triangle(valueC, valueA, valueB)},
                { "triangle6", new Triangle(valueC, valueB, valueA)}
        };
    }

    @DataProvider(name = "infiniteTrianglesProvider")
    public Object[][] createInfiniteTriangle() {
        double catet = Double.MAX_VALUE;
        double hypotenuse = Math.sqrt((Math.pow(catet, 2)) + (Math.pow(catet, 2)));
        return new Object[][] {
                { "triangle1", new Triangle(catet, catet, hypotenuse)},
                { "triangle2", new Triangle(catet, hypotenuse, catet)},
                { "triangle3", new Triangle(hypotenuse, catet, catet)}
        };
    }

    @DataProvider(name = "trianglesWithNullProvider")
    public Object[][] createTriangleWithNull() {
        double nullValue = 0;
        double correctValueA = 4d;
        double correctValueB = 6d;
        return new Object[][] {
                { "triangle1", new Triangle(nullValue, correctValueA, correctValueB)},
                { "triangle2", new Triangle(correctValueA, nullValue, correctValueB)},
                { "triangle3", new Triangle(correctValueA, correctValueB, nullValue)},
                { "triangle4", new Triangle(nullValue, nullValue, nullValue)}
        };
    }
//=========================================================================

    @Test
    void tstEqualiteralTriangle(){
        Random random = new Random();
        double side = random.nextDouble() * (1.0 - 10.0) + 10.0;
        triangle = new Triangle(side, side, side);
        Assert.assertTrue(triangle.checkTriangle());
    }

    @Test
    void tstRectangularTriangle(){
        Random random = new Random();
        double catetA = random.nextDouble() * (1.0 - 10.0) + 10.0;
        double catetB = random.nextDouble() * (1.0 - 10.0) + 11.0;
        double hypotenuse = Math.sqrt((Math.pow(catetA, 2)) + (Math.pow(catetB, 2)));
        triangle = new Triangle(catetA, catetB, hypotenuse);
        Assert.assertTrue(triangle.checkTriangle());
    }

    @Test(dataProvider = "equilateralTriangleProvider")
    void checkEquilateralTriangles(String triangleName, Triangle triangle){
        Assert.assertTrue(triangle.checkTriangle(), triangleName);
    }

    @Test(dataProvider = "ordynaryTrianglesProvider")
    void tstOrdynaryTriangles(String triangleName, Triangle triangle){
        Assert.assertTrue(triangle.checkTriangle(), triangleName);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void tstTriangleInfiniteException(){
        double catet = Double.MAX_VALUE;
        double hypotenuse = Math.sqrt((Math.pow(catet, 2)) + (Math.pow(catet, 2)));
        triangle = new Triangle(catet, catet, hypotenuse);
        triangle.checkTriangle();
    }

    @Test(dataProvider = "infiniteTrianglesProvider")
    void tstTriangleInfinite(String triangleName, Triangle triangle){
        Assert.assertTrue(triangle.checkTriangle(), triangleName);
    }

    @Test(dataProvider = "trianglesWithNullProvider")
    void tstTriangleWithNull(String triangleName, Triangle triangle){
        Assert.assertFalse(triangle.checkTriangle(), triangleName);
    }

    @Test
    void tstCheckNegativeValues(){
        //Произвольные отрицательные значения
        double sideA = -1.2;
        double sideB = -2.0;
        double sideC = 5.0;
        triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertFalse(triangle.checkTriangle());
    }
}

