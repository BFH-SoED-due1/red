/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.jpa;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * My room test.
 */
public class MyRoomTest {

    /**
     * Test if room  knows name and if it changes, if set.
     */
    @Test
    public void testSetAndGetName() {

        MyRoom room = new MyRoom("room", "building", 12);
        assertEquals(room.getName(), "room");
        room.setName("a12");
        assertEquals(room.getName(), "a12");
    }

    /**
     * Test get and set building.
     */
    @Test
    public void testGetAndSetBuilding() {
        MyRoom room = new MyRoom("room", "building", 12);
        assertEquals(room.getBuilding(), "building");
        room.setBuilding("a12");
        assertEquals(room.getBuilding(), "a12");
    }

    /**
     * Test get and set size.
     */
    @Test
    public void testGetAndSetSize() {
        MyRoom room = new MyRoom("room", "building", 12);
        assertEquals(room.getSize(), 12);
        room.setSize(8);
        assertEquals(room.getSize(), 8);
    }

    /**
     * Test get and set id.
     */
    @Test
    public void testGetAndSetId() {
        MyRoom room = new MyRoom(1, "room", "building", 12);
        assertEquals(room.getId(), 1);
        room.setId(3);
        assertEquals(room.getId(), 3);
    }
}
