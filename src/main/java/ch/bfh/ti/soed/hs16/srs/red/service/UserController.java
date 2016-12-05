/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.service;

import ch.bfh.ti.soed.hs16.srs.red.data.DataAccess;
import ch.bfh.ti.soed.hs16.srs.red.data.User;
import java.util.List;
import java.util.Map;



public class UserController {
    private Map<String, String> pws;//sould be in DB before Deploy

    public UserController(Map<String, String> pws) {
        this.pws = pws;
    }

    public User logIn(String name, String passWord) throws Exception {  // Receives the entered Username and a has of the password from the UI, checks if user is valid and creates user object.
        //MyUser u;
        DataAccess dataAccess = DataAccess.getInstance();
        List<User> user = dataAccess.findAllUsers();
        for (User u : user) {
            if (u.getName().equals(name) && pws.get(name).equals(passWord)) {
                return u;
            }
        }
        Exception e = new Exception();
        throw e;
           /* if(dataAccess.findAllUsers().get()==passWord) {
                u = new MyUser(name, dbID.get(name), dbRole.get(name));
            }else{
                Exception e = new Exception();
                throw e;
            }

            return u;*/
    }

    public int removeUser(User user) {
        DataAccess dataAccess = DataAccess.getInstance();
        if (dataAccess.findAllUsers().contains(user) && user != null) {
            dataAccess.removeUser(user);
            return 1;
        }
        return -1;
    }

    public User findUser(int id) {
          DataAccess dataAccess = DataAccess.getInstance();
          return dataAccess.findUser(id);
    }

    public void makeUser(String name, int id, int role) {
        DataAccess dataAccess = DataAccess.getInstance();

        dataAccess.makeUser(name, id, role);

    }

    public List<User> getAllUser() {
        DataAccess dataAccess = DataAccess.getInstance();
        return dataAccess.findAllUsers();
    }

    public void clearAllUsers() {
        DataAccess dataAccess = DataAccess.getInstance();
        List<User> users = dataAccess.findAllUsers();
        for (User user : users) {
            removeUser(user);
        }
    }


}
