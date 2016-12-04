/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.jpa;

import ch.bfh.ti.soed.hs16.srs.red.data.Reservation;
import ch.bfh.ti.soed.hs16.srs.red.data.Room;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Martin
 */
public class MyRoom implements Room {
    private String name;
    private String building;
    private int size;
    private Set<Reservation> reservations = new HashSet<>();

    public MyRoom(String name, String building, int size) {
        this.name = name;
        this.building = building;
        this.size = size;
    }

    @Override
    public void addReservation(Reservation res) {
        reservations.add(res);
    }


    @Override
    public void removeReservation(Reservation res) {
        reservations.remove(res);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getBuilding() {
        return this.building;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
