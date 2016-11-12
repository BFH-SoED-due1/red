/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.soed.hs16.srs.red;


import org.junit.Test;

import java.sql.Time;
import java.util.Date;

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
        user.makeReservation(room, new TimeSlot(new Time(14, 30, 0), new Time(16, 0, 0)), new Date(2016, 10, 20));
        assertFalse(user.getReservations().isEmpty());
    }

    @Test
    public void testCancelReservation() {
        User user = new User("user", 1, 1);
        Room room = new Room("room", "building", 12);
        Reservation reservation = user.makeReservation(room, null, null);
        user.cancelReservation(reservation);
        assertTrue(user.getReservations().isEmpty());
    }

    @Test
    public void testReservationKnowsOwner() {
        User user = new User("user", 1, 1);
        Room room = new Room("room", "building", 12);
        Reservation reservation = user.makeReservation(room, new TimeSlot(new Time(14, 30, 0), new Time(16, 0, 0)), new Date(2016, 10, 20));
        assertEquals(reservation.getOwner(), user);
    }


}