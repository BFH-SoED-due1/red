/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.soed.hs16.srs.red;

import java.util.Date;


/**
 *
 * @author Martin
 */
public class Reservation {
    private User owner;
    private Room room;
    private TimeSlot timeslot;
    private Date date;
    
    
    public Reservation(User user, Room room, TimeSlot timeslot, Date date){
        this.owner = user;
        this.room=room;
        this.timeslot=timeslot;
        this.date=date;
    }
    
    public void cancelReservation(){
        room.removeReservation(this);
    }

    public User getOwner() {
        return owner;
    }
    
    
}
