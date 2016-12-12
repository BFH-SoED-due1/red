/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.jpa;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class MyRoomTest {

    @Test
    public void testReservationKnowsOwner() {

        MyRoom room = new MyRoom("room", "building", 12);
        assertEquals(room.getName(), "room");
    }

}
