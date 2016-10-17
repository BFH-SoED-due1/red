/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.soed.hs16.srs.red;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Martin
 */
public class Room {
    private String name;
    private String building;
    private int size;
    private Set<Reservation> reservations = new HashSet<>();

    public Room(String name, String building, int size) {
        this.name = name;
        this.building = building;
        this.size = size;
    }
    
    public void addReservation(Reservation res){
        reservations.add(res);
    }
    
    
        public void removeReservation(Reservation res){
        reservations.remove(res);
    }
}
