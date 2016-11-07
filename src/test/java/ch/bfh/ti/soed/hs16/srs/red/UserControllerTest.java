/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

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
        UserController uc = new UserController();
         Map<String, String> dbLogin = new HashMap<>();
         dbLogin.put(username, password);
         Map<String, Integer> dbID = new HashMap<>();
         dbID.put(username, 1);
         Map<String, Integer> dbRole = new HashMap<>();
         dbRole.put(username, 0);

         uc.loadDatabase(dbLogin, dbID, dbRole);
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
        UserController uc = new UserController();
         Map<String, String> dbLogin = new HashMap<>();
         dbLogin.put(username, password);
         Map<String, Integer> dbID = new HashMap<>();
         dbID.put(username, 1);
         Map<String, Integer> dbRole = new HashMap<>();
         dbRole.put(username, 0);

         uc.loadDatabase(dbLogin, dbID, dbRole);

            u = uc.logIn("notuser", password);

            System.out.println("Incorrect Username or Password");
        }





@Test(expected = Exception.class)
    public void testLoginWrongPassword() throws Exception {
        String username="user1";
        String password="hunter2";
        User u;
        UserController uc = new UserController();
         Map<String, String> dbLogin = new HashMap<>();
         dbLogin.put(username, password);
         Map<String, Integer> dbID = new HashMap<>();
         dbID.put(username, 1);
         Map<String, Integer> dbRole = new HashMap<>();
         dbRole.put(username, 0);

         uc.loadDatabase(dbLogin, dbID, dbRole);

            u = uc.logIn(username, "123test");

            System.out.println("Incorrect Username or Password");
        }


    }
