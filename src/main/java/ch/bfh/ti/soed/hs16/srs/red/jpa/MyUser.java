/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.jpa;

import ch.bfh.ti.soed.hs16.srs.red.data.Reservation;
import ch.bfh.ti.soed.hs16.srs.red.data.User;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;


/**
 * @author Martin
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

    public MyUser(String name, int id, int role) {
        this.name = name;
        this.id = id;
        this.role = role;
    }
    public MyUser(String name,int role) {
        this.name = name;
        this.role = role;
    }
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
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public void setRole(int role) {
        this.role = role;
    }
}
