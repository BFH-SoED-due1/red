/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Martin
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
    

    public TimeSlot(Date startTime, Date endTime) {
        if (startTime.before(endTime)) {
            this.startTime = startTime;
            this.endTime = endTime;
        } else {
            this.startTime = endTime;
            this.endTime = startTime;
        }

    }
    
    public TimeSlot(){
        
    }

    public Date getStart() {
        return startTime;
    }
    public void setStart(Date start){
        this.startTime = start;
    }

    public Date getEnd() {
        return endTime;
    }
    
    public void setEnd(Date end){
        this.endTime = end;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

}
