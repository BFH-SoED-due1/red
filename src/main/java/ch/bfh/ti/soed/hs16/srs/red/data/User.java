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

    String getName();
    int getID();
    int getRole();
    void setName(String name);
    void setID(int id);
    void setRole(int role);

}
