/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.soed.hs16.srs.red;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 *
 * @author Martin
 */
public class ReservationTest {
    
   
    /**
     * Test of cancelReservation method, of class Reservation.
     */
    @Test
    public void testMakeReservation() {
        User user = new User("user", 1, 1);
        Room room = new Room("room", "building", 12); 
        user.makeReservation(room, null, null);
        assertFalse(user.getReservations().isEmpty());
                
        
    }
    
}
