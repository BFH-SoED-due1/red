/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.jpa;

/**
 *
 * @author Martin
 */
import ch.bfh.ti.soed.hs16.srs.red.data.Password;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.apache.commons.codec.binary.Base64;
/**
 * The MyPassword class, implements Password.
 *
 *
 */

public class MyPassword extends Password {

    public static final String PERSISTENCE_UNIT = "srs-pu";
    private final EntityManager entityManager;

    /**
     * Instantiates a new MyPassword class.
     */
    public MyPassword() {
        this.entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
    }




    // The higher the number of iterations the more
    // expensive computing the hash is for us and
    // also for an attacker.
    private static final int iterations = 20*10;
    private static final int saltLen = 32;
    private static final int desiredKeyLen = 256;

    /** Computes a salted PBKDF2 hash of given plaintext password
        suitable for storing in a database.
        Empty passwords are not supported. */

     /**
     * get Salted Hash.
     * @param password the password that is to be hashed
     * @return the updated user
     * @throws java.lang.Exception
     */

    @Override
    public String getSaltedHash(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
    }

    /** Checks whether given plaintext password corresponds
        to a stored salted hash of the password. */

    /**
     * check.
     * @param password the password that is to be checked
     * @param stored the stored salt and hash String
     * @return true if the password is correct
     * @throws java.lang.Exception
     */

    @Override
    public boolean check(String password, String stored) throws Exception{
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException(
                "The stored password have the form 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[0]));
        return hashOfInput.equals(saltAndPass[1]);
    }

    // using PBKDF2 from Sun

        /**
     * hash.
     * @param password the password that is to be hashed
     * @param salt the salt that is to be used for the hashing
     * @return the String which contains the salt and the hashed String
     * @throws java.lang.Exception
     */
    @Override
    public String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
            password.toCharArray(), salt, iterations, desiredKeyLen)
        );
        return Base64.encodeBase64String(key.getEncoded());
    }
}


