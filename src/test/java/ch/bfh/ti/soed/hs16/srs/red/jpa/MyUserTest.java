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
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * The My user test.
 *
 *
 */
public class MyUserTest {

    /**
     * Test make reservations adds reservation to DB.
     */
    @Test
    public void testMakeReservationsAddsReservationToReservations(){
        UserController userController = new UserController(null);
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("Ralph", 1, 1);
        User user = userController.findUser(1);
        roomController.addRoom(new MyRoom(1, "406", "HG", 30));
        Room room = roomController.findRoom(1);
        reservationController.createReservation(1, new TimeSlot(new Date(9364), new Date(14588)), room, user);
        Reservation res = reservationController.findReservation(1);
        assertTrue(reservationController.findReservationsOfUser(user).contains(res));
    }

    /**
     * Test get reservations gets reservations.
     */
    @Test
    public void testGetReservationsGetsReservations() {
        UserController userController = new UserController(null);
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("Ralph", 1, 1);
        User user = userController.findUser(1);
        roomController.addRoom(new MyRoom(1, "406", "HG", 30));
        Room room = roomController.findRoom(1);
        Set<Reservation> reservations = new HashSet<>();
        reservationController.createReservation(1, new TimeSlot(new Date(9364), new Date(14588)), room, user);
        Reservation res1 = reservationController.findReservation(1);
        reservations.add(res1);
        roomController.addRoom(new MyRoom(2, "209", "Nord", 1297));
        Room room2 = roomController.findRoom(2);
        reservationController.createReservation(2, new TimeSlot(new Date(758963), new Date(75882)), room2, user);
        Reservation res2 = reservationController.findReservation(2);
        reservations.add(res2);
        assertTrue(reservationController.findReservationsOfUser(user).containsAll(reservations));

    }

    /**
     * Test if canceled reservations vanish.
     */
    @Test
    public void testCanceledReservationsVanish() {
        UserController userController = new UserController(null);
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("Ralph", 1, 1);
        User user = userController.findUser(1);
        roomController.addRoom(new MyRoom(1, "406", "HG", 30));
        Room room = roomController.findRoom(1);
        List<Reservation> reservations;
        reservationController.createReservation(1, new TimeSlot(new Date(9364), new Date(14588)), room, user);
        Reservation res = reservationController.findReservation(1);
        reservationController.cancelReservation(res);
        reservations = reservationController.findReservationsOfUser(user);
        assertFalse(reservations.contains(res));

    }

    /**
     * test if constructor works
     */

    @Test
    public void testCreateUser(){
        UserController userController = new UserController(null);
        userController.makeUser("john",2);
        assertFalse(userController.getAllUser().isEmpty());
        userController.makeUser("jane",2,1);
        User user = userController.findUser(2);
        assertTrue(userController.getAllUser().contains(user));
    }

    /**
     * test the setters
     */

    @Test
    public void testSetters(){
        MyUser user = new MyUser("Cat",4);
        user.setName("doe");
        user.setRole(2);
        assertEquals(2,user.getRole());
        assertEquals("doe",user.getName());
    }

}
