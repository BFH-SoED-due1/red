/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.soed.hs16.srs.red;

import java.sql.Time;

/**
 *
 * @author Martin
 */
class TimeSlot {
    private Time startTime;
    private Time endTime;
    
    public TimeSlot(Time startTime,Time endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
}
