/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;


/**
 * The interface User.
 */
public interface User {

    /**
     * Gets name.
     *
     * @return the name
     */
    String getName();

    /**
     * Gets id.
     *
     * @return the id
     */
    int getID();

    /**
     * Gets role.
     *
     * @return the role
     */
    int getRole();

    /**
     * Sets name.
     *
     * @param name the name to be set
     */
    void setName(String name);


    /**
     * Sets role.
     *
     * @param role the role to be assigned
     */
    void setRole(int role);


    /**
     * Gets passHash.
     *
     * @return the passHash
     */

    String getPassHash();
}
