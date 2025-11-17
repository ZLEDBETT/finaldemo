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

/**
 * <p>A simple Person class for testing purposes.</p>
 * 
 * <p>This class represents a person with a name, age, and smoker status.
 * The age must be between 0 and 150 (inclusive).</p>
 * 
 * @since 3.2
 * @version $Id$
 */
public class Person {
    
    /**
     * Maximum valid age (inclusive).
     */
    public static final int MAX_AGE = 150;
    
    /**
     * Minimum valid age (inclusive).
     */
    public static final int MIN_AGE = 0;
    
    /**
     * Name of the person.
     */
    public String name;
    
    /**
     * Age of the person (0-150).
     */
    private int age;
    
    /**
     * Whether the person is a smoker.
     */
    public boolean smoker;
    
    /**
     * Default constructor initializing age to 0.
     */
    public Person() {
        this.age = 0;
        this.name = "";
        this.smoker = false;
    }
    
    /**
     * Constructor with name, age, and smoker status.
     * 
     * @param name the person's name
     * @param age the person's age (must be 0-150)
     * @param smoker whether the person is a smoker
     * @throws IllegalArgumentException if age is out of valid range
     */
    public Person(final String name, final int age, final boolean smoker) {
        updateAge(age);
        this.name = name;
        this.smoker = smoker;
    }
    
    /**
     * Get the current age of the person.
     * 
     * @return the age
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Update the age of the person.
     * 
     * <p>Age must be between 0 and 150 (inclusive). An exception is thrown
     * if the value is outside this range.</p>
     * 
     * @param age the new age to set
     * @throws IllegalArgumentException if age is less than 0 or greater than 150
     */
    public void updateAge(final int age) {
        if (age < MIN_AGE || age > MAX_AGE) {
            throw new IllegalArgumentException(
                String.format("Age must be between %d and %d, but got %d", MIN_AGE, MAX_AGE, age)
            );
        }
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", smoker=" + smoker +
                '}';
    }
}
