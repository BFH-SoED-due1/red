/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.data;

import java.util.List;

public abstract class DataAccess { // acts as a singleton
	// A more versatile approach could be introduced here:
	// - reading value via a Java property
	// - using Java's CDI
	public static final String DEFAULT_DATA_ACCESS_CLASS = "ch.bfh.ti.soed.hs16.srs.red.jpa.JPADataAccess";

	private static DataAccess instance = null;

	public static DataAccess getInstance() {
		// The following is NOT thread safe:
		if (instance == null) {
			try {
				@SuppressWarnings("rawtypes")
				Class clazz = Class.forName(DEFAULT_DATA_ACCESS_CLASS);
				instance = (DataAccess) clazz.newInstance();
			} catch (Exception ex) {
				System.err.println("Could not load class: " + DEFAULT_DATA_ACCESS_CLASS);
				throw new RuntimeException("Could not load class: " + DEFAULT_DATA_ACCESS_CLASS);
			}
		}
		return instance;
	}

	// Methods for persons
	//////////////////////
	public abstract User makeUser(String name,int id, int role);

	public abstract List<User> findAllUsers();

	public abstract void removeUser(Long id);

	// Methods for rooms
	////////////////////
	public abstract Room makeRoom(String name,String building, int capacity);

	public abstract List<Room> findAllRooms();

	public abstract void removeRoom(Long id);

	// Methods for reservations
	///////////////////////////
	public abstract Reservation makeReservation(User user, Room room, TimeSlot timeslot);
}