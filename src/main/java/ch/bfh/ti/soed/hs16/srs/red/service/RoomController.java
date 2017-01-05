/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.service;

import ch.bfh.ti.soed.hs16.srs.red.data.DataAccess;
import ch.bfh.ti.soed.hs16.srs.red.data.Room;

import java.util.List;


/**
 * The type Room controller.
 *
 * @author tambur
 */
public class RoomController {


    /**
     * Adding a room to the List of all existing rooms
     *
     * @param room the room to add to the list
     * @return tells you if success. -1 if room is null or already exists and 1 if adding was successful
     */
    public int addRoom(Room room) {
        DataAccess dataAccess = DataAccess.getInstance();
        if (dataAccess.findAllRooms().contains(room) || room == null) {
            return -1;//nothing happens return -1 => room already exist or null
        }

        dataAccess.makeRoom(room.getId(), room.getName(), room.getBuilding(), room.getSize());//return 1 => success
        return 1;

    }


    /**
     * Remove a room from the List of all existing rooms
     *
     * @param room the room to be removed
     */
    public void removeRoom(Room room) {
        DataAccess dataAccess = DataAccess.getInstance();

        if (room != null) {
            dataAccess.removeRoom(room);

        }

    }

    /**
     * Find room.
     *
     * @param id the id to find
     * @return the room belonging to ID
     */
    public Room findRoom(int id) {
          DataAccess dataAccess = DataAccess.getInstance();
          return dataAccess.findRoom(id);
    }

    /**
     * Gets all rooms.
     *
     * @return list of all rooms in DB
     */
    public List<Room> getAllRooms() {
        DataAccess dataAccess = DataAccess.getInstance();
        return dataAccess.findAllRooms();
    }

    /**
     * Clear all rooms.
     */
    public void clearAllRooms() {
        DataAccess dataAccess = DataAccess.getInstance();
        List<Room> rooms = dataAccess.findAllRooms();
        for (Room room : rooms) {
            removeRoom(room);
        }
    }
}
