/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.development;

import ch.bfh.ti.soed.hs16.srs.red.service.RoomCreator;
import ch.bfh.ti.soed.hs16.srs.red.service.UserController;

/**
 *
 * @author Martin
 */
public class DevDemo {

    /**
     * create various database entries for demo purposes.
     * @throws java.lang.Exception
     */

    public DevDemo() throws Exception { //Fills the database with some generic entries for testing and demo purposes

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
        UserController uc = new UserController();
        uc.makeUser("admin", 1, 1, "admin");
        uc.makeUser("user1", 2, 0, "pass1");
        uc.makeUser("user2", 3, 0, "pass2");
        uc.makeUser("user3", 4, 0, "pass3");
        uc.makeUser("user4", 5, 0, "pass4");

    }

}
