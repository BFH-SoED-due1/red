package ch.bfh.ti.soed.hs16.srs.red;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * @author Marvin
 */
public class ReservationController {

    //TODO Implement Database


    private List<Reservation> Reservations = new ArrayList<>();;


    /**
     * Generates a Reservation for a Room if timeslot is available
     * @param timeslot the timeslot of the reservation
     * @param room the room to reserve
     * @param user User creating reservation
     * @return true if reservation successfull, false if room not available
     */
    public boolean createReservation(TimeSlot timeslot, Room room, User user) {
        if (available(room, timeslot)) {
            Reservations.add(new Reservation(user, room, timeslot));
            return true;
        }
        return false;
    }


    /**
     * Remove a reservation from the list
     * @param res the cancelled reservation
     */
    public void cancelReservation(Reservation res) {

        
        for (Reservation reservation : Reservations) {
            if (reservation.equals(res)) {
                res.cancelReservation();
                Reservations.remove(Reservations.indexOf(res));
                break;
            }
        }

    }

    private boolean available(Room room, TimeSlot timeslot) {
        for (Reservation reservation : Reservations) {
            if (reservation.getRoom().equals(room)) {
                TimeSlot t = reservation.getTimeSlot();
                if (timeslot.getStart().compareTo(t.getStart()) >=0 && timeslot.getStart().compareTo(t.getEnd()) <0) {
                    //the start of the needed timeslot is in between the start and end of another timeslot
                    return false;
                }
                if (timeslot.getEnd().compareTo(t.getStart()) >=0 && timeslot.getEnd().compareTo(t.getEnd()) <0) {
                    //the end of the needed timeslot is in between the start and end of another timeslot
                    return false;
                }
            }
            
        }
        return true;
    }

}
