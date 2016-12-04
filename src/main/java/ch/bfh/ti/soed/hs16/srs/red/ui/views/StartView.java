package ch.bfh.ti.soed.hs16.srs.red.ui.views;

import ch.bfh.ti.soed.hs16.srs.red.data.Room;
import ch.bfh.ti.soed.hs16.srs.red.jpa.MyRoom;
import ch.bfh.ti.soed.hs16.srs.red.service.RoomController;
import ch.bfh.ti.soed.hs16.srs.red.ui.helper.Menu;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.handler.BasicBackwardHandler;
import com.vaadin.ui.components.calendar.handler.BasicForwardHandler;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tambur on 04.12.2016.
 */
public class StartView extends CustomComponent implements View {


    private Panel root;
    private GridLayout layout;
    private Button buttonReservation;
    private InlineDateField calendarData;
    private Date date;
    private Label dateOfToday;
    private Layout calendarLayout;
    private Navigator nav;
    private Menu menu;
    private RoomController roomController;
    List<Room> rooms;


    private List<Pair<Room, Calendar>> calendarPerRoom = new ArrayList<>();



    public StartView(Navigator nav) {

        /* --------------------------------------------
        Initializes the components of the design
        -------------------------------------------- */
        this.nav = nav;
        this.layout = new GridLayout(6, 6);
        this.root = new Panel();
        this.buttonReservation = new Button("Make Reservation");
        this.calendarData = new InlineDateField();
        this.date = new Date();
        this.calendarData.setValue(date);
        this.dateOfToday = new Label("Overview of: " + calendarData.getValue());
        this.calendarLayout = new HorizontalLayout();
        this.menu = new Menu(this.nav);
        this.roomController = new RoomController();
        this.rooms = roomController.getRooms();


        //----------------löschen wenn datenbank klappt
        if(rooms == null) {
            int i = 3;
            rooms = new ArrayList<>();
            while (i < 9) {
                rooms.add(new MyRoom("Raum", "Building", 4));
                i++;
            }
        }
        //-------------------------------------------

        /* --------------------------------------------
        add Components to root layout
        -------------------------------------------- */
        addCalendarToRoom();
        Layout layoutMenu = menu.getMenu();

        layout.addComponent(dateOfToday, 0, 1, 1, 1);
        layout.addComponent(calendarData, 5, 1, 5, 1);
        layout.addComponent(buttonReservation, 0, 5, 0, 5);
        layout.addComponent(calendarLayout, 0, 2, 5, 4);
        layout.addComponent(layoutMenu, 0, 0, 5, 0);
        
        layout.setWidth(100, Unit.PERCENTAGE);

        root.setContent(layout);
        root.setSizeFull();
        setCompositionRoot(root);

         /* --------------------------------------------
        ChangeListener for InlineDateField
        -------------------------------------------- */
        calendarData.addValueChangeListener(valueChangeEvent -> {

                    for (Pair p : calendarPerRoom) {
                        Calendar calendar = (Calendar) p.getValue();
                        calendar.setStartDate(calendarData.getValue());
                        calendar.setEndDate(calendarData.getValue());
                    }
                    dateOfToday.setValue("Overview of: " + calendarData.getValue());
                }
        );
    }



    private void addCalendarToRoom() {
        for (Room r : rooms) {

            Calendar calendar = new Calendar();
            calendar.setCaption(r.getName()); //name of the room

            if (r != rooms.get(0)) {
                calendar.setStyleName("calendarDayView");
            }

            //-------------------------------------------------------------
            // Listen for clicks on days
            calendar.setHandler(new CalendarComponentEvents.EventClickHandler() {
                private static final long serialVersionUID = 4548304318112120161L;

                public void eventClick(CalendarComponentEvents.EventClick event) {
                    BasicEvent e = (BasicEvent) event.getCalendarEvent();
                    System.out.println("lasdjkf");
                }
            });

            // Listen on clicks on events
            calendar.setHandler(new CalendarComponentEvents.EventClickHandler() {
                @Override
                public void eventClick(CalendarComponentEvents.EventClick event) {
                    // Get event
                    BasicEvent e = (BasicEvent) event.getCalendarEvent();

                    // Show popup

                }
            });

            //---------------------------------------------------------------


            //Handles moving backward in the calendar - not allowed in this view
            calendar.setHandler(new BasicBackwardHandler() {
                @Override
                public void backward(CalendarComponentEvents.BackwardEvent event) { /* super.backward(event); */ }
            });

            //Handles moving forward in the calendar - not allowed in this view
            calendar.setHandler(new BasicForwardHandler() {
                @Override
                public void forward(CalendarComponentEvents.ForwardEvent event) { /*  super.forward(event); */ }
            });

            // Set daily time range
            calendar.setFirstVisibleHourOfDay(9);
            calendar.setLastVisibleHourOfDay(17);

            calendarPerRoom.add(new Pair<>(r, calendar)); //put calendar and room to list

            calendar.setStartDate(calendarData.getValue()); //shows the same date as inline calendar
            calendar.setEndDate(calendarData.getValue()); //shows the same date as inline calendar

            calendar.getEventProvider();

            calendarLayout.addComponent(calendar);
        }
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        //TODO: get username from session
        /*
        // Get the user name from the session
        String username = String.valueOf(getSession().getAttribute("username"));

        // And show the username

        //passiert wenn in diese view gewechselt wird
        //update von calendar
        */
    }


}
