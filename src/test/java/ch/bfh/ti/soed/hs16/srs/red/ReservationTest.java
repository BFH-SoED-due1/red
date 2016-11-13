/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.soed.hs16.srs.red;


import org.junit.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;


/**
 * @author Martin
 */
public class ReservationTest {


    /**
     * Test of cancelReservation method, of class Reservation.
     */

    @Test
    public void testMakeReservation() {
        User user = new User("user", 1, 1);
        Room room = new Room("room", "building", 12);
        user.makeReservation(room, new TimeSlot(new Date(1000*1000*60), new Date(2000*1000*60)));
        assertFalse(user.getReservations().isEmpty());
    }

    @Test
    public void testCancelReservation() {
        User user = new User("user", 1, 1);
        Room room = new Room("room", "building", 12);
        Reservation reservation = user.makeReservation(room, new TimeSlot(new Date(1000*1000*60), new Date(2000*1000*60)));
        user.cancelReservation(reservation);
        assertTrue(user.getReservations().isEmpty());
    }

    @Test
    public void testReservationKnowsOwner() {
        User user = new User("user", 1, 1);
        Room room = new Room("room", "building", 12);
        Reservation reservation = user.makeReservation(room, new TimeSlot(new Date(1000*1000*60), new Date(2000*1000*60)));
        assertEquals(reservation.getOwner(), user);
    }
    
    
    @Test
    public void testReservationConflict() {
        User user = new User("user", 1, 1);
        Room room = new Room("room", "building", 12);
        Reservation reservation1 = user.makeReservation(room, new TimeSlot(new Date(1000*60*60*24*10), new Date(1000*60*60*24*15)));
        Reservation reservation2 = user.makeReservation(room, new TimeSlot(new Date(1000*60*60*24*12), new Date(1000*60*60*24*18)));
        assertNull(reservation2);
    }
    
    @Test
    public void testReservationRoomList() {
        User user = new User("user", 1, 1);
        Room room1 = new Room("room1", "building", 12);
        Room room2 = new Room("room2", "building", 12);
        Room room3 = new Room("room3", "building", 12);
        List<Room> roomList = new ArrayList<Room>();
        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
        Reservation reservation1 = user.makeReservation(room1, new TimeSlot(new Date(1000*60*60*24), new Date(1000*60*60*24*3)));
        Reservation reservation2 = user.makeReservation(room2, new TimeSlot(new Date(1000*60*60*24), new Date(1000*60*60*24*3)));
        Reservation reservation3 = user.makeReservation(room3, new TimeSlot(new Date(1000*60*60*24*3), new Date(1000*60*60*24*5)));
        List<Room> openList = ReservationController.getOpenRooms(new TimeSlot(new Date(1000*60*60*24*2), new Date(1000*60*60*24*3)), roomList);
        assertTrue(openList.size()==1&&openList.contains(room3));
    }


}