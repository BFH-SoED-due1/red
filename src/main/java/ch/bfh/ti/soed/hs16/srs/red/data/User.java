/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;


/**
 * @author Martin
 */
public interface User {
    
    public String getName();
    public Reservation makeReservation(Room room, TimeSlot timeslot);
    public void cancelReservation(Reservation res);
}
