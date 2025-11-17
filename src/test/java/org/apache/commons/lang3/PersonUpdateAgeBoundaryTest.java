/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Boundary tests for the {@link Person#updateAge(int)} method.
 * 
 * <p>This test class focuses on boundary value analysis to ensure that
 * the updateAge method correctly handles edge cases and validates input constraints.</p>
 * 
 * @since 3.2
 * @version $Id$
 */
public class PersonUpdateAgeBoundaryTest {
    
    /**
     * Test the lower boundary: age = 0 (valid minimum).
     * This is the lowest valid age value.
     */
    @Test
    public void testUpdateAgeLowerBoundaryValid() {
        final Person person = new Person();
        person.updateAge(0);
        assertEquals("Age 0 should be valid", 0, person.getAge());
    }
    
    /**
     * Test below the lower boundary: age = -1 (invalid).
     * This is one below the minimum valid value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAgeBelowLowerBoundary() {
        final Person person = new Person();
        person.updateAge(-1);
    }
    
    /**
     * Test significantly below the lower boundary: age = -100 (invalid).
     * This tests a large negative value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAgeSignificantlyBelowLowerBoundary() {
        final Person person = new Person();
        person.updateAge(-100);
    }
    
    /**
     * Test the upper boundary: age = 150 (valid maximum).
     * This is the highest valid age value.
     */
    @Test
    public void testUpdateAgeUpperBoundaryValid() {
        final Person person = new Person();
        person.updateAge(150);
        assertEquals("Age 150 should be valid", 150, person.getAge());
    }
    
    /**
     * Test above the upper boundary: age = 151 (invalid).
     * This is one above the maximum valid value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAgeAboveUpperBoundary() {
        final Person person = new Person();
        person.updateAge(151);
    }
    
    /**
     * Test significantly above the upper boundary: age = 200 (invalid).
     * This tests a large value beyond the reasonable human lifespan.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAgeSignificantlyAboveUpperBoundary() {
        final Person person = new Person();
        person.updateAge(200);
    }
    
    /**
     * Test Integer.MAX_VALUE (invalid).
     * This tests the extreme upper boundary of int values.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAgeIntegerMaxValue() {
        final Person person = new Person();
        person.updateAge(Integer.MAX_VALUE);
    }
    
    /**
     * Test Integer.MIN_VALUE (invalid).
     * This tests the extreme lower boundary of int values.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAgeIntegerMinValue() {
        final Person person = new Person();
        person.updateAge(Integer.MIN_VALUE);
    }
    
    /**
     * Test a typical valid age in the middle of the range: age = 30.
     * This tests a normal, non-boundary value.
     */
    @Test
    public void testUpdateAgeMiddleRange() {
        final Person person = new Person();
        person.updateAge(30);
        assertEquals("Age 30 should be valid", 30, person.getAge());
    }
    
    /**
     * Test a typical valid age: age = 65 (senior boundary).
     */
    @Test
    public void testUpdateAgeSeniorAge() {
        final Person person = new Person();
        person.updateAge(65);
        assertEquals("Age 65 should be valid", 65, person.getAge());
    }
    
    /**
     * Test updating age multiple times.
     * Ensures that age can be updated to different valid values sequentially.
     */
    @Test
    public void testUpdateAgeMultipleTimes() {
        final Person person = new Person();
        person.updateAge(0);
        assertEquals("Initial age should be 0", 0, person.getAge());
        
        person.updateAge(25);
        assertEquals("Age should update to 25", 25, person.getAge());
        
        person.updateAge(150);
        assertEquals("Age should update to 150", 150, person.getAge());
        
        person.updateAge(1);
        assertEquals("Age should update to 1", 1, person.getAge());
    }
    
    /**
     * Test that an invalid update does not modify the current age.
     * After a failed update, the age should remain unchanged.
     */
    @Test
    public void testUpdateAgeInvalidDoesNotChangeState() {
        final Person person = new Person();
        person.updateAge(30);
        assertEquals("Initial age should be 30", 30, person.getAge());
        
        try {
            person.updateAge(151);
            fail("Expected IllegalArgumentException for age 151");
        } catch (final IllegalArgumentException e) {
            assertEquals("Age should remain 30 after failed update", 30, person.getAge());
        }
    }
    
    /**
     * Test boundary age = 1 (just above minimum).
     * This is the second-lowest valid value.
     */
    @Test
    public void testUpdateAgeJustAboveMinimum() {
        final Person person = new Person();
        person.updateAge(1);
        assertEquals("Age 1 should be valid", 1, person.getAge());
    }
    
    /**
     * Test boundary age = 149 (just below maximum).
     * This is the second-highest valid value.
     */
    @Test
    public void testUpdateAgeJustBelowMaximum() {
        final Person person = new Person();
        person.updateAge(149);
        assertEquals("Age 149 should be valid", 149, person.getAge());
    }
    
    /**
     * Test constructor with valid boundary age.
     */
    @Test
    public void testConstructorWithBoundaryAge() {
        final Person person1 = new Person("Alice", 0, false);
        assertEquals("Constructor with age 0 should work", 0, person1.getAge());
        
        final Person person2 = new Person("Bob", 150, true);
        assertEquals("Constructor with age 150 should work", 150, person2.getAge());
    }
    
    /**
     * Test constructor with invalid age (below minimum).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidAgeBelowMinimum() {
        new Person("Charlie", -1, false);
    }
    
    /**
     * Test constructor with invalid age (above maximum).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidAgeAboveMaximum() {
        new Person("Diana", 151, false);
    }
}
