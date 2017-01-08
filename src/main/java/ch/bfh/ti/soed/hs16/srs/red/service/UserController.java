/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.service;

import ch.bfh.ti.soed.hs16.srs.red.data.DataAccess;
import ch.bfh.ti.soed.hs16.srs.red.data.Password;
import ch.bfh.ti.soed.hs16.srs.red.data.User;
import java.util.List;


/**
 * The User controller.
 */
public class UserController {


    /**
     * Instantiates a new User controller.
     *
     */
    public UserController() {

    }

    /**
     * Log in user.
     *
     * @param name     the name
     * @param passWord the password
     * @return the user
     * @throws Exception the exception
     */
    public User logIn(String name, String passWord) throws Exception {  // Receives the entered Username and a hash of the password from the UI, checks if user is valid and creates user object.
        //MyUser u;
        DataAccess dataAccess = DataAccess.getInstance();
        Password pw = Password.getInstance();
        List<User> user = dataAccess.findAllUsers();
        for (User u : user) {
            if(u.getName().equals(name)){
                if(pw.check(passWord, u.getPassHash())){
                    return u;
                }
            }
        }

        Exception e = new Exception("no such username or password");
        throw e;

    }

    /**
     * removes single user
     *
     * @param user the user
     * @return int
     */
    public int removeUser(User user) {
        DataAccess dataAccess = DataAccess.getInstance();
        if (dataAccess.findAllUsers().contains(user) && user != null) {
            dataAccess.removeUser(user);
            return 1;
        }
        return -1;
    }

    /**
     * finds user, depending on his id
     *
     * @param id unique id
     * @return the user
     */
    public User findUser(int id) {
          DataAccess dataAccess = DataAccess.getInstance();
          return dataAccess.findUser(id);
    }

    /**
     * makes new user
     *
     * @param name name
     * @param id   unique id
     * @param role admin, user or poweruser
     * @param password unhashed password
     * @throws java.lang.Exception
     */
    public void makeUser(String name, int id, int role, String password) throws Exception {
        DataAccess dataAccess = DataAccess.getInstance();

        dataAccess.makeUser(name, id, role, password);

    }
    /**
     * makes new user
     *
     * @param name name
     * @param role admin, user or poweruser
     */
    public void makeUser(String name, int role) {
        DataAccess dataAccess = DataAccess.getInstance();

        dataAccess.makeUser(name, role);

    }

    /**
     * Gets all user.
     *
     * @return List with all users
     */
    public List<User> getAllUser() {
        DataAccess dataAccess = DataAccess.getInstance();
        return dataAccess.findAllUsers();
    }

    /**
     * deletes all users from DB
     */
    public void clearAllUsers() {
        DataAccess dataAccess = DataAccess.getInstance();
        List<User> users = dataAccess.findAllUsers();
        if(!users.isEmpty()){
        for (User user : users) {
            removeUser(user);
        }}
    }


}
