/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red;

import org.junit.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by scarface on 10.11.2016.
 */
public class RoomControllerTest {


    List<Room> roomOverviewTest = new ArrayList<>();


    /**
     * Test if correct room has been removed
     */
    @Test
    public void testRemoveRoom() {

        //Creates 10 rooms and add it to List
        for (int i = 1; i <= 10; i++) {
            roomOverviewTest.add(new Room("room" + i, "building" + i, i));
        }

        RoomController roomController = new RoomController();
        roomController.setRoomOverview(roomOverviewTest);

        Room r6 = new Room("room6", "building6", 6);
        roomController.removeRoom(r6);

        List<Room> room = roomController.getRooms();

        // Creates second List without deleted room
        List<Room> roomWithoutRoom6 = new ArrayList<>();
        roomWithoutRoom6.add(new Room("room1", "building1", 1));
        roomWithoutRoom6.add(new Room("room2", "building2", 2));
        roomWithoutRoom6.add(new Room("room3", "building3", 3));
        roomWithoutRoom6.add(new Room("room4", "building4", 4));
        roomWithoutRoom6.add(new Room("room5", "building5", 5));
        roomWithoutRoom6.add(new Room("room7", "building7", 7));
        roomWithoutRoom6.add(new Room("room8", "building8", 8));
        roomWithoutRoom6.add(new Room("room9", "building9", 9));
        roomWithoutRoom6.add(new Room("room10", "building10", 10));

        assertEquals(9, room.size());
        assertEquals(9, roomWithoutRoom6.size());

        for (int i = 0; i < roomController.getRooms().size(); i++) {
            assertEquals(roomController.getRooms().get(i).getName(), roomWithoutRoom6.get(i).getName());
        }
    }

    /**
     * Test if nothing happens when room isn't in the list or room == null
     */
    @Test
    public void testRemoveRoomNotExists() {

        //Creates 10 rooms and add it to List
        for (int i = 1; i <= 10; i++) {
            roomOverviewTest.add(new Room("room" + i, "building" + i, i));
        }

        RoomController roomController = new RoomController();
        roomController.setRoomOverview(roomOverviewTest);

        Room r6 = new Room("TestRoom", "TestBuilding", 45);

        RoomController roomController1 = new RoomController();
        roomController1.setRoomOverview(roomOverviewTest);
        Room r7 = null;

        roomController.removeRoom(r6);
        roomController1.removeRoom(r7);

        List<Room> room = roomController.getRooms();
        List<Room> room1 = roomController1.getRooms();

        assertEquals(10, room.size());
        assertEquals(10, room1.size());
    }


    /**
     * Test add new Room to the list
     */
    @Test
    public void testAddRoom() {

        //Creates 10 rooms and add it to List
        for (int i = 1; i <= 10; i++) {
            roomOverviewTest.add(new Room("room" + i, "building" + i, i));
        }

        RoomController roomController = new RoomController();
        roomController.setRoomOverview(roomOverviewTest);

        Room r6 = new Room("newRoom", "newBuilding", 34);

        roomController.addRoom(r6);
        List<Room> roomList = roomController.getRooms();

        assertEquals(11, roomList.size());
    }

    /**
     * Test add Rooms with same name or null element
     */
    @Test
    public void testAddRoomWithSameName() {

        //Creates 10 rooms and add it to List
        for (int i = 1; i <= 10; i++) {
            roomOverviewTest.add(new Room("room" + i, "building" + i, i));
        }

        RoomController roomController = new RoomController();
        roomController.setRoomOverview(roomOverviewTest);

        Room r6 = new Room("room6", "building6", 6);

        roomController.addRoom(r6);
        List<Room> roomList = roomController.getRooms();

        assertEquals(10, roomList.size());

    }


    /**
     * Test add Rooms when list isn't initialized
     */
    @Test
    public void testAddRoomWithListNull() {

        RoomController roomController = new RoomController();
        Room r6 = new Room("room6", "building6", 6);
        roomController.addRoom(r6);

        assertEquals(1, roomController.getRooms().size());

    }

    /**
     * Test add Rooms when room == null
     */
    @Test
    public void testAddRoomWithNullRoom() {

        RoomController roomController = new RoomController();
        Room r6 = null;
        roomController.addRoom(r6);

        assertEquals(0, roomController.getRooms().size());

    }

}
