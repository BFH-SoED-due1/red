/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;

/**
 * @author Martin
 */
public interface Room {



    String getName();

    void setName(String name);

    String getBuilding();

    void setBuilding(String building);

    int getSize();

    void setSize(int size);

    int getId();

    void setId(int id);
}