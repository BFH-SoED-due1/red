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
    public User makeUser(String name, int id, int role) {
        this.entityManager.getTransaction().begin();
        MyUser user = new MyUser(name, id, role);
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
        return user;
    }

    public User makeUser(String name,int role) {
        this.entityManager.getTransaction().begin();
        MyUser user = new MyUser(name,role);
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User updateUser(String name, int id, int role) {
        this.entityManager.getTransaction().begin();
        User user = this.entityManager.find(MyUser.class, id);
        user.setName(name);
        user.setRole(role);
        this.entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User findUser(int id) {
        User user = this.entityManager.find(MyUser.class, id);
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAllUsers() {
        Query query = this.entityManager.createQuery("select p from MyUser p");
        return query.getResultList();
    }




    @Override
    public void removeUser(User user) {
        this.entityManager.getTransaction().begin();
        //MyUser user = this.entityManager.find(MyUser.class, id);
        this.entityManager.remove(user);
        this.entityManager.getTransaction().commit();

    }

    @Override
    public Room makeRoom(int id, String name, String building, int capacity) {
        this.entityManager.getTransaction().begin();
        MyRoom room = new MyRoom(id, name, building, capacity);
        this.entityManager.persist(room);
        this.entityManager.getTransaction().commit();
        return room;
    }

    @Override
    public Room makeRoom(String name, String building, int capacity) {
        this.entityManager.getTransaction().begin();
        MyRoom room = new MyRoom(name, building, capacity);
        this.entityManager.persist(room);
        this.entityManager.getTransaction().commit();
        return room;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Room> findAllRooms() {
        Query query = this.entityManager.createQuery("select r from MyRoom r");
        return query.getResultList();
    }


    @Override
    public void removeRoom(Room room) {
        this.entityManager.getTransaction().begin();
        //MyRoom room = this.entityManager.find(MyRoom.class, id);
        this.entityManager.remove(room);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public Room updateRoom(int id, String name, String building, int capacity) {
        this.entityManager.getTransaction().begin();
        Room room = this.entityManager.find(MyRoom.class, id);
        room.setName(name);
        room.setBuilding(building);
        room.setSize(capacity);
        this.entityManager.getTransaction().commit();
        return room;
    }

    @Override
    public Room findRoom(int id) {
        Room room = this.entityManager.find(MyRoom.class, id);
        return room;
    }

    @Override
    public Reservation makeReservation(int id, User user, Room room, TimeSlot timeslot) {
        this.entityManager.getTransaction().begin();
        MyReservation reservation = new MyReservation(id, user, room, timeslot);
        this.entityManager.persist(reservation);
        this.entityManager.getTransaction().commit();
        return reservation;
    }

    @Override
    public Reservation makeReservation(User user, Room room, TimeSlot timeslot) {
        this.entityManager.getTransaction().begin();
        MyReservation reservation = new MyReservation(user, room, timeslot);
        this.entityManager.persist(reservation);
        this.entityManager.getTransaction().commit();
        return reservation;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Reservation> findAllReservations() {
        Query query = this.entityManager.createQuery("select r from MyReservation r");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Reservation> findAllReservationsOfUser(User owner) {
        Query query = this.entityManager.createQuery("select r from MyReservation r WHERE r.owner = :owner");
        return query.setParameter("owner", owner).getResultList();
    }


    @Override
    public void removeReservation(Reservation reservation) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(reservation);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public Reservation updateReservation(int id, User user, Room room, TimeSlot timeslot) {
        this.entityManager.getTransaction().begin();
        Reservation reservation = this.entityManager.find(MyReservation.class, id);
        reservation.setOwner(user);
        reservation.setRoom(room);
        reservation.setTimeSlot(timeslot);
        this.entityManager.getTransaction().commit();
        return reservation;
    }

    @Override
    public Reservation findReservation(int id) {
        Reservation reservation = this.entityManager.find(MyReservation.class, id);
        return reservation;
    }
}