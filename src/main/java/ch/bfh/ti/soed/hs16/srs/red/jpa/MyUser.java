/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.jpa;

import ch.bfh.ti.soed.hs16.srs.red.data.User;

import javax.persistence.*;
import java.util.List;


/**
 *  My user class, imlementaition of user.
 *
 *
 */
@Entity
@Table
public class MyUser implements User {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private int id;

    private String name;
    private int role;

    @OneToMany(mappedBy="owner",cascade = CascadeType.ALL)
    private List<MyReservation> reservations;

    /**
     * Instantiates a new My user.
     *
     * @param name the name of user
     * @param id   the id of user
     * @param role the role of user, eg admin etc
     */
    public MyUser(String name, int id, int role) {
        this.name = name;
        this.id = id;
        this.role = role;
    }

    /**
     * Instantiates a new My user.
     *
     * @param name the name of user
     * @param role the role of user
     */
    public MyUser(String name,int role) {
        this.name = name;
        this.role = role;
    }

    /**
     * Instantiates a new My user.
     */
    public MyUser() {

    }

    public String getName() {
        return name;
    }



    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public int getRole() {
       return this.role;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }


    @Override
    public void setRole(int role) {
        this.role = role;
    }
}
