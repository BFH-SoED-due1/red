/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.ui.views;

//TODO imports
import ch.bfh.ti.soed.hs16.srs.red.ui.helper.Menu;
import ch.bfh.ti.soed.hs16.srs.red.ui.helper.SubWindowReservation;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.event.BasicEvent;

import java.util.GregorianCalendar;

/**
 * Created by tambur on 04.12.2016.
 */
public class MyReservationView extends CustomComponent implements View {


    //--------- layout -------------
    private VerticalLayout vertical;
    private Window subWindowReservation;

    //--------- content ------------
    private Menu navigation;
    private Label labelWelcome;
    private Calendar calendarWeek;
    private Button buttonAddRes;




    public MyReservationView(Navigator nav) {

        /*---------------------------------
        initialize objects
        ---------------------------------*/
        this.vertical = new VerticalLayout();
        this.subWindowReservation = new Window();

        this.navigation = new Menu(nav);
        this.labelWelcome = new Label();
        this.calendarWeek = new Calendar();
        this.buttonAddRes = new Button();


        /*---------------------------------
        add style names
        ---------------------------------*/
        calendarWeek.setPrimaryStyleName("calendarWeek");

        // Set daily time range
        //calendarWeek.setFirstVisibleHourOfDay(9);
        //calendarWeek.setLastVisibleHourOfDay(22);


        /*------------------------------
        Event handling button
        ------------------------------*/
        buttonAddRes.addClickListener((Button.ClickListener) clickEvent -> {
            //TODO: handle button event open popup
            System.out.println("button clicked");
        });

        /*-------------------------------
        add components to vertical
        --------------------------------*/
        Layout layoutMenu = navigation.getMenu();
        vertical.addComponents(layoutMenu, labelWelcome, buttonAddRes, calendarWeek);
        setCompositionRoot(vertical);



        /*-------------------------------
        handle with calendar
        --------------------------------*/
        calendarWeek.setHandler((CalendarComponentEvents.EventClickHandler) event -> {
            BasicEvent e = (BasicEvent) event.getCalendarEvent();

            //TODO handle new entry
            SubWindowReservation contentSubWindow = new SubWindowReservation();
            subWindowReservation.setContent(contentSubWindow.getLayout());
            subWindowReservation.center();

            UI ui = UI.getCurrent();
            ui.addWindow(subWindowReservation);

        });

    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        //TODO: update view with reservation
        addEntry();
        buttonAddRes.setCaption("add Reservation");

        //username from login TODO handle username
        String username = String.valueOf(getSession().getAttribute("username"));
        labelWelcome.setValue("Your Reservations " + username + ":");

    }


    public void addEntry() {
                //add event to calendar two hour
        GregorianCalendar start = new GregorianCalendar();
        GregorianCalendar end   = new GregorianCalendar();
        end.add(java.util.Calendar.HOUR, 2);
        calendarWeek.addEvent(new BasicEvent("Calendar study",
                "Learning how to use Vaadin Calendar",
                start.getTime(), end.getTime()));
    }


}
