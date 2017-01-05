/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;


/**
 * The interface Reservation.
 *
 *
 */
public interface Reservation {


    /**
     * Gets id.
     *
     * @return the id of the Reservation
     */
    int getId();

    /**
     * Sets id.
     *
     * @param id the id of the Reservation
     */
    void setId(int id);

    /**
     * Gets owner.
     *
     * @return the user owning the reservation
     */
    User getOwner();

    /**
     * Sets owner.
     *
     * @param owner the user owning the reservation
     */
    void setOwner(User owner);

    /**
     * Gets room.
     *
     * @return the room reserved
     */
    Room getRoom();

    /**
     * Sets room.
     *
     * @param room the room reserved
     */
    void setRoom(Room room);


    /**
     * Gets time slot.
     *
     * @return the time slot belonging to reservation
     */
    TimeSlot getTimeSlot();

    /**
     * Sets time slot.
     *
     * @param timeSlot the time slot the room is being reserved
     */
    void setTimeSlot(TimeSlot timeSlot);
}
