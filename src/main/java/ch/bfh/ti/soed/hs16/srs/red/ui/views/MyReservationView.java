/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.ui.views;

import ch.bfh.ti.soed.hs16.srs.red.data.Reservation;
import ch.bfh.ti.soed.hs16.srs.red.data.Room;
import ch.bfh.ti.soed.hs16.srs.red.data.TimeSlot;
import ch.bfh.ti.soed.hs16.srs.red.data.User;
import ch.bfh.ti.soed.hs16.srs.red.service.ReservationController;
import ch.bfh.ti.soed.hs16.srs.red.service.RoomController;
import ch.bfh.ti.soed.hs16.srs.red.service.UserController;
import ch.bfh.ti.soed.hs16.srs.red.ui.helper.Menu;
import ch.bfh.ti.soed.hs16.srs.red.ui.helper.SubWindowReservation;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by tambur on 04.12.2016.
 */
public class MyReservationView extends CustomComponent implements View {


    //--------- layout -------------
    private VerticalLayout root;
    private Window subWindowReservation;

    //--------- content ------------
    private Menu navigation;
    private Label labelWelcome;
    private Label error;
    private Calendar calendarWeek;
    private Button buttonAddRes;

    //--------- sub Window---------
    private SubWindowReservation contentSubWindow;
    private Button saveButton;
    private Button deleteButton;

    //--------- controller ---------
    private ReservationController reservationController;
    private UserController userController;
    private RoomController roomController;

    private int id;


    public MyReservationView(Navigator nav) {

        /*---------------------------------
        initialize objects
        ---------------------------------*/
        this.root = new VerticalLayout();
        this.subWindowReservation = new Window();

        this.navigation = new Menu(nav);
        this.labelWelcome = new Label();
        this.error = new Label();
        this.calendarWeek = new Calendar();
        this.buttonAddRes = new Button("add Reservation", this::buttonAddReservation);  //handle event in method addReservation
        this.contentSubWindow = new SubWindowReservation();
        this.saveButton = contentSubWindow.getButtonSave();
        this.deleteButton = contentSubWindow.getButtonDelete();


        this.reservationController = new ReservationController();
        this.userController = new UserController();
        this.roomController = new RoomController();

        /*---------------------------------
        specialize objects
        ---------------------------------*/
        calendarWeek.setFirstDayOfWeek(GregorianCalendar.MONDAY);
        saveButton.addClickListener(this::buttonSave);
        deleteButton.addClickListener(this::buttonDelete);

        List<Room> rooms = roomController.getAllRooms();
        for (int i = 0; i < rooms.size(); i++) {
            contentSubWindow.getRooms().addItem(rooms.get(i).getId() + " " + rooms.get(i).getName());
        }

        /*---------------------------------
        add style names
        ---------------------------------*/
        calendarWeek.setPrimaryStyleName("calendarWeek");


        /*-------------------------------
        add components to root
        --------------------------------*/
        Layout layoutMenu = navigation.getMenu();
        root.addComponents(layoutMenu, labelWelcome, error, buttonAddRes, calendarWeek);
        setCompositionRoot(root);


        /*-------------------------------
        double click on calendar events
        --------------------------------*/
        calendarWeek.setHandler((CalendarComponentEvents.EventClickHandler) event -> {
            addSubWindowToRoot(event);
        });

    }

    private void buttonDelete(Button.ClickEvent event) {

        String id = contentSubWindow.getTextFieldId().getValue();
        int idRes = Integer.parseInt(id);

        try {

            Reservation res = reservationController.findReservation(idRes);
            reservationController.cancelReservation(res);

            CalendarEvent e = contentSubWindow.getEvent();
            calendarWeek.removeEvent(e);

            subWindowReservation.close();

        } catch (Exception e) {
            System.out.println("Exception");
        }


    }


