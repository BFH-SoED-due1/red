/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;

/**
 * The interface Room.
 *
 *
 */
public interface Room {


    /**
     * Gets name.
     *
     * @return the name of room
     */
    String getName();

    /**
     * Sets name.
     *
     * @param name the name of the room
     */
    void setName(String name);

    /**
     * Gets building.
     *
     * @return the building room is in
     */
    String getBuilding();

    /**
     * Sets building.
     *
     * @param building the building the room is in
     */
    void setBuilding(String building);

    /**
     * Gets size.
     *
     * @return the size of the room, how many person fit
     */
    int getSize();

    /**
     * Sets size.
     *
     * @param size the size of the room, how many person fit
     */
    void setSize(int size);

    /**
     * Gets id.
     *
     * @return the id of room
     */
    int getId();

    /**
     * Sets id.
     *
     * @param id the id of room
     */
    void setId(int id);
}
