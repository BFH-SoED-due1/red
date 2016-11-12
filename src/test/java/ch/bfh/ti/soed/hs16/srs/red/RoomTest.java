/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.soed.hs16.srs.red;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RoomTest {

    @Test
    public void testReservationKnowsOwner() {

        Room room = new Room("room", "building", 12);
        assertEquals(room.getName(), "room");
    }

}
