/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.development;

import ch.bfh.ti.soed.hs16.srs.red.service.RoomCreator;

/**
 *
 * @author Martin
 */
public class DevDemo {

    public DevDemo(){ //Fills the database with some generic entries for testing and demo purposes
        RoomCreator rc = new RoomCreator();
        rc.createRoom("001", "Main", 30);
        rc.createRoom("002", "Main", 20);
        rc.createRoom("003", "Main", 15);
        rc.createRoom("004", "Main", 30);
        rc.createRoom("101", "Main", 35);
        rc.createRoom("102", "Main", 20);
        rc.createRoom("Aula", "Main", 100);
        rc.createRoom("205", "Main", 25);
        rc.createRoom("N3", "North", 33);
        rc.createRoom("N2", "North", 10);
        rc.createRoom("N1", "North", 5);

    }

}
