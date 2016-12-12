/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;

import java.util.List;

public abstract class DataAccess { // acts as a singleton
    // A more versatile approach could be introduced here:
    // - reading value via a Java property
    // - using Java's CDI
    public static final String DEFAULT_DATA_ACCESS_CLASS = "ch.bfh.ti.soed.hs16.srs.red.jpa.JPADataAccess";

    private static DataAccess instance = null;

    public static DataAccess getInstance() {
        // The following is NOT thread safe:
        if (instance == null) {
            try {
                @SuppressWarnings("rawtypes")
                Class clazz = Class.forName(DEFAULT_DATA_ACCESS_CLASS);
                instance = (DataAccess) clazz.newInstance();
            } catch (Exception ex) {
                System.err.println("Could not load class: " + DEFAULT_DATA_ACCESS_CLASS);
                throw new RuntimeException("Could not load class: " + DEFAULT_DATA_ACCESS_CLASS);
            }
        }
        return instance;
    }

    // Methods for persons
    //////////////////////
    public abstract User makeUser(String name, int id, int role);

    public abstract User makeUser(String name, int role);

    public abstract User updateUser(String name, int id, int role);

    public abstract User findUser(int id);

    public abstract List<User> findAllUsers();

    public abstract void removeUser(User user);


    // Methods for rooms
    ////////////////////
    public abstract Room makeRoom(int id, String name, String building, int capacity);

    public abstract Room makeRoom(String name, String building, int capacity);

    public abstract List<Room> findAllRooms();

    public abstract void removeRoom(Room room);

    public abstract Room updateRoom(int id, String name, String building, int capacity);

    public abstract Room findRoom(int id);
    // Methods for reservations
    ///////////////////////////
    public abstract Reservation makeReservation(int id, User user, Room room, TimeSlot timeslot);

    public abstract Reservation makeReservation(User user, Room room, TimeSlot timeslot);

    public abstract List<Reservation> findAllReservations();
    public abstract List<Reservation> findAllReservationsOfUser(User owner);

    public abstract void removeReservation(Reservation reservation);

    public abstract Reservation updateReservation(int id, User user, Room room, TimeSlot timeslot);

    public abstract Reservation findReservation(int id);

}
