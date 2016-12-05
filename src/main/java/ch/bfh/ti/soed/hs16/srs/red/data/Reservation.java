/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;


/**
 * @author Martin
 */
public interface Reservation {


    int getId();
    void setId(int id);

    User getOwner();
    void setOwner(User owner);

    Room getRoom();
    void setRoom(Room room);


    TimeSlot getTimeSlot();
    void setTimeSlot(TimeSlot timeSlot);
}
