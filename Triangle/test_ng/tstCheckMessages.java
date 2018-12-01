import org.testng.Assert;
import org.testng.annotations.Test;
import triangle.Triangle;

/**
 * Проверки сообщений. Поскольку метод может быть вызван без предварительной проверки с помощью метода checkTriangle(),
 * проверяется его реакция на ввод некоректных данных.
 */

public class tstCheckMessages {
    private Triangle triangle;

    @Test
    void tstMessageA(){
        triangle = new Triangle(0, 2d, 3d); //Произвольные значения double, одно из них некорректно
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), "a<=0");
    }

    @Test
    void tstMessageB(){
        triangle = new Triangle(2d, 0, 3d); //Произвольные значения double, одно из них некорректно
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), "b<=0");
    }

    @Test
    void tstMessageC(){
        triangle = new Triangle(2d, 3d, 0); //Произвольные значения double, одно из них некорректно
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), "c<=0");
    }


    @Test
    void tstMessageAB(){
        triangle = new Triangle(2d, 3d, 5d); //Произвольные значения double, сумма некорректна
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), "a+b<=c");
    }


    @Test
    void tstMessageABC(){
        triangle = new Triangle(2d, 3d, 8d);  //Произвольные значения double, сумма некорректна
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), "a+b<=c");
    }



    @Test
    void tstMessageAC(){
        triangle = new Triangle(2d, 8d, 3d); //Произвольные значения double, сумма некорректна
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), "a+c<=b");
    }


    @Test
    void tstMessageACB(){
        triangle = new Triangle(2d, 5d, 3d); //Произвольные значения double, сумма некорректна
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), "a+c<=b");
    }


    @Test
    void tstMessageBCA(){
        triangle = new Triangle(8d, 2d, 3d); //Произвольные значения double, сумма некорректна
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), "b+c<=a");
    }


    @Test
    void tstMessageBCAb(){
        triangle = new Triangle(5d, 2d, 3d); //Произвольные значения double, сумма некорректна
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), "b+c<=a");
    }
}
