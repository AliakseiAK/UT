import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import triangle.Triangle;

import java.util.Random;

/**
 * Проверки метода detectTriangle() (тип треугольника)
 */
public class tstDetectTriangle {
    private final int TR_EQUILATERAL = 1; // равносторонний
    private final int TR_ISOSCELES = 2;   // равнобедренный
    private final int TR_ORDYNARY = 4;    // обычный
    private final int TR_RECTANGULAR = 8; // прямоугольный

    //DataProviders----------------------------------------------------------------------

    @DataProvider(name = "trianglesWithNullProvider")
    public Object[][] createTriangleWithNull() {
        double nullValue = 0;
        double correctValueA = 4d; //Произвольные корректные double
        double correctValueB = 6d;
        return new Object[][] {
                { "triangle1", new Triangle(nullValue, correctValueA, correctValueB)},
                { "triangle2", new Triangle(correctValueA, nullValue, correctValueB)},
                { "triangle3", new Triangle(correctValueA, correctValueB, nullValue)},
                { "triangle4", new Triangle(nullValue, nullValue, nullValue)}
        };
    }

    @DataProvider(name = "ordinaryTrianglesProvider")
    public Object[][] createOrdinaryTriangles(){
        Random random = new Random();
        int degree = random.nextInt() * (1 - 89) + 89;
        double sideB = random.nextDouble() * (1d - 10d) + 10d;//Произвольные корректные double
        double sideC = random.nextDouble() * (1d - 10d) + 10d;
        double sideA = Math.sqrt((sideB * sideB) + (sideC * sideC)
                - (2 * sideB * sideC)
                * Math.cos(Math.toRadians(degree)));
        return new Object[][]{
                {"triangle1", new Triangle(sideA, sideB, sideC)},
                {"triangle2", new Triangle(sideA, sideC, sideB)},
                {"triangle3", new Triangle(sideB, sideA, sideC)},
                {"triangle4", new Triangle(sideB, sideC, sideA)},
                {"triangle5", new Triangle(sideC, sideB, sideA)},
                {"triangle6", new Triangle(sideC, sideA, sideB)}
        };
    }

    @DataProvider(name = "isoscelesTrianglesProvider")
    public Object[][] createIsoscelesTriangles(){
        double sideA = 10d;//Произвольные корректные double
        double sideB = 6d;
        return new Object[][]{
                {"triangle1", new Triangle(sideA, sideA, sideB)},
                {"triangle2", new Triangle(sideA, sideB, sideA)},
                {"triangle3", new Triangle(sideB, sideA, sideA)}
        };
    }

    @DataProvider(name = "equilateralTriangleProvider")
    public Object[][] createEquilateralTriangles(){
        Random random = new Random();
        double side = random.nextDouble() * (1d - Double.MAX_VALUE) + Double.MAX_VALUE;
        return new Object[][]{
                {"triangle", new Triangle(side, side, side)}
        };
    }

    @DataProvider(name = "rectangularTrianglesProvider")
    public Object[][] createRectangularTriangles(){
        Random random = new Random();
        double catet = random.nextDouble() * (1.0 - Double.MAX_VALUE) + Double.MAX_VALUE;
        double hypotenuse = Math.sqrt((Math.pow(catet, 2)) + (Math.pow(catet, 2)));
        return new Object[][]{
                {"triangle1", new Triangle(catet, catet, hypotenuse)},
                {"triangle2", new Triangle(catet, hypotenuse, catet)},
                {"triangle3", new Triangle(hypotenuse, catet, catet)}
        };
    }
    //-----------------------------------------------------------------------------------

    @Test(dataProvider = "isoscelesTrianglesProvider")
    void tstDetectIsosceles(String triangleName, Triangle triangle) {
        Assert.assertEquals(triangle.detectTriangle(), TR_ISOSCELES, triangleName);
    }

    //Равносторонний (Equaliteral) треугольник. Поскольку существует отдельный индекс TR_EQUILATERAL, принимаем,
    // что он должен распознаваться именно так, а не как "равносторонний равнобедренный", но это может требовать
    // уточняющего вопроса об ожидаемом поведении программы.
    @Test
    void tstDetectEqualiteral() {
        Random random = new Random();
        double randomDouble = random.nextDouble() * (1d - Double.MAX_VALUE) + Double.MAX_VALUE;
        Triangle triangle = new Triangle(randomDouble, randomDouble, randomDouble);
        Assert.assertEquals(triangle.detectTriangle(), TR_EQUILATERAL);
    }

    @Test(dataProvider = "rectangularTrianglesProvider")
    void tstDetectRectangular(String triangleName, Triangle triangle){
        Assert.assertEquals(triangle.detectTriangle(), TR_RECTANGULAR | TR_ISOSCELES, triangleName);
    }

    //Если требуется распознавание прямоугольного треугольника как TR_RECTANGULAR
    @Test
    void tstDetectRectangular() {
        Random random = new Random();
        double catetA = random.nextDouble() * (1d - Double.MAX_VALUE) + Double.MAX_VALUE;
        double catetB = random.nextDouble() * (1d - Double.MAX_VALUE) + Double.MAX_VALUE;
        double hypotenuse = Math.sqrt((Math.pow(catetA, 2)) + (Math.pow(catetB, 2)));
        Triangle triangle = new Triangle(hypotenuse, catetA, catetB);
        Assert.assertEquals(triangle.detectTriangle(), TR_RECTANGULAR);
    }

    //Если требуется распознавание прямоугольного обычного треугольника
    @Test
    void tstDetectEqualiteralIsosceles() {
        //Произвольные отличающиеся корректные значения для катетов
        double catetA = 8d;
        double catetB = 7d;
        double hypotenuse = Math.sqrt((Math.pow(catetA, 2)) + (Math.pow(catetB, 2)));
        Triangle triangle = new Triangle(catetA, catetB, hypotenuse);
        Assert.assertEquals(triangle.detectTriangle(), TR_RECTANGULAR | TR_ORDYNARY);
    }

    @Test
    void tstDetectOrdinary() {
        //Для сторон используются корректные, отличающиеся друг от друга double
        double sideA = 8d;
        double sideB = 5.12;
        double sideC = 6.71;
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.detectTriangle(), TR_ORDYNARY);
    }

    @Test(dataProvider = "equilateralTriangleProvider")
    void tstDetectEquilateralTriangle(Triangle triangle){
        //Равносторонний треугольник является равнобедренным
        Assert.assertEquals(triangle.detectTriangle(), TR_EQUILATERAL | TR_ISOSCELES);
    }

    @Test(dataProvider = "isoscelesTrianglesProvider")
    void tstDetectIsoscelesTriangle(Triangle triangle) {
        Assert.assertEquals(triangle.detectTriangle(), TR_ISOSCELES);
    }

    @Test(dataProvider = "ordinaryTrianglesProvider")
    void tstDetectOrdinaryTriangle(Triangle triangle){
        Assert.assertEquals(triangle.detectTriangle(), TR_ORDYNARY);
    }
}
