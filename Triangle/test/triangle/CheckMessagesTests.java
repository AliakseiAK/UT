package triangle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Проверка сообщений. Поскольку метод может быть вызван отдельно, проверяется правильность данных
 */

class CheckMessagesTests {
    private Triangle triangle;

    @DisplayName("Сообщение a<=0")
    @Test
    void getMessageATest(){
        triangle = new Triangle(0, 2d, 3d); //Произвольные значения double, одно из них некорректно
        assertFalse(triangle.checkTriangle());
        assertEquals("a<=0", triangle.getMessage());
    }

    @DisplayName("Сообщение b<=0")
    @Test
    void getMessageBTest(){
        triangle = new Triangle(2d, 0, 3d); //Произвольные значения double, одно из них некорректно
        assertFalse(triangle.checkTriangle());
        assertEquals("b<=0", triangle.getMessage());
    }

    @DisplayName("Сообщение c<=0")
    @Test
    void getMessageCTest(){
        triangle = new Triangle(2d, 3d, 0); //Произвольные значения double, одно из них некорректно
        assertFalse(triangle.checkTriangle());
        assertEquals("c<=0", triangle.getMessage());
    }


    @DisplayName("Сообщение о некорректном треугольнике a+b=c")
    @Test
    void getMessageABTest(){
        triangle = new Triangle(2d, 3d, 5d); //Произвольные значения double, сумма некорректна
        assertFalse(triangle.checkTriangle());
        assertEquals("a+b<=c", triangle.getMessage());
    }

    @DisplayName("Сообщение о некорректном треугольнике a+b<c")
    @Test
    void getMessageBCTest(){
        triangle = new Triangle(2d, 3d, 8d);  //Произвольные значения double, сумма некорректна
        assertFalse(triangle.checkTriangle());
        assertEquals("a+b<=c", triangle.getMessage());
    }

    @DisplayName("Сообщение о некорректном треугольнике a+c<b")
    @Test
    void getMessageACTest(){
        triangle = new Triangle(2d, 8d, 3d); //Произвольные значения double, сумма некорректна
        assertFalse(triangle.checkTriangle());
        assertEquals("a+c<=b", triangle.getMessage());
    }

    @DisplayName("Сообщение о некоректном треугольнике a+c=b")
    @Test
    void getMessageACBTest(){
        triangle = new Triangle(2d, 5d, 3d); //Произвольные значения double, сумма некорректна
        assertFalse(triangle.checkTriangle());
        assertEquals("a+c<=b", triangle.getMessage());
    }

    @DisplayName("Сообщение о некорректном треугольнике b+c<a")
    @Test
    void getMessageBCATest(){
        triangle = new Triangle(8d, 2d, 3d); //Произвольные значения double, сумма некорректна
        assertFalse(triangle.checkTriangle());
        assertEquals("b+c<=a", triangle.getMessage());
    }

    @DisplayName("Сообщение о некорректном треугольнике b+c=a")
    @Test
    void getMessageBCAbTest(){
        triangle = new Triangle(5d, 2d, 3d); //Произвольные значения double, сумма некорректна
        assertFalse(triangle.checkTriangle());
        assertEquals("b+c<=a", triangle.getMessage());
    }
}

