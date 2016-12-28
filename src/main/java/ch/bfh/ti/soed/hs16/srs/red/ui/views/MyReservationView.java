/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.ui.views;

//TODO imports
import ch.bfh.ti.soed.hs16.srs.red.data.Reservation;
import ch.bfh.ti.soed.hs16.srs.red.service.ReservationController;
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
    private Calendar calendarWeek;
    private Button buttonAddRes;

    //--------- sub Window---------
    private SubWindowReservation contentSubWindow;
    private Button saveButton;
    private Button deleteButton;

    //--------- controller ---------
    private ReservationController reservationController;



    public MyReservationView(Navigator nav) {

        /*---------------------------------
        initialize objects
        ---------------------------------*/
        this.root = new VerticalLayout();
        this.subWindowReservation = new Window();

        this.navigation = new Menu(nav);
        this.labelWelcome = new Label();
        this.calendarWeek = new Calendar();
        this.buttonAddRes = new Button("add Reservation", this::buttonAddReservation);  //handle event in method addReservation
        this.contentSubWindow = new SubWindowReservation();
        this.saveButton = contentSubWindow.getButtonSave();
        this.deleteButton = contentSubWindow.getButtonDelete();


        this.reservationController = new ReservationController();

        /*---------------------------------
        specialize objects
        ---------------------------------*/
        calendarWeek.setFirstDayOfWeek(GregorianCalendar.MONDAY);
        saveButton.addClickListener(this::buttonSave);
        deleteButton.addClickListener(this::buttonDelete);
        contentSubWindow.getTextFieldId().setEnabled(false);  //nobody can change value

        /*---------------------------------
        add style names
        ---------------------------------*/
        calendarWeek.setPrimaryStyleName("calendarWeek");


        /*-------------------------------
        add components to root
        --------------------------------*/
        Layout layoutMenu = navigation.getMenu();
        root.addComponents(layoutMenu, labelWelcome, buttonAddRes, calendarWeek);
        setCompositionRoot(root);


        /*-------------------------------
        double click on calendar events
        --------------------------------*/
        calendarWeek.setHandler((CalendarComponentEvents.EventClickHandler) event -> {
                addSubWindowToRoot(event);
        });

    }

    private void buttonDelete(Button.ClickEvent event) {
        //TODO delete from database and from calendar
        CalendarEvent e = contentSubWindow.getEvent();
        calendarWeek.removeEvent(e);

        subWindowReservation.close();
    }


    private void buttonSave(Button.ClickEvent event) {
        //TODO Event to database and to calendar
        DateField dateStart = contentSubWindow.getDateFieldStart();
        DateField dateEnd = contentSubWindow.getDateFieldEnd();

        addNewEntryToCalendar(contentSubWindow.getTextFieldId().getValue(), contentSubWindow.getTextFieldName().getValue(),
                new Date(dateStart.getValue().getTime()), new Date(dateEnd.getValue().getTime()));

        subWindowReservation.close();
    }

    //click button in root layout to add new reservation
    private void buttonAddReservation(Button.ClickEvent event) {
        addSubWindowToRoot(event);
    }

    //happens when enter the page
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        updateEvents();

        //get username from session
        String username = String.valueOf(getSession().getAttribute("username"));
        labelWelcome.setValue("Your Reservations " + username + ":");
    }

    //add new entry to calendar view
    public void addNewEntryToCalendar(String id, String name, Date start, Date end) {
        updateEvents();
        BasicEvent e = new BasicEvent(id, name, start, end);
        calendarWeek.addEvent(e);
    }


    public void addSubWindowToRoot(Event event) {

        if(event.getComponent().getClass().equals(Calendar.class)) {
            //Calendar event click
            CalendarComponentEvents.EventClick e = (CalendarComponentEvents.EventClick) event;
            contentSubWindow.setEvent(e.getCalendarEvent());
            contentSubWindow.getTextFieldId().setValue(e.getCalendarEvent().getCaption());
            contentSubWindow.getTextFieldName().setValue(e.getCalendarEvent().getDescription());
            contentSubWindow.getRooms().setValue("Room1");  //TODO handle with rooms
            contentSubWindow.getDateFieldStart().setValue(e.getCalendarEvent().getStart());
            contentSubWindow.getDateFieldEnd().setValue(e.getCalendarEvent().getEnd());
        }

        if(event.getComponent().getClass().equals(Button.class)) {
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
        for(CalendarEvent cal : event) {
            calendarWeek.removeEvent(cal);
        }

        List<BasicEvent> e = getEvents();
        for(BasicEvent ev : e) {
            calendarWeek.addEvent(ev);
        }

    }


    public List<BasicEvent> getEvents() {
        List<BasicEvent> e = new ArrayList<>();
        List<Reservation> res = reservationController.getAllReservations();
        for(Reservation r : res) {
           e.add(new BasicEvent(r.getOwner().getName(), r.getOwner().getID() + "", r.getTimeSlot().getStart(), r.getTimeSlot().getEnd()));
        }
        return e;
    }

}
