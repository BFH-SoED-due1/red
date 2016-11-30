package ch.bfh.ti.soed.hs16.srs.red.service;

import ch.bfh.ti.soed.hs16.srs.red.data.Reservation;
import ch.bfh.ti.soed.hs16.srs.red.data.Room;
import ch.bfh.ti.soed.hs16.srs.red.data.TimeSlot;
import ch.bfh.ti.soed.hs16.srs.red.data.User;
import ch.bfh.ti.soed.hs16.srs.red.jpa.MyReservation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marvin
 */
public class ReservationController {

    //TODO Implement Database


    private static List<Reservation> Reservations = new ArrayList<>();;


    /**
     * Generates a Reservation for a Room if timeslot is available
     * @param timeslot the timeslot of the reservation
     * @param room the room to reserve
     * @param user User creating reservation
     * @return reservation if successful, null otherwise
     */
    public static Reservation createReservation(TimeSlot timeslot, Room room, User user) {
        if (available(room, timeslot)) {
            Reservation res = new MyReservation(user, room, timeslot);
            Reservations.add(res);
            return res;
        }
        return null;
    }


    /**
     * Remove a reservation from the list
     * @param res the cancelled reservation
     */
    public static void cancelReservation(Reservation res) {


        for (Reservation reservation : Reservations) {
            if (reservation.equals(res)) {
                res.cancelReservation();
                Reservations.remove(Reservations.indexOf(res));
                break;
            }
        }

    }
    /**
     * Removes all reservations from the list
     */
    public static void ClearAllReservations() {


        for (Reservation reservation : Reservations) {
            reservation.cancelReservation();
            Reservations.remove(Reservations.indexOf(reservation));
        }

    }

     /**
     * returns a list of rooms which are open for specified timeslot
     * @param t the timeslot to check for
     * @param allRooms a list of all rooms. TODO: make roomcontroller static, get list from there
     * @return list of rooms open at timeslot
     */
    public static List<Room> getOpenRooms(TimeSlot t, List<Room> allRooms)
    {
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
     * @param room the room to check
     * @param timeslot the timeslot to check for
     * @return true if timeslot open, false otherwise
     */
    private static boolean available(Room room, TimeSlot timeslot) {
        for (Reservation reservation : Reservations) {
            if (reservation.getRoom().equals(room)) {
                TimeSlot t = reservation.getTimeSlot();
                if (timeslot.getStart().after(t.getStart())&&timeslot.getStart().before(t.getEnd())) {
                    //the start of the needed timeslot is in between the start and end of another timeslot
                    return false;
                }
                if (timeslot.getEnd().after(t.getStart())&&timeslot.getEnd().before(t.getEnd())) {
                    //the end of the needed timeslot is in between the start and end of another timeslot
                    return false;
                }
            }

        }
        return true;
    }


}
