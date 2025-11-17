package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppBoundaryTestMCP {

    @Test
    void test_add_boundaries() {
        App obj = new App();
        // Lower bound - testing with minimum integer values
        assertDoesNotThrow(() -> obj.add(Integer.MIN_VALUE, 0));
        assertDoesNotThrow(() -> obj.add(0, Integer.MIN_VALUE));
        
        // Nominal - testing with typical values
        assertDoesNotThrow(() -> obj.add(0, 0));
        assertEquals(5, obj.add(2, 3));
        
        // Upper bound - testing with maximum integer values
        assertDoesNotThrow(() -> obj.add(Integer.MAX_VALUE, 0));
        assertDoesNotThrow(() -> obj.add(0, Integer.MAX_VALUE));
    }

    @Test
    void test_multiply_boundaries() {
        App obj = new App();
        // Lower bound - testing with minimum integer values
        assertDoesNotThrow(() -> obj.multiply(Integer.MIN_VALUE, 1));
        assertDoesNotThrow(() -> obj.multiply(1, Integer.MIN_VALUE));
        
        // Nominal - testing with typical values
        assertDoesNotThrow(() -> obj.multiply(0, 0));
        assertEquals(6, obj.multiply(2, 3));
        
        // Upper bound - testing with maximum integer values
        assertDoesNotThrow(() -> obj.multiply(Integer.MAX_VALUE, 1));
        assertDoesNotThrow(() -> obj.multiply(1, Integer.MAX_VALUE));
    }
}