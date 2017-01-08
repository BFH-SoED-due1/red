/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.service;

import ch.bfh.ti.soed.hs16.srs.red.data.Password;
import ch.bfh.ti.soed.hs16.srs.red.data.User;
import ch.bfh.ti.soed.hs16.srs.red.jpa.MyUser;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

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
        String username = "user1";
        String password = "hunter2";
        User u;
        UserController uc = new UserController();
        uc.makeUser(username, 1, 1, password);
        u = uc.logIn(username, "123test");

    }

     /**
     * Test correct Password Returns User.
     */

    @Test
    public void testLoginCorrectPassword() throws Exception {
        UserController userController = new UserController();
        userController.clearAllUsers();
        userController.makeUser("Franz", 2, 3, "pw");
        Password pw = Password.getInstance();

        assertTrue(userController.logIn("Franz", "pw").getName().equals("Franz"));
    }

    /**
     * Test make user.
     */
    @Test
    public void testMakeUser() throws Exception {
        UserController userController = new UserController();
        userController.clearAllUsers();
        userController.makeUser("Franz", 2, 3, "pw");
        User franz = userController.findUser(2);

        assertTrue(userController.getAllUser().contains(franz));

    }

    /**
     * Test remove user.
     */
    @Test
    public void testRemoveUser() throws Exception {
        UserController userController = new UserController();
        userController.clearAllUsers();
        userController.makeUser("Franz", 3, 3, "pw");
        User franz = new MyUser("Franz", 3, 3, "pw");
        userController.removeUser(franz);
        assertTrue(!userController.getAllUser().contains(franz));
    }

    /**
     * Tests first if clearAllUsers really clears all and then if getAllUsers returns the newly added user(s)
     */
    @Test
    public void testClearAllUsersAndGetAll() throws Exception {
        UserController userController = new UserController();
        userController.clearAllUsers();
        assertTrue(userController.getAllUser().isEmpty());
        List<User> list = userController.getAllUser();
        userController.makeUser("Franz", 3, 3, "pw");
        list.add(userController.findUser(3));
        list.forEach(user -> assertTrue(userController.getAllUser().contains(user)));

    }
}
