/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;

import java.util.Date;

/**
 * @author Martin
 */
public class TimeSlot {
    private Date startTime;
    private Date endTime;

    public TimeSlot(Date startTime, Date endTime) {
        if (startTime.before(endTime)) {
            this.startTime = startTime;
            this.endTime = endTime;
        } else {
            this.startTime = endTime;
            this.endTime = startTime;
        }

    }

    public Date getStart() {
        return startTime;
    }

    public Date getEnd() {
        return endTime;
    }

}
