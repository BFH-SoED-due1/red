/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.jpa;

import ch.bfh.ti.soed.hs16.srs.red.data.Room;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The My room class, implements room.
 *
 * @author Martin
 */
@Entity
public class MyRoom implements Room {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private int id;

    private String name;
    private String building;
    private int size;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MyReservation> reservations;

    /**
     * Instantiates a new My room.
     *
     * @param name     the name of room
     * @param building the building room is in
     * @param size     the size of room(how many people fit inside)
     */
    public MyRoom(String name, String building, int size) {
        this.name = name;
        this.building = building;
        this.size = size;
    }

    /**
     * Instantiates a new My room.
     *
     * @param id       the id of room (for DB)
     * @param name     the name of room
     * @param building the building room is in
     * @param size     the size of room(how many people fit inside)
     */
    public MyRoom(int id, String name, String building, int size) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.size = size;
    }

    /**
     * Instantiates a new My room.
     */
    public MyRoom() {
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getBuilding() {
        return this.building;
    }

    @Override
    public void setBuilding(String building) {
        this.building = building;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
