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
package org.apache.commons.lang3.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * Unit tests for truncatedEquals and truncatedCompareTo methods in DateUtils.
 */
public class DateUtilsTruncatedCompareTest {

    @Test
    public void testTruncatedEqualsAndCompareForDates() {
        final Calendar base = new GregorianCalendar(2020, Calendar.MAY, 10, 10, 20, 30);
        base.set(Calendar.MILLISECOND, 500);
        final Date date1 = base.getTime();

        // same minute but different seconds/millis -> truncated to minute should be equal
        final Calendar other = (Calendar) base.clone();
        other.set(Calendar.SECOND, 59);
        other.set(Calendar.MILLISECOND, 900);
        final Date date2 = other.getTime();

        assertTrue("Dates should be truncated-equal at MINUTE", DateUtils.truncatedEquals(date1, date2, Calendar.MINUTE));
        assertEquals("Compare should be 0 when truncated to minute", 0, DateUtils.truncatedCompareTo(date1, date2, Calendar.MINUTE));

        // different hours -> compare truncated to HOUR should reflect order
        final Date earlier = new GregorianCalendar(2020, Calendar.MAY, 10, 9, 59, 59).getTime();
        final Date later = new GregorianCalendar(2020, Calendar.MAY, 10, 11, 0, 0).getTime();
        assertFalse(DateUtils.truncatedEquals(earlier, later, Calendar.HOUR_OF_DAY));
        int cmp = DateUtils.truncatedCompareTo(earlier, later, Calendar.HOUR_OF_DAY);
        assertTrue("earlier should be less than later when truncated to hour", cmp < 0);
    }

    @Test
    public void testTruncatedEqualsAndCompareForCalendars() {
        final Calendar c1 = new GregorianCalendar(2019, Calendar.DECEMBER, 31, 23, 59, 59);
        c1.set(Calendar.MILLISECOND, 1);
        final Calendar c2 = (Calendar) c1.clone();
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);

        // Truncate to DAY: both represent same day
        assertTrue(DateUtils.truncatedEquals(c1, c2, Calendar.DATE));
        assertEquals(0, DateUtils.truncatedCompareTo(c1, c2, Calendar.DATE));

        // Different year
        final Calendar cy1 = new GregorianCalendar(2018, Calendar.JANUARY, 1);
        final Calendar cy2 = new GregorianCalendar(2019, Calendar.JANUARY, 1);
        assertFalse(DateUtils.truncatedEquals(cy1, cy2, Calendar.YEAR));
        int cmp = DateUtils.truncatedCompareTo(cy1, cy2, Calendar.YEAR);
        assertTrue("cy1 should be less than cy2 when truncated to year", cmp < 0);
    }

    @Test
    public void testNullArgumentsThrow() {
        final Date now = new Date();
        final Calendar cal = Calendar.getInstance();
        try {
            DateUtils.truncatedEquals((Date) null, now, Calendar.DATE);
            fail("Expected IllegalArgumentException for null date1");
        } catch (final IllegalArgumentException ex) {
            // expected
        }
        try {
            DateUtils.truncatedEquals(now, (Date) null, Calendar.DATE);
            fail("Expected IllegalArgumentException for null date2");
        } catch (final IllegalArgumentException ex) {
            // expected
        }
        try {
            DateUtils.truncatedCompareTo((Calendar) null, cal, Calendar.DATE);
            fail("Expected IllegalArgumentException for null cal1");
        } catch (final IllegalArgumentException ex) {
            // expected
        }
        try {
            DateUtils.truncatedCompareTo(cal, (Calendar) null, Calendar.DATE);
            fail("Expected IllegalArgumentException for null cal2");
        } catch (final IllegalArgumentException ex) {
            // expected
        }
    }
}
