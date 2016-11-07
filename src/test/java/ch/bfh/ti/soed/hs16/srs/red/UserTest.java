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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author Martin
 */
public class UserTest{

    @Test
    public void testGetReservationsGetsReservations(){
        User user = new User("Ralph",1,1);
        Room room1 = new Room("406","HG",30);
        Set<Reservation> reservations = new HashSet<>();
        TimeSlot slot = new TimeSlot(new Time(14,30,0),new Time(16,0,0));
        reservations.add(user.makeReservation(room1,slot, new Date(2016,10,20)));
        room1 = new Room("209","Nord",1297);
        slot = new TimeSlot(new Time(14,30,0),new Time(16,0,0));
        reservations.add(user.makeReservation(room1,slot, new Date(2016,10,20)));
        assertEquals( user.getReservations() , reservations);
    }

    @Test
    public void testCanceldReservationsVanish(){
        User user = new User("Ralph",1,1);
        Room room1 = new Room("406","HG",30);
        Set<Reservation> reservations = new HashSet<>();
        TimeSlot slot = new TimeSlot(new Time(14,30,0),new Time(16,0,0));
        Reservation res = user.makeReservation(room1,slot, new Date(2016,10,20));
        //reservations.add(res);
        user.cancelReservation(res);
        reservations = user.getReservations();
        assertFalse(reservations.contains(res));

    }
    
}
