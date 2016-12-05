/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.soed.hs16.srs.red;


import ch.bfh.ti.soed.hs16.srs.red.data.Reservation;
import ch.bfh.ti.soed.hs16.srs.red.data.Room;
import ch.bfh.ti.soed.hs16.srs.red.data.TimeSlot;
import ch.bfh.ti.soed.hs16.srs.red.data.User;
import ch.bfh.ti.soed.hs16.srs.red.jpa.MyRoom;
import ch.bfh.ti.soed.hs16.srs.red.jpa.MyUser;
import ch.bfh.ti.soed.hs16.srs.red.service.ReservationController;
import ch.bfh.ti.soed.hs16.srs.red.service.RoomController;
import ch.bfh.ti.soed.hs16.srs.red.service.UserController;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


/**
 * @author Martin
 */
public class MyReservationTest {


    /**
     * Test of cancelReservation method, of class Reservation.
     */

    @Test
    public void testMakeReservation() {
        UserController userController = new UserController(null);
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("user", 1, 1);
        User user = userController.findUser(1);
        roomController.addRoom(new MyRoom(1, "room", "building", 12));
        Room room = roomController.findRoom(1);
        reservationController.createReservation(new TimeSlot(new Date(1000*1000*60), new Date(2000*1000*60)), room, user);
        assertFalse(reservationController.getAllReservations().isEmpty());
    }

    @Test
    public void testCancelReservation() {
        UserController userController = new UserController(null);
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("user", 1, 1);
        User user = userController.findUser(1);
        roomController.addRoom(new MyRoom(1, "room", "building", 12));
        Room room = roomController.findRoom(1);
        reservationController.createReservation(1, new TimeSlot(new Date(1000*1000*60), new Date(2000*1000*60)), room, user);
        Reservation res = reservationController.findReservation(1);
        reservationController.cancelReservation(res);
        assertTrue(reservationController.getAllReservations().isEmpty());
    }

    @Test
    public void testReservationKnowsOwner() {
        UserController userController = new UserController(null);
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("user", 1, 1);
        User user = userController.findUser(1);
        roomController.addRoom(new MyRoom(1, "room", "building", 12));
        Room room = roomController.findRoom(1);
        reservationController.createReservation(1, new TimeSlot(new Date(1000*1000*60), new Date(2000*1000*60)), room, user);
        Reservation res = reservationController.findReservation(1);
        assertEquals(res.getOwner(), user);
    }


    @Test
    public void testReservationConflict() {
        UserController userController = new UserController(null);
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("user", 1, 1);
        User user = userController.findUser(1);
        roomController.addRoom(new MyRoom(1, "room", "building", 12));
        Room room = roomController.findRoom(1);
        reservationController.createReservation(1, new TimeSlot(new Date(1000*60*60*24*10), new Date(1000*60*60*24*15)), room, user);
        Reservation reservation1 = reservationController.findReservation(1);
        reservationController.createReservation(2, new TimeSlot(new Date(1000*60*60*24*12), new Date(1000*60*60*24*18)), room, user);
        Reservation reservation2 = reservationController.findReservation(2);
        assertNull(reservation2);
    }

    @Test
    public void testReservationRoomList() {
        UserController userController = new UserController(null);
        ReservationController reservationController = new ReservationController();
        RoomController roomController = new RoomController();
        reservationController.clearAllReservations();
        userController.clearAllUsers();
        roomController.clearAllRooms();
        userController.makeUser("user", 1, 1);
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
        reservationController.createReservation(1, new TimeSlot(new Date(1000*60*60*24), new Date(1000*60*60*24*3)), room1, user);
        Reservation reservation1 = reservationController.findReservation(1);
        reservationController.createReservation(2, new TimeSlot(new Date(1000*60*60*24), new Date(1000*60*60*24*3)), room2, user);
        Reservation reservation2 = reservationController.findReservation(2);
        reservationController.createReservation(3, new TimeSlot(new Date(1000*60*60*24*3), new Date(1000*60*60*24*5)), room3, user);
        Reservation reservation3 = reservationController.findReservation(3);
        List<Room> openList = ReservationController.getOpenRooms(new TimeSlot(new Date(1000*60*60*24*2), new Date(1000*60*60*24*3)));
        assertTrue(openList.size()==1&&openList.contains(room3));
    }


}