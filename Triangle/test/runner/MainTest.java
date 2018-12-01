package runner;

import org.junit.jupiter.api.*;
import triangle.Triangle;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final int TR_EQUILATERAL = 1; // равносторонний
    private final int TR_ISOSCELES = 2;   // равнобедренный
    private final int TR_ORDYNARY = 4;    // обычный
    private final int TR_RECTANGULAR = 8; // прямоугольный

    //================ Общие проверки, позволяют выявить, есть ли проблемы в целом===================
    @Tag("main")
    @DisplayName("Объект Triangle создан (не null)")
    @Test
    void isTriangleCreatedTest() {
        Triangle triangle = new Triangle(1.1,2.2,3.3);
        assertNotNull(triangle);
    }


    //================Основные сценарии применения программы. Позитивные тесты====================

    @Tag("mainPositive")
    @DisplayName("Корректный равносторонний треугольник")
    @Test
    void correctEqualiteralTriangleTest(){
        Random random = new Random();
        double side = random.nextDouble() * (1.0 - 10.0) + 10.0;
        Triangle triangle = new Triangle(side, side, side);
        assertAll(
                () -> assertNotNull(triangle),
                () -> assertTrue(triangle.checkTriangle()),
                () -> assertEquals("", triangle.getMessage()),
                () -> assertEquals(TR_EQUILATERAL, triangle.detectTriangle())
        );
    }

    @Tag("mainPositive")
    @DisplayName("Корректный равнобедренный треугольник")
    @Test
    void correctIsoscelesTriangleTest(){
        Triangle triangle = new Triangle(4.3, 4.3, 3.2);
        assertAll(
                () -> assertNotNull(triangle),
                () -> assertTrue(triangle.checkTriangle()),
                () -> assertEquals("", triangle.getMessage()),
                () -> assertEquals(TR_ISOSCELES, triangle.detectTriangle())
        );
    }

    @Tag("mainPositive")
    @DisplayName("Корректный обычный треугольник")
    @Test
    void correctOrdinaryTriangleTest(){
        Triangle triangle = new Triangle(3.7,4.8,5.9);
        assertAll(
                () -> assertNotNull(triangle),
                () -> assertTrue(triangle.checkTriangle()),
                () -> assertEquals("", triangle.getMessage()),
                () -> assertEquals(TR_ORDYNARY, triangle.detectTriangle())
        );
    }

    //По логике приложения коды видов треугольников должны складываться, однако, это может потребовать уточнения.
    @Tag("mainPositive")
    @DisplayName("Корректный прямоугольный обычный треугольник")
    @Test
    void correctRectangularTriangleTest(){
        Random random = new Random();
        double catetA = random.nextDouble() * (1.0 - 10.0) + 10.0;
        double catetB = random.nextDouble() * (1.0 - 10.0) + 11.0;
        double hypotenuse = Math.sqrt((Math.pow(catetA, 2)) + (Math.pow(catetB, 2)));
        Triangle triangle = new Triangle(hypotenuse, catetA, catetB);
        assertAll(
                () -> assertNotNull(triangle),
                () -> assertTrue(triangle.checkTriangle()),
                () -> assertEquals("", triangle.getMessage()),
                () -> assertEquals(TR_ORDYNARY | TR_RECTANGULAR, triangle.detectTriangle())
        );
    }

    //================Основные сценарии применения программы. Негативные тесты====================
    @Tag("mainNegative")
    @DisplayName("Некоректный треугольник с нулевой стороной")
    @Test
    void incorrectTriangleTest(){
        Triangle triangle = new Triangle(0, 1.1, 1.1);
        assertAll(
                () -> assertNotNull(triangle),
                () -> assertFalse(triangle.checkTriangle()),
                () -> assertEquals("a<=0", triangle.getMessage()),
                () -> assertThrows(IllegalArgumentException.class, triangle::getSquare)
        );
    }

    @Tag("mainNegative")
    @DisplayName("Невозможный треугольник")
    @Test
    void impossibleTriangleTest(){
        Triangle triangle = new Triangle(10.5, 1.2, 1.1);
        assertAll(
                () -> assertNotNull(triangle),
                () -> assertFalse(triangle.checkTriangle()),
                () -> assertEquals("b+c<=a", triangle.getMessage()),
                () -> assertThrows(IllegalArgumentException.class, triangle::getSquare)
        );
    }
}