/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red;

import java.io.IOException;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Martin
 */
public class RoomCreatorTest {

    @Test
    public void testCreateRoomCreatesRoom() {
        String name = "301";
        RoomCreator rc = new RoomCreator();
        Room room = rc.createRoom(name, "hauptgebäude", 10);
        assertEquals(room.getName(),name);
    }

    @Test
    public void testCreateRoomsFromCSVCreatesRooms() throws IOException {
        String name1 = "303"; //From the excel file needed for this test

        RoomCreator rc = new RoomCreator();
        Set<Room> rooms = rc.createRoomsFromCSV("roomTest.csv");


        assertEquals(rooms.iterator().next().getName(),name1);
    }

   
}
