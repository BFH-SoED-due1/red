/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.service;

import ch.bfh.ti.soed.hs16.srs.red.data.Room;
import ch.bfh.ti.soed.hs16.srs.red.jpa.MyRoom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test of RoomController
 */
public class RoomControllerTest {


    List<Room> roomOverviewTest = new ArrayList<>();


    /**
     * Test if correct room has been removed
     */
    @Test
    public void testRemoveRoom() {
        RoomController roomController = new RoomController();
        roomController.clearAllRooms();
        //Creates 10 rooms and add it to DataBase
        for (int i = 1; i <= 10; i++) {
            roomController.addRoom(new MyRoom(i, "room" + i, "building" + i, i));
        }

        roomOverviewTest = roomController.getAllRooms();



        Room r6 = roomController.findRoom(6);
        roomController.removeRoom(r6);

        List<Room> room = roomController.getAllRooms();


        assertEquals(9, room.size());

        assertFalse(room.contains(r6));
    }

    /**
     * Test if nothing happens when room isn't in the list or room == null
     */
    @Test
    public void testRemoveRoomNotExists() {

        RoomController roomController = new RoomController();
        roomController.clearAllRooms();
        //Creates 10 rooms and add it to DataBase
        for (int i = 1; i <= 10; i++) {
            roomController.addRoom(new MyRoom("room" + i, "building" + i, i));
        }

        roomOverviewTest = roomController.getAllRooms();

        MyRoom r6 = new MyRoom("TestRoom", "TestBuilding", 45);


        MyRoom r7 = null;

        roomController.removeRoom(r6);
        roomController.removeRoom(r7);

        List<Room> room = roomController.getAllRooms();


        assertEquals(10, room.size());

    }


    /**
     * Test add new Room to the list
     */
    @Test
    public void testAddRoom() {

        RoomController roomController = new RoomController();
        roomController.clearAllRooms();
        //Creates 10 rooms and add it to DataBase
        for (int i = 1; i <= 10; i++) {
            roomController.addRoom(new MyRoom("room" + i, "building" + i, i));
        }

        roomOverviewTest = roomController.getAllRooms();

        MyRoom r6 = new MyRoom("newRoom", "newBuilding", 34);

        roomController.addRoom(r6);
        List<Room> roomList = roomController.getAllRooms();

        assertEquals(11, roomList.size());
    }

    /**
     * Test add Rooms with same name or null element
     */
//    @Test
//    public void testAddRoomWithSameName() {
//
//        RoomController roomController = new RoomController();
//        roomController.clearAllRooms();
//        //Creates 10 rooms and add it to DataBase
//        for (int i = 1; i <= 10; i++) {
//            roomController.addRoom(new MyRoom("room" + i, "building" + i, i));
//        }
//
//        roomOverviewTest = roomController.getAllRooms();
//
//        MyRoom r6 = new MyRoom("room6", "building6", 6);
//
//        roomController.addRoom(r6);
//        List<Room> roomList = roomController.getAllRooms();
//
//        assertEquals(10, roomList.size());
//
//    }


    /**
     * Test add Rooms to empty list (in DB)
     */
    @Test
    public void testAddRoomWithListNull() {

        RoomController roomController = new RoomController();
        roomController.clearAllRooms();
        MyRoom r6 = new MyRoom("room6", "building6", 6);
        roomController.addRoom(r6);

        assertEquals(1, roomController.getAllRooms().size());

    }

    /**
     * Test add Rooms when room == null
     */
    @Test
    public void testAddRoomWithNullRoom() {

        RoomController roomController = new RoomController();
        roomController.clearAllRooms();
        MyRoom r6 = null;
        roomController.addRoom(r6);

        assertEquals(0, roomController.getAllRooms().size());

    }

}
