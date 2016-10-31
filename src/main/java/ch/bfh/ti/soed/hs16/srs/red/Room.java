/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Martin
 */
public class Room {
    private String name;
    private String building;
    private int size;
    private Set<Reservation> reservations = new HashSet<>();

    public Room(String name, String building, int size) {
        this.name = name;
        this.building = building;
        this.size = size;
    }

    public void addReservation(Reservation res){
        reservations.add(res);
    }


        public void removeReservation(Reservation res){
        reservations.remove(res);
    }

    Object getName() {
        return this.name;
    }
}
