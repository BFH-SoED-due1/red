/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.development;

import ch.bfh.ti.soed.hs16.srs.red.service.RoomController;
import ch.bfh.ti.soed.hs16.srs.red.service.UserController;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

/**
 *
 * @author Martin
 */
public class DevDemoTest {

    public DevDemoTest() {
    }
     /**
     * Test if the DevDemo creates users.
     */
    @Test
    public void testDemoCreatesUsers() throws Exception {
        UserController uc = new UserController();
        uc.clearAllUsers();
        DevDemo dd = new DevDemo();
        //Password pw = Password.getInstance();
        //MyUser u = new MyUser("user1",2,0,pw.getSaltedHash("pass1"));
        assertFalse(uc.getAllUser().isEmpty());
    }
     /**
     * Test if the DevDemo creates rooms.
     */

        @Test
    public void testDemoCreatesRooms() throws Exception {
        RoomController rc = new RoomController();
        rc.clearAllRooms();
        DevDemo dd = new DevDemo();
        //Password pw = Password.getInstance();
        //MyUser u = new MyUser("user1",2,0,pw.getSaltedHash("pass1"));
        assertFalse(rc.getAllRooms().isEmpty());
    }
}

