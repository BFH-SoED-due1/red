/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red;

import java.util.Date;


/**
 * @author Martin
 */
public class Reservation {
    private User owner;
    private Room room;
    private TimeSlot timeslot;


    public Reservation(User user, Room room, TimeSlot timeslot) {
        this.owner = user;
        this.room = room;
        this.timeslot = timeslot;
        room.addReservation(this);
    }

    public void cancelReservation() {
        room.removeReservation(this);
    }

    public User getOwner() {
        return owner;
    }
    public Room getRoom() {
        return room;
    }
    
    
    public TimeSlot getTimeSlot() {
        return timeslot;
    }
    
    


}
