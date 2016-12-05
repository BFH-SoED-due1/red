/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.service;

import ch.bfh.ti.soed.hs16.srs.red.data.User;
import ch.bfh.ti.soed.hs16.srs.red.jpa.MyUser;
import ch.bfh.ti.soed.hs16.srs.red.service.UserController;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Martin
 */
public class UserControllerTest {



    @Test
    public void testLoginExistingUser() {
        String username="user1";
        String password="hunter2";
        User u;

         Map<String, String> dbLogin = new HashMap<>();
         dbLogin.put(username, password);
        UserController uc = new UserController(dbLogin);
        try {
            u = uc.logIn(username, password);
            assertEquals(u.getName(), username);
        } catch (Exception ex) {
            Logger.getLogger(UserControllerTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Incorrect Username or Password");
        }


    }

        @Test(expected = Exception.class)
    public void testLoginNonExistingUser() throws Exception {
            String username="user1";
            String password="hunter2";
            User u;

            Map<String, String> dbLogin = new HashMap<>();
            dbLogin.put(username, password);
            UserController uc = new UserController(dbLogin);

            u = uc.logIn("notuser", password);

            System.out.println("Incorrect Username or Password");
        }





@Test(expected = Exception.class)
    public void testLoginWrongPassword() throws Exception {
    String username="user1";
    String password="hunter2";
    User u;

    Map<String, String> dbLogin = new HashMap<>();
    dbLogin.put(username, password);
    UserController uc = new UserController(dbLogin);

            u = uc.logIn(username, "123test");

            System.out.println("Incorrect Username or Password");
        }

        @Test
    public void testMakeUser(){
        UserController userController = new UserController(null);
        userController.clearAllUsers();
        userController.makeUser("Franz",2,3);
        User franz = userController.findUser(2);

        assertTrue(userController.getAllUser().contains(franz));

        }

        @Test
    public void testRemoveUser(){
            UserController userController = new UserController(null);
            userController.clearAllUsers();
            userController.makeUser("Franz",3,3);
            User franz = new MyUser("Franz",3,3);
            userController.removeUser(franz);
            assertTrue(!userController.getAllUser().contains(franz));
        }


    }