    private void buttonSave(Button.ClickEvent event) {

        try {
            DateField dateStart = contentSubWindow.getDateFieldStart();
            DateField dateEnd = contentSubWindow.getDateFieldEnd();
            String roomName = contentSubWindow.getRooms().getValue().toString();
            String id = contentSubWindow.getTextFieldId().getValue();
            int idRes = Integer.parseInt(id);

            String[] room = roomName.split(" ");
            String roomId = room[0];
            int idR = Integer.parseInt(roomId);

            User u = userController.findUser(this.id);
            Room r = roomController.findRoom(idR);

            reservationController.createReservation(idRes, new TimeSlot(dateStart.getValue(), dateEnd.getValue()), r, u);

            addNewEntryToCalendar(contentSubWindow.getTextFieldId().getValue(), contentSubWindow.getTextFieldName().getValue(),
                   r, new Date(dateStart.getValue().getTime()), new Date(dateEnd.getValue().getTime()));


        } catch (Exception e) {
            error.setValue("Sorry this room ins't available or you haven't filled all fields");
        }


        subWindowReservation.close();


    }

    //click button in root layout to add new reservation
    private void buttonAddReservation(Button.ClickEvent event) {
        error.setValue("");
        addSubWindowToRoot(event);
    }

    //happens when enter the page
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        //get username from session
        String username = String.valueOf(getSession().getAttribute("username"));
        this.id = (int) getSession().getAttribute("id");
        labelWelcome.setValue("Your Reservations " + username + ":");

        updateEvents();
    }

    //add new entry to calendar view
    public void addNewEntryToCalendar(String id, String name, Room r, Date start, Date end) {
        BasicEvent e = new BasicEvent(id, name, start, end);
        contentSubWindow.getRooms().setValue(r.getId() + " " + r.getName());
        calendarWeek.addEvent(e);
    }


    public void addSubWindowToRoot(Event event) {

        if (event.getComponent().getClass().equals(Calendar.class)) {
            //Calendar event click
            CalendarComponentEvents.EventClick e = (CalendarComponentEvents.EventClick) event;
            contentSubWindow.setEvent(e.getCalendarEvent());
            contentSubWindow.getTextFieldId().setValue(e.getCalendarEvent().getCaption());
            contentSubWindow.getTextFieldName().setValue(e.getCalendarEvent().getDescription());
            contentSubWindow.getRooms().setValue("Room1");  //TODO handle with rooms
            contentSubWindow.getDateFieldStart().setValue(e.getCalendarEvent().getStart());
            contentSubWindow.getDateFieldEnd().setValue(e.getCalendarEvent().getEnd());
        }

        if (event.getComponent().getClass().equals(Button.class)) {
            //Button click
            contentSubWindow.getTextFieldId().setValue("");
            contentSubWindow.getTextFieldName().setValue("");
            contentSubWindow.getRooms().setValue("");
            contentSubWindow.getDateFieldStart().setValue(null);
            contentSubWindow.getDateFieldEnd().setValue(null);
        }

        subWindowReservation.setContent(contentSubWindow.getLayout());
        subWindowReservation.center();

        UI ui = UI.getCurrent();
        ui.addWindow(subWindowReservation);
    }

    public void updateEvents() {

        List<CalendarEvent> event = calendarWeek.getEvents(calendarWeek.getStartDate(), calendarWeek.getEndDate());
        for (CalendarEvent cal : event) {
            calendarWeek.removeEvent(cal);
        }

        List<BasicEvent> e = getEvents();
        for (BasicEvent ev : e) {
            int id = Integer.parseInt(ev.getCaption());
            Reservation r = reservationController.findReservation(id);
            contentSubWindow.getRooms().setValue(r.getRoom().getId() + " " + r.getRoom().getName());
            calendarWeek.addEvent(ev);
        }

    }


    public List<BasicEvent> getEvents() {
        List<BasicEvent> e = new ArrayList<>();
        User user = userController.findUser(this.id);
        List<Reservation> res = reservationController.findReservationsOfUser(user);
        for (Reservation r : res) {
            e.add(new BasicEvent(r.getId()+"", r.getOwner().getName(), r.getTimeSlot().getStart(), r.getTimeSlot().getEnd()));
        }
        return e;
    }


}
