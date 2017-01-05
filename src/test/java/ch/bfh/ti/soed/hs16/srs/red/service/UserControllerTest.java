/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.service;

import ch.bfh.ti.soed.hs16.srs.red.data.User;
import ch.bfh.ti.soed.hs16.srs.red.jpa.MyUser;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * The type User controller test.
 *
 * @author Martin
 */
public class UserControllerTest {

    /**
     * Test login with wrong password.
     *
     * @throws Exception the wrong password or username exception
     */
    @Test(expected = Exception.class)
    public void testLoginWrongPassword() throws Exception {
    String username="user1";
    String password="hunter2";
    User u;

    Map<String, String> dbLogin = new HashMap<>();
    dbLogin.put(username, password);
    UserController uc = new UserController(dbLogin);

            u = uc.logIn(username, "123test");

        }

    /**
     * Test make user.
     */
    @Test
    public void testMakeUser(){
        UserController userController = new UserController(null);
        userController.clearAllUsers();
        userController.makeUser("Franz",2,3);
        User franz = userController.findUser(2);

        assertTrue(userController.getAllUser().contains(franz));

        }

    /**
     * Test remove user.
     */
    @Test
    public void testRemoveUser(){
            UserController userController = new UserController(null);
            userController.clearAllUsers();
            userController.makeUser("Franz",3,3);
            User franz = new MyUser("Franz",3,3);
            userController.removeUser(franz);
            assertTrue(!userController.getAllUser().contains(franz));
        }

    /**
     * Tests first if clearAllUsers really clears all and then if getAllUsers returns the newly added user(s)
     */
    @Test
    public void testClearAllUsersAndGetAll(){
        UserController userController = new UserController(null);
        userController.clearAllUsers();
        assertTrue(userController.getAllUser().isEmpty());
        List<User> list = userController.getAllUser();
        userController.makeUser("Franz",3,3);
        list.add(userController.findUser(3));
        list.forEach(user -> assertTrue(userController.getAllUser().contains(user)));

        }
    }
