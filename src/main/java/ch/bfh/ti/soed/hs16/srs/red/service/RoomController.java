/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.service;

import ch.bfh.ti.soed.hs16.srs.red.data.DataAccess;
import ch.bfh.ti.soed.hs16.srs.red.data.Room;



/**
 * @author tambur
 */
public class RoomController {

    //TODO Implement Database





    /**
     * Adding a room to the List of all existing rooms
     *
     * @param room the room to add to the list
     * @return tells you if success. -1 if room is null or already exists and 1 if adding was successful
     */
    public int addRoom(Room room) {
        DataAccess dataAccess = DataAccess.getInstance();
        if (dataAccess.findAllRooms().contains(room)||room == null){
            return -1;//nothing happens return -1 => room already exist or null
        }

        dataAccess.findAllRooms().add(room);//return 1 => success
        return 1;

        /*if (roomOverview == null) {
            this.roomOverview = new ArrayList<>();
        }

        if (room != null) {

            for (Room r : roomOverview)
                if (r.getName().equals(room.getName())) return -1; // return -1 => room already exist or null

            roomOverview.add(room);
            return 1; //return 1 => success
        }

        return -1; //nothing happens return -1 => room already exist or null*/
    }


    /**
     * Remove a room from the List of all existing rooms
     *
     * @param room the room to be removed
     */
    public void removeRoom(Room room) {
        DataAccess dataAccess = DataAccess.getInstance();

        if (room != null) {
            dataAccess.findAllRooms().remove(room);


        }

    }

}
