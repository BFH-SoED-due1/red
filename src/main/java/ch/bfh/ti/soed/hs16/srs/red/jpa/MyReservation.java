/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.jpa;

import ch.bfh.ti.soed.hs16.srs.red.data.Reservation;
import ch.bfh.ti.soed.hs16.srs.red.data.Room;
import ch.bfh.ti.soed.hs16.srs.red.data.TimeSlot;
import ch.bfh.ti.soed.hs16.srs.red.data.User;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Martin
 */
@Entity
@Table
public class MyReservation implements Reservation {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private int id;
    
    @OneToOne(cascade = CascadeType.ALL)
    private TimeSlot timeslot;
    
    @ManyToOne
    private MyUser owner;
    
    @ManyToOne()
    private MyRoom room;
    
    public MyReservation(int id,User user, Room room, TimeSlot timeslot) {
        this.id = id;
        this.owner = (MyUser) user;
        this.room = (MyRoom) room;
        this.timeslot = timeslot;

    }


    public MyReservation(User user, Room room, TimeSlot timeslot) {
        this.owner = (MyUser) user;
        this.room = (MyRoom) room;
        this.timeslot = timeslot;

    }
    public MyReservation() {
        
    }


    @Override
    public User getOwner() {
        return owner;
    }

    @Override
    public void setOwner(User owner) {
        this.owner = (MyUser) owner;
    }
    
    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public void setRoom(Room room) {
        this.room = (MyRoom) room;
    }
    
    @Override
    public TimeSlot getTimeSlot() {
        return timeslot;
    }

    @Override
    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeslot=timeSlot;
    }
    
    @Override
    public int getId() {
       return id;
    }

    @Override
    public void setId(int id) {
       this.id = id;
    }

    

    

    


}
