/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Timeslottest
 */
public class TimeslotTest {

    /**
     * test the setters
     */
    @Test
    public void testSetter() {
        TimeSlot slot = new TimeSlot(new Date(9999), new Date(999));
        Date date1 = new Date(1234);
        Date date2 = new Date(5678);
        slot.setEnd(date1);
        slot.setStart(date2);
        assertEquals(slot.getEnd(), date1);
        assertEquals(slot.getStart(), date2);
    }
}
