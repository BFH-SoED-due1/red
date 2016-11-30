/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.jpa;

import ch.bfh.ti.soed.hs16.srs.red.data.DataAccess;
import ch.bfh.ti.soed.hs16.srs.red.data.Reservation;
import ch.bfh.ti.soed.hs16.srs.red.data.Room;
import ch.bfh.ti.soed.hs16.srs.red.data.TimeSlot;
import ch.bfh.ti.soed.hs16.srs.red.data.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPADataAccess extends DataAccess {
	public static final String PERSISTENCE_UNIT = "srs-pu";
	private EntityManager entityManager;

	public JPADataAccess() {
		this.entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
	}

	@Override
	public User makeUser(String name,int id, int role) {
		this.entityManager.getTransaction().begin();
		MyUser user = new MyUser(name, id, role);
		this.entityManager.persist(user);
		this.entityManager.getTransaction().commit();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		Query query = this.entityManager.createQuery("select p from User p");
		return query.getResultList();
	}

	@Override
	public void removeUser(Long id) {
		this.entityManager.getTransaction().begin();
		MyUser user = this.entityManager.find(MyUser.class, id);
		this.entityManager.remove(user);
		this.entityManager.getTransaction().commit();

	}

	@Override
	public Room makeRoom(String name, String building, int capacity) {
		this.entityManager.getTransaction().begin();
		MyRoom room = new MyRoom(name,building, capacity);
		this.entityManager.persist(room);
		this.entityManager.getTransaction().commit();
		return room;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> findAllRooms() {
		Query query = this.entityManager.createQuery("select r from Room r");
		return query.getResultList();
	}

	@Override
	public void removeRoom(Long id) {
		this.entityManager.getTransaction().begin();
		MyRoom room = this.entityManager.find(MyRoom.class, id);
		this.entityManager.remove(room);
		this.entityManager.getTransaction().commit();
	}

	@Override
	public Reservation makeReservation(User user, Room room, TimeSlot timeslot) {
		this.entityManager.getTransaction().begin();
		MyReservation reservation = new MyReservation(user, room, timeslot);
		this.entityManager.persist(reservation);
	//	user.addReservation(reservation);
		room.addReservation(reservation);
		this.entityManager.merge(user);
		this.entityManager.merge(room);
		this.entityManager.getTransaction().commit();
		return reservation;
	}
}