/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;

/**
 *
 * @author Martin
 */
public abstract class Password {

    public static final String DEFAULT_PASSWORD_CLASS = "ch.bfh.ti.soed.hs16.srs.red.jpa.MyPassword";

    private static Password instance = null;

    public static Password getInstance() {
        // The following is NOT thread safe:
        if (instance == null) {
            try {
                @SuppressWarnings("rawtypes")
                Class clazz = Class.forName(DEFAULT_PASSWORD_CLASS);
                instance = (Password) clazz.newInstance();
            } catch (Exception ex) {
                System.err.println("Could not load class: " + DEFAULT_PASSWORD_CLASS);
                throw new RuntimeException("Could not load class: " + DEFAULT_PASSWORD_CLASS);
            }
        }
        return instance;
    }
/**
     * get Salted Hash.
     *
     * @param password the password that is to be hashed
     * @return the updated user
     */

    /**
     * get Salted Hash.
     * @param password the password that is to be hashed
     * @return the updated user
     * @throws java.lang.Exception
     */
    public abstract String getSaltedHash(String password) throws Exception;

/**
     * hash.
     * @param password the password that is to be hashed
     * @param salt the salt that is to be used for the hashing
     * @return the String which contains the salt and the hashed String
     * @throws java.lang.Exception
     */
    public abstract String hash(String password, byte[] salt) throws Exception;

    /**
     * check.
     * @param password the password that is to be checked
     * @param stored the stored salt and hash String
     * @return true if the password is correct
     * @throws java.lang.Exception
     */
    public abstract boolean check(String password, String stored) throws Exception;

}
