/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;

import javax.persistence.*;
import java.util.Date;

/**
 * The Time slot class, defined by start and end date.
 *
 *
 */
@Entity
@Table
public class TimeSlot {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date startTime;
    @Temporal(TemporalType.DATE)

    private Date endTime;


    /**
     * Instantiates a new Time slot.
     *
     * @param startTime the start time
     * @param endTime   the end time
     */
    public TimeSlot(Date startTime, Date endTime) {
        if (startTime.before(endTime)) {
            this.startTime = startTime;
            this.endTime = endTime;
        } else {
            this.startTime = endTime;
            this.endTime = startTime;
        }

    }

    /**
     * Instantiates a new Time slot.
     */
    public TimeSlot(){

    }

    /**
     * Gets startTime
     *
     * @return the startTime as Date
     */
    public Date getStart() {
        return startTime;
    }

    /**
     * Set startTime.
     *
     * @param start the startTime (Date) to be set
     */
    public void setStart(Date start){
        this.startTime = start;
    }

    /**
     * Gets endTime.
     *
     * @return the endTime as Date
     */
    public Date getEnd() {
        return endTime;
    }

    /**
     * Set endTime
     *
     * @param end the endTime to be set
     */
    public void setEnd(Date end){
        this.endTime = end;
    }

    /**
     * Gets id.
     *
     * @return the id of Timeslot
     */
    public int getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id to be set
     */
    public void setId(int id){
        this.id = id;
    }

}
