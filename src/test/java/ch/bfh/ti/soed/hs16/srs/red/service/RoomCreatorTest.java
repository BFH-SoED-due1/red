/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.service;

import ch.bfh.ti.soed.hs16.srs.red.data.Room;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * The type Room creator test.
 */
public class RoomCreatorTest {

    /**
     * Test create room creates room.
     */
    @Test
    public void testCreateRoomCreatesRoom() {
        String name = "301";
        RoomCreator rc = new RoomCreator();
        Room room = rc.createRoom(name, "hauptgebäude", 10);
        assertEquals(room.getName(), name);
    }

    /**
     * Test create rooms from csv creates rooms.
     *
     * @throws IOException the io exception if file not found or no rights
     */
    @Test
    public void testCreateRoomsFromCSVCreatesRooms() throws IOException {
        String name1 = "301"; //From the excel file needed for this test
        RoomCreator rc = new RoomCreator();
        Set<Room> rooms = rc.createRoomsFromCSV("roomTest.csv");
        assertEquals(rooms.iterator().next().getName(), name1);
    }


}
