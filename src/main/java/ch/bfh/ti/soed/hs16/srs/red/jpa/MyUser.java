/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.jpa;

import ch.bfh.ti.soed.hs16.srs.red.data.User;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
    private String passHash;

    @OneToMany(mappedBy="owner",cascade = CascadeType.ALL)
    private List<MyReservation> reservations;

    /**
     * Instantiates a new My user.
     *
     * @param name the name of user
     * @param id   the id of user
     * @param role the role of user, eg admin etc
     * @param passwordHash the combined salt and hashed password
     */
    public MyUser(String name, int id, int role, String passwordHash) {
        this.name = name;
        this.id = id;
        this.role = role;
        this.passHash = passwordHash;
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

    @Override
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

    @Override
    public String getPassHash(){
        return this.passHash;
    }

}
