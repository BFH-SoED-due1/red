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

    public void cancelReservation();
    public User getOwner();
    public Room getRoom();
    public TimeSlot getTimeSlot();
}
