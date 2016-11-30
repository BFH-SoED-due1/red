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


/**
 * @author Martin
 */
public class MyReservation implements Reservation {
    private User owner;
    private Room room;
    private TimeSlot timeslot;


    public MyReservation(User user, Room room, TimeSlot timeslot) {
        this.owner = user;
        this.room = room;
        this.timeslot = timeslot;
        room.addReservation(this);
    }

    @Override
    public void cancelReservation() {
        room.removeReservation(this);
    }

    @Override
    public User getOwner() {
        return owner;
    }
    @Override
    public Room getRoom() {
        return room;
    }


    @Override
    public TimeSlot getTimeSlot() {
        return timeslot;
    }




}
