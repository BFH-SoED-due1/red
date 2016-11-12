/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.soed.hs16.srs.red;


import org.junit.Test;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Martin
 */
public class UserTest {

    @Test
    public void testMakeFeservationsAddsReservationToReservations(){
        User user = new User("Ralph", 1, 1);
        Room room1 = new Room("406", "HG", 30);
        Set<Reservation> reservations;
        TimeSlot slot = new TimeSlot(new Time(9364), new Time(14588));
        Reservation res = user.makeReservation(room1, slot, new Date());
        assertTrue(user.getReservations().contains(res));
    }
    @Test
    public void testGetReservationsGetsReservations() {
        User user = new User("Ralph", 1, 1);
        Room room1 = new Room("406", "HG", 30);
        Set<Reservation> reservations = new HashSet<>();
        TimeSlot slot = new TimeSlot(new Time(9364), new Time(14588));
        reservations.add(user.makeReservation(room1, slot, new Date()));
        room1 = new Room("209", "Nord", 1297);
        slot = new TimeSlot(new Time(75863), new Time(75832));
        reservations.add(user.makeReservation(room1, slot, new Date(12561996)));
        assertEquals(user.getReservations(), reservations);

    }

    @Test
    public void testCanceledReservationsVanish() {
        User user = new User("Ralph", 1, 1);
        Room room1 = new Room("406", "HG", 30);
        Set<Reservation> reservations;
        TimeSlot slot = new TimeSlot(new Time(9364), new Time(14588));
        Reservation res = user.makeReservation(room1, slot, new Date());
        user.cancelReservation(res);
        reservations = user.getReservations();
        assertFalse(reservations.contains(res));

    }

}
