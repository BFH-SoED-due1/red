/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;

/**
 * @author Martin
 */
public interface Room {

    public void addReservation(Reservation res);
    public void removeReservation(Reservation res);
    public String getName();
}