/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.service;

import ch.bfh.ti.soed.hs16.srs.red.data.DataAccess;
import ch.bfh.ti.soed.hs16.srs.red.data.Reservation;
import ch.bfh.ti.soed.hs16.srs.red.data.Room;
import ch.bfh.ti.soed.hs16.srs.red.data.TimeSlot;
import ch.bfh.ti.soed.hs16.srs.red.data.User;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Reservation controller.
 *
 * @author Marvin
 */
public class ReservationController {


    /**
     * Generates a Reservation for a Room if timeslot is available
     *
     * @param timeslot the timeslot of the reservation
     * @param room     the room to reserve
     * @param user     User creating reservation
     * @return reservation if successful, null otherwise
     */
    public Reservation createReservation(TimeSlot timeslot, Room room, User user) {
        DataAccess dataAccess = DataAccess.getInstance();

        if (available(room, timeslot)) {
            Reservation res = dataAccess.makeReservation(user, room, timeslot);
            return res;
        }
        return null;
    }

    /**
     * Create reservation reservation.
     *
     * @param id       the id of reservation
     * @param timeslot the timeslot allocated
     * @param room     the room being reserved
     * @param user     the user making the reservation
     * @return the reservation made
     */
    public Reservation createReservation(int id, TimeSlot timeslot, Room room, User user) {
        DataAccess dataAccess = DataAccess.getInstance();

        if (available(room, timeslot)) {
            Reservation res = dataAccess.makeReservation(id, user, room, timeslot);
            return res;
        }
        return null;
    }


    /**
     * Remove a reservation from the list
     *
     * @param res the cancelled reservation
     */
    public void cancelReservation(Reservation res) {
        DataAccess dataAccess = DataAccess.getInstance();
        if (res != null) {
            dataAccess.removeReservation(res);

        }

    }

    /**
     * Find reservation.
     *
     * @param id the id of searched reservation
     * @return the reservation belonging to id
     */
    public Reservation findReservation(int id) {
          DataAccess dataAccess = DataAccess.getInstance();
          return dataAccess.findReservation(id);
    }


    /**
     * Find reservations of user list.
     *
     * @param owner the user owning the reservations
     * @return the list of reservations belonging to owner
     */
    public List<Reservation> findReservationsOfUser(User owner) {
          DataAccess dataAccess = DataAccess.getInstance();
          return dataAccess.findAllReservationsOfUser(owner);
    }

    /**
     * Removes all reservations from the DB
     */
    public static void ClearAllReservations() {
        DataAccess dataAccess = DataAccess.getInstance();
        List<Reservation> Reservations = dataAccess.findAllReservations();

        for (Reservation reservation : Reservations) {
            dataAccess.removeReservation(reservation);
        }

    }

    /**
     * returns a list of rooms which are open for specified timeslot
     *
     * @param t the timeslot to check for
     * @return list of rooms open at timeslot
     */
    public static List<Room> getOpenRooms(TimeSlot t) {

        DataAccess dataAccess = DataAccess.getInstance();
        List<Room> allRooms = dataAccess.findAllRooms();
        List<Room> openRooms = new ArrayList<Room>();
        for (Room r : allRooms) {
            if (available(r, t)) {
                openRooms.add(r);
            }
        }
        return openRooms;
    }

    /**
     * checks if a timeslot fits into the reservations of a room
     *
     * @param room     the room to check
     * @param timeslot the timeslot to check for
     * @return true if timeslot open, false otherwise
     */
    private static boolean available(Room room, TimeSlot timeslot) {
        DataAccess dataAccess = DataAccess.getInstance();
        List<Reservation> Reservations = dataAccess.findAllReservations();
        for (Reservation reservation : Reservations) {
            if (reservation.getRoom().equals(room)) {
                TimeSlot t = reservation.getTimeSlot();
                if (timeslot.getStart().after(t.getStart()) && timeslot.getStart().before(t.getEnd())) {
                    //the start of the needed timeslot is in between the start and end of another timeslot
                    return false;
                }
                if (timeslot.getEnd().after(t.getStart()) && timeslot.getEnd().before(t.getEnd())) {
                    //the end of the needed timeslot is in between the start and end of another timeslot
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * Gets all reservations.
     *
     * @return the list of all reservations
     */
    public List<Reservation> getAllReservations() {
        DataAccess dataAccess = DataAccess.getInstance();
        return dataAccess.findAllReservations();
    }

    /**
     * Clear all reservations.
     */
    public void clearAllReservations() {
        DataAccess dataAccess = DataAccess.getInstance();
        List<Reservation> reservations = dataAccess.findAllReservations();
        if(!reservations.isEmpty()){
        for (Reservation reservation : reservations) {
            cancelReservation(reservation);
        }}
    }


}
