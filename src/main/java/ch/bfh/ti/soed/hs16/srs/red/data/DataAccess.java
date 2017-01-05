/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;

import java.util.List;

/**
 * The Data access class, creates connection to DB
 */
public abstract class DataAccess { // acts as a singleton
    /**
     * The constant DEFAULT_DATA_ACCESS_CLASS gives path to default implementation.
     */
// A more versatile approach could be introduced here:
    // - reading value via a Java property
    // - using Java's CDI
    public static final String DEFAULT_DATA_ACCESS_CLASS = "ch.bfh.ti.soed.hs16.srs.red.jpa.JPADataAccess";

    private static DataAccess instance = null;

    /**
     * Gets instance.
     *
     * @return the instance of DataAcess (something like the view of a DB)
     */
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

    /**
     * Make user user.
     *
     * @param name the name
     * @param id   the id
     * @param role the role
     * @return the user
     */
// Methods for persons
    //////////////////////
    public abstract User makeUser(String name, int id, int role);

    /**
     * Make user.
     *
     * @param name the name of user
     * @param role the role of user
     * @return the user made
     */
    public abstract User makeUser(String name, int role);

    /**
     * Update user.
     *
     * @param name the name to be updated
     * @param id   the id to identify user( does not get updated)
     * @param role the role to be updated
     * @return the updated user
     */
    public abstract User updateUser(String name, int id, int role);

    /**
     * Find user.
     *
     * @param id the id to find and identify user with
     * @return the user belonging to id
     */
    public abstract User findUser(int id);

    /**
     * Find all users list.
     *
     * @return the list of all user
     */
    public abstract List<User> findAllUsers();

    /**
     * Remove user.
     *
     * @param user the user to be removed
     */
    public abstract void removeUser(User user);


    /**
     * Make room room.
     *
     * @param id       the id of room
     * @param name     the name of room
     * @param building the building room is in
     * @param capacity the capacity (size) room has
     * @return the room created
     */
// Methods for rooms
    ////////////////////
    public abstract Room makeRoom(int id, String name, String building, int capacity);

    /**
     * Make room.
     *
     * @param name     the name of room
     * @param building the building room is in
     * @param capacity the capacity (size) room has
     * @return the room created
     */
    public abstract Room makeRoom(String name, String building, int capacity);

    /**
     * Find all rooms list.
     *
     * @return the list of all rooms
     */
    public abstract List<Room> findAllRooms();

    /**
     * Remove room.
     *
     * @param room the room to be removed
     */
    public abstract void removeRoom(Room room);

    /**
     * Update room room.
     *
     * @param id       the id to identify room
     * @param name     the new name
     * @param building the new building
     * @param capacity the new capacity
     * @return the updated room
     */
    public abstract Room updateRoom(int id, String name, String building, int capacity);

    /**
     * Find room.
     *
     * @param id the id of room to be found
     * @return the room belonging to id
     */
    public abstract Room findRoom(int id);

    /**
     * Make reservation
     *
     * @param id       the id of reservation
     * @param user     the user owning reservation
     * @param room     the room reserved
     * @param timeslot the timeslot allocated
     * @return the reservation made
     */
// Methods for reservations
    ///////////////////////////
    public abstract Reservation makeReservation(int id, User user, Room room, TimeSlot timeslot);

    /**
     * Make reservation
     *
     * @param user     the user owning reservation
     * @param room     the room reserved
     * @param timeslot the timeslot allocated
     * @return the reservation made
     */
    public abstract Reservation makeReservation(User user, Room room, TimeSlot timeslot);

    /**
     * Find all reservations list.
     *
     * @return the list of all reservations
     */
    public abstract List<Reservation> findAllReservations();

    /**
     * Find all reservations of user
     *
     * @param owner the owner of the reservations
     * @return the list of all reservations made by user owner
     */
    public abstract List<Reservation> findAllReservationsOfUser(User owner);

    /**
     * Remove reservation.
     *
     * @param reservation the reservation removed
     */
    public abstract void removeReservation(Reservation reservation);

    /**
     * Update reservation reservation.
     *
     * @param id       the id to identify te reservation with
     * @param user     the new user
     * @param room     the new room
     * @param timeslot the new timeslot
     * @return the updated reservation
     */
    public abstract Reservation updateReservation(int id, User user, Room room, TimeSlot timeslot);

    /**
     * Find reservation
     *
     * @param id the id to search with
     * @return the reservation belonging to id
     */
    public abstract Reservation findReservation(int id);

}
