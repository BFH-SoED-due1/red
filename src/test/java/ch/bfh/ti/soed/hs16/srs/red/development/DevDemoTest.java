/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.development;

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

    @Test
    public void testDemoCreatesBuildingsAndUsers() throws Exception {
        UserController uc = new UserController();
        uc.clearAllUsers();
        DevDemo dd = new DevDemo();
        //Password pw = Password.getInstance();
        //MyUser u = new MyUser("user1",2,0,pw.getSaltedHash("pass1"));
        assertFalse(uc.getAllUser().isEmpty());
    }

}
