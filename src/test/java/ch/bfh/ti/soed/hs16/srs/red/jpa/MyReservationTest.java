/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.jpa;


import ch.bfh.ti.soed.hs16.srs.red.data.Reservation;
import ch.bfh.ti.soed.hs16.srs.red.data.Room;
import ch.bfh.ti.soed.hs16.srs.red.data.TimeSlot;
import ch.bfh.ti.soed.hs16.srs.red.data.User;
import ch.bfh.ti.soed.hs16.srs.red.service.ReservationController;
import ch.bfh.ti.soed.hs16.srs.red.service.RoomController;
import ch.bfh.ti.soed.hs16.srs.red.service.UserController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * MyReservation test.
 */
public class MyReservationTest {


    /**
     * Test of makeReservation
     */
    @Test
    public void testMakeReservation() throws Exception {
        UserController userController = new UserController();
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("user", 1, 1, "pw");
        User user = userController.findUser(1);
        roomController.addRoom(new MyRoom(1, "room", "building", 12));
        Room room = roomController.findRoom(1);
        reservationController.createReservation(new TimeSlot(new Date(1000 * 1000 * 60), new Date(2000 * 1000 * 60)), room, user);
        assertFalse(reservationController.getAllReservations().isEmpty());
        reservationController.clearAllReservations();
        reservationController.createReservation(1, new TimeSlot(new Date(1000 * 1000 * 60), new Date(2000 * 1000 * 60)), room, user);
        assertFalse(reservationController.getAllReservations().isEmpty());
    }

    /**
     * Test cancel reservation.
     */
    @Test
    public void testCancelReservation() throws Exception {
        UserController userController = new UserController();
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        userController.makeUser("user", 7, 1,"pw");
        User user = userController.findUser(7);
        roomController.addRoom(new MyRoom(11, "room", "building", 12));
        Room room = roomController.findRoom(11);
        reservationController.createReservation(8, new TimeSlot(new Date(1000 * 1000 * 6), new Date(2000 * 1000 * 20)), room, user);
        Reservation res = reservationController.findReservation(8);
        reservationController.cancelReservation(res);
        assertTrue(!reservationController.getAllReservations().contains(res));
        assertTrue(reservationController.findReservation(res.getId()) == null);
    }

    /**
     * Test if reservation knows owner, and notices if changed through set.
     */
    @Test
    public void testReservationKnowsOwner() throws Exception {
        UserController userController = new UserController();
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("user", 1, 1, "pw");
        User user = userController.findUser(1);
        roomController.addRoom(new MyRoom(1, "room", "building", 12));
        Room room = roomController.findRoom(1);
        reservationController.createReservation(1, new TimeSlot(new Date(1000 * 1000 * 60), new Date(2000 * 1000 * 60)), room, user);
        Reservation res = reservationController.findReservation(1);
        assertEquals(res.getOwner(), user);
        userController.makeUser("john", 2, 1, "pw");
        User john = userController.findUser(2);
        reservationController.findReservation(1).setOwner(john);
        res = reservationController.findReservation(1);
        assertEquals(res.getOwner(), john);

    }


    /**
     * Test conflicting reservations
     */
    @Test
    public void testReservationConflict() throws Exception {
        UserController userController = new UserController();
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("user", 1, 1, "pw");
        User user = userController.findUser(1);
        roomController.addRoom(new MyRoom(1, "room", "building", 12));
        Room room = roomController.findRoom(1);
        reservationController.createReservation(1, new TimeSlot(new Date(1000 * 60 * 60 * 24 * 10), new Date(1000 * 60 * 60 * 24 * 15)), room, user);
        Reservation reservation1 = reservationController.findReservation(1);
        reservationController.createReservation(2, new TimeSlot(new Date(1000 * 60 * 60 * 24 * 12), new Date(1000 * 60 * 60 * 24 * 18)), room, user);
        Reservation reservation2 = reservationController.findReservation(2);
        assertNull(reservation2);
    }

    /**
     * Test adding multiple rooms that are equal
     */
    @Test
    public void testReservationRoomList() throws Exception {
        UserController userController = new UserController();
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("user", 1, 1, "pw");
        User user = userController.findUser(1);
        roomController.addRoom(new MyRoom(1, "room1", "building", 12));
        Room room1 = roomController.findRoom(1);
        roomController.addRoom(new MyRoom(2, "room2", "building", 12));
        Room room2 = roomController.findRoom(2);
        roomController.addRoom(new MyRoom(3, "room3", "building", 12));
        Room room3 = roomController.findRoom(3);
        List<Room> roomList = new ArrayList<Room>();
        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
        reservationController.createReservation(1, new TimeSlot(new Date(1000 * 60 * 60 * 24), new Date(1000 * 60 * 60 * 24 * 3)), room1, user);
        Reservation reservation1 = reservationController.findReservation(1);
        reservationController.createReservation(2, new TimeSlot(new Date(1000 * 60 * 60 * 24), new Date(1000 * 60 * 60 * 24 * 3)), room2, user);
        Reservation reservation2 = reservationController.findReservation(2);
        reservationController.createReservation(3, new TimeSlot(new Date(1000 * 60 * 60 * 24 * 3), new Date(1000 * 60 * 60 * 24 * 5)), room3, user);
        Reservation reservation3 = reservationController.findReservation(3);
        List<Room> openList = ReservationController.getOpenRooms(new TimeSlot(new Date(1000 * 60 * 60 * 24 * 2), new Date(1000 * 60 * 60 * 24 * 3)));
        assertTrue(openList.size() == 1 && openList.contains(room3));
    }

    /**
     * test setters
     */
    @Test
    public void testSetters() throws Exception {
        UserController userController = new UserController();
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("user", 1, 1, "pw");
        User user = userController.findUser(1);
        roomController.addRoom(new MyRoom(1, "room", "building", 12));
        Room room = roomController.findRoom(1);
        reservationController.createReservation(1, new TimeSlot(new Date(1000 * 60 * 60 * 24 * 10), new Date(1000 * 60 * 60 * 24 * 15)), room, user);
        Reservation reservation1 = reservationController.findReservation(1);
        roomController.addRoom(new MyRoom(2, "a12", "maze", 12));
        room = roomController.findRoom(2);
        reservation1.setRoom(room);
        TimeSlot timeSlot = new TimeSlot(new Date(1000 * 60 * 60 * 24 * 2), new Date(1000 * 60 * 60 * 24 * 3));
        reservation1.setTimeSlot(timeSlot);
        assertEquals(reservation1.getRoom(), room);
        assertEquals(reservation1.getTimeSlot(), timeSlot);
    }

}