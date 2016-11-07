/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Martin
 */



public class UserController {


//TODO Implement Database

// These only exist until a proper Database has been implemented */
private Map<String, String> dbLogin = new HashMap<>();
private Map<String, Integer> dbID = new HashMap<>();
private Map<String, Integer> dbRole = new HashMap<>();

public void loadDatabase(Map login, Map id, Map role){
    this.dbLogin=login;
    this.dbID=id;
    this.dbRole=role;
}


// End of Pre DB implementation, Delete before deployment


        public User logIn(String name, String passWord) throws Exception{  // Receives the entered Username and a has of the password from the UI, checks if user is valid and creates user object.
            User u;
            if(dbLogin.get(name)==passWord) {
                u = new User(name, dbID.get(name), dbRole.get(name));
            }else{
                Exception e = new Exception();
                throw e;
            }

            return u;
    }
}
