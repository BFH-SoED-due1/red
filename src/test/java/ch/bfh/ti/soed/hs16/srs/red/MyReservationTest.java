/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.soed.hs16.srs.red;


import ch.bfh.ti.soed.hs16.srs.red.data.Reservation;
import ch.bfh.ti.soed.hs16.srs.red.data.Room;
import ch.bfh.ti.soed.hs16.srs.red.data.TimeSlot;
import ch.bfh.ti.soed.hs16.srs.red.jpa.MyRoom;
import ch.bfh.ti.soed.hs16.srs.red.jpa.MyUser;
import ch.bfh.ti.soed.hs16.srs.red.service.ReservationController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * @author Martin
 */
public class MyReservationTest {


    /**
     * Test of cancelReservation method, of class Reservation.
     */

    @Test
    public void testMakeReservation() {
        MyUser user = new MyUser("user", 1, 1);
        MyRoom room = new MyRoom("room", "building", 12);
        user.makeReservation(room, new TimeSlot(new Date(1000*1000*60), new Date(2000*1000*60)));
        assertFalse(user.getReservations().isEmpty());
    }

    @Test
    public void testCancelReservation() {
        MyUser user = new MyUser("user", 1, 1);
        MyRoom room = new MyRoom("room", "building", 12);
        Reservation reservation = user.makeReservation(room, new TimeSlot(new Date(1000*1000*60), new Date(2000*1000*60)));
        user.cancelReservation(reservation);
        assertTrue(user.getReservations().isEmpty());
    }

    @Test
    public void testReservationKnowsOwner() {
        MyUser user = new MyUser("user", 1, 1);
        MyRoom room = new MyRoom("room", "building", 12);
        Reservation reservation = user.makeReservation(room, new TimeSlot(new Date(1000*1000*60), new Date(2000*1000*60)));
        assertEquals(reservation.getOwner(), user);
    }


    @Test
    public void testReservationConflict() {
        MyUser user = new MyUser("user", 1, 1);
        MyRoom room = new MyRoom("room", "building", 12);
        Reservation reservation1 = user.makeReservation(room, new TimeSlot(new Date(1000*60*60*24*10), new Date(1000*60*60*24*15)));
        Reservation reservation2 = user.makeReservation(room, new TimeSlot(new Date(1000*60*60*24*12), new Date(1000*60*60*24*18)));
        assertNull(reservation2);
    }

    @Test
    public void testReservationRoomList() {
        MyUser user = new MyUser("user", 1, 1);
        MyRoom room1 = new MyRoom("room1", "building", 12);
        MyRoom room2 = new MyRoom("room2", "building", 12);
        MyRoom room3 = new MyRoom("room3", "building", 12);
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