/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author Martin
 */
public class RoomCreator {







    public Room createRoom(String name, String building, int size){
        return new Room(name, building, size);
        //TODO add Room to Database
    }


    // Input has to be an *.csv file with 3 collumns, one room per collumn in order: name, building, size
    // Sanity Check before calling this class
    public Set<Room> createRoomsFromCSV(String filepath) throws FileNotFoundException, IOException{
        Set<Room> rooms = new HashSet();
            System.out.println("Called Class");
            String line;
            String splitter = ";";
            String[] room;

  try (BufferedReader br = new BufferedReader(new FileReader(filepath))){


          while((line = br.readLine()) != null){

                room = line.split(splitter);
                System.out.println(" "+ room.length);
                rooms.add(createRoom(room[0], room[1], Integer.parseInt(room[2])));

          }
    } catch (Exception e) {
        System.out.println("Couldn't load file");
    }
      return rooms;
    }


}