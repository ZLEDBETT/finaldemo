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
 * <p>An enum representing all the versions of the Java specification.
 * This is intended to mirror available values from the
 * <em>java.specification.version</em> System property. </p>
 *
 * @since 3.0
 * @version $Id: $
 */
public enum JavaVersion {
    
    /**
     * The Java version reported by Android. This is not an official Java version number.
     */
    JAVA_0_9(1.5f, "0.9"),
    
    /**
     * Java 1.1.
     */
    JAVA_1_1(1.1f, "1.1"),

    /**
     * Java 1.2.
     */
    JAVA_1_2(1.2f, "1.2"),

    /**
     * Java 1.3.
     */
    JAVA_1_3(1.3f, "1.3"),

    /**
     * Java 1.4.
     */
    JAVA_1_4(1.4f, "1.4"),

    /**
     * Java 1.5.
     */
    JAVA_1_5(1.5f, "1.5"),

    /**
     * Java 1.6.
     */
    JAVA_1_6(1.6f, "1.6"),

    /**
     * Java 1.7.
     */
    JAVA_1_7(1.7f, "1.7"),

    /**
     * Java 1.8.
     */
    JAVA_1_8(1.8f, "1.8"),

    /**
     * Java 9.
     */
    JAVA_9(9.0f, "9"),

    /**
     * Java 10.
     */
    JAVA_10(10.0f, "10"),

    /**
     * Java 11.
     */
    JAVA_11(11.0f, "11"),

    /**
     * Java 12.
     */
    JAVA_12(12.0f, "12"),

    /**
     * Java 13.
     */
    JAVA_13(13.0f, "13"),

    /**
     * Java 14.
     */
    JAVA_14(14.0f, "14"),

    /**
     * Java 15.
     */
    JAVA_15(15.0f, "15"),

    /**
     * Java 16.
     */
    JAVA_16(16.0f, "16"),

    /**
     * Java 17.
     */
    JAVA_17(17.0f, "17"),

    /**
     * Java 18.
     */
    JAVA_18(18.0f, "18"),

    /**
     * Java 19.
     */
    JAVA_19(19.0f, "19"),

    /**
     * Java 20.
     */
    JAVA_20(20.0f, "20"),

    /**
     * Java 21.
     */
    JAVA_21(21.0f, "21");

    /**
     * The float value.
     */
    private float value;
    /**
     * The standard name.
     */
    private String name;

    /**
     * Constructor.
     *
     * @param value  the float value
     * @param name  the standard name, not null
     */
    JavaVersion(final float value, final String name) {
        this.value = value;
        this.name = name;
    }

    //-----------------------------------------------------------------------
    /**
     * <p>Whether this version of Java is at least the version of Java passed in.</p>
     *
     * <p>For example:<br />
     *  {@code myVersion.atLeast(JavaVersion.JAVA_1_4)}<p>
     *
     * @param requiredVersion  the version to check against, not null
     * @return true if this version is equal to or greater than the specified version
     */
    public boolean atLeast(final JavaVersion requiredVersion) {
        return this.value >= requiredVersion.value;
    }

    /**
     * Transforms the given string with a Java version number to the
     * corresponding constant of this enumeration class. This method is used
     * internally.
     *
     * @param nom the Java version as string
     * @return the corresponding enumeration constant or <b>null</b> if the
     * version is unknown
     */
    // helper for static importing
    static JavaVersion getJavaVersion(final String nom) {
        return get(nom);
    }

    /**
     * Transforms the given string with a Java version number to the
     * corresponding constant of this enumeration class. This method is used
     * internally.
     *
     * @param nom the Java version as string
     * @return the corresponding enumeration constant or <b>null</b> if the
     * version is unknown
     */
    static JavaVersion get(final String nom) {
        if ("0.9".equals(nom)) {
            return JAVA_0_9;
        } else if ("1.1".equals(nom)) {
            return JAVA_1_1;
        } else if ("1.2".equals(nom)) {
            return JAVA_1_2;
        } else if ("1.3".equals(nom)) {
            return JAVA_1_3;
        } else if ("1.4".equals(nom)) {
            return JAVA_1_4;
        } else if ("1.5".equals(nom)) {
            return JAVA_1_5;
        } else if ("1.6".equals(nom)) {
            return JAVA_1_6;
        } else if ("1.7".equals(nom)) {
            return JAVA_1_7;
        } else if ("1.8".equals(nom)) {
            return JAVA_1_8;
        } else if ("9".equals(nom)) {
            return JAVA_9;
        } else if ("10".equals(nom)) {
            return JAVA_10;
        } else if ("11".equals(nom)) {
            return JAVA_11;
        } else if ("12".equals(nom)) {
            return JAVA_12;
        } else if ("13".equals(nom)) {
            return JAVA_13;
        } else if ("14".equals(nom)) {
            return JAVA_14;
        } else if ("15".equals(nom)) {
            return JAVA_15;
        } else if ("16".equals(nom)) {
            return JAVA_16;
        } else if ("17".equals(nom)) {
            return JAVA_17;
        } else if ("18".equals(nom)) {
            return JAVA_18;
        } else if ("19".equals(nom)) {
            return JAVA_19;
        } else if ("20".equals(nom)) {
            return JAVA_20;
        } else if ("21".equals(nom)) {
            return JAVA_21;
        } else {
            // For unknown versions, do a conservative parse:
            // - If the version starts with "1." then it is an unknown legacy 1.x (e.g. "1.9") and should
            //   be treated as unknown (return null). Known legacy 1.x versions were handled above by exact
            //   string matches ("1.1".."1.8").
            // - Otherwise try to parse as a numeric major-version (9, 10, 11, ...)
            if (nom != null && nom.startsWith("1.")) {
                return null;
            }
            try {
                float version = Float.parseFloat(nom);
                if (version < 9.0f) {
                    // Any numeric value under 9 that wasn't an exact legacy match is unknown
                    return null;
                } else if (version < 10.0f) {
                    return JAVA_9;
                } else if (version < 11.0f) {
                    return JAVA_10;
                } else if (version < 12.0f) {
                    return JAVA_11;
                } else if (version < 13.0f) {
                    return JAVA_12;
                } else if (version < 14.0f) {
                    return JAVA_13;
                } else if (version < 15.0f) {
                    return JAVA_14;
                } else if (version < 16.0f) {
                    return JAVA_15;
                } else if (version < 17.0f) {
                    return JAVA_16;
                } else if (version < 18.0f) {
                    return JAVA_17;
                } else if (version < 19.0f) {
                    return JAVA_18;
                } else if (version < 20.0f) {
                    return JAVA_19;
                } else if (version < 21.0f) {
                    return JAVA_20;
                } else {
                    return JAVA_21;
                }
            } catch (final NumberFormatException e) {
                return null;
            }
        }
    }

    //-----------------------------------------------------------------------
    /**
     * <p>The string value is overridden to return the standard name.</p>
     *
     * <p>For example, <code>"1.5"</code>.</p>
     *
     * @return the name, not null
     */
    @Override
    public String toString() {
        return name;
    }

}
