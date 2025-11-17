package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testAdd() {
        App app = new App();
        assertEquals(5, app.add(2, 3));
    }

    @Test
    void testMultiply() {
        App app = new App();
        assertEquals(6, app.multiply(2, 3));
    }

    @Test
    void testAddWithNegatives() {
        App app = new App();
        assertEquals(-1, app.add(2, -3));
        assertEquals(0, app.add(0, 0));
    }

    @Test
    void testMultiplyEdgeCases() {
        App app = new App();
        assertEquals(0, app.multiply(0, 12345));
        assertEquals(-6, app.multiply(-2, 3));
    }
}
