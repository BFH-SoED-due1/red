/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Martin
 */
public class User {
    private String name;
    private int id;
    private int role;
    private Set<Reservation> reservations = new HashSet<>();

    public User(String name, int id, int role) {
        this.name = name;
        this.id = id;
        this.role = role;
    }
    public String getName(){
        return name;
    }

    public Reservation makeReservation(Room room, TimeSlot timeslot) {
        Reservation res = ReservationController.createReservation(timeslot, room, this);
        if(res != null){
            reservations.add(res);
            return res;
        }
        return null;

    }

    public void cancelReservation(Reservation res) {
        ReservationController.cancelReservation(res);
        this.reservations.remove(res);
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }
}
