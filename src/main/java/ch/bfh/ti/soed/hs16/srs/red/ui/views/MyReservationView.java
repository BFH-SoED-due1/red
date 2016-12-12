/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.ui.views;

import ch.bfh.ti.soed.hs16.srs.red.ui.helper.Menu;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;

/**
 * Created by tambur on 04.12.2016.
 */
public class MyReservationView extends CustomComponent implements View {

    Button button;
    GridLayout layout;
    Menu menu;

    public MyReservationView(Navigator nav) {

        /*---------------------------------
        initalize Objects
        ---------------------------------*/
        this.button = new Button();
        this.layout = new GridLayout(6, 6);
        this.menu = new Menu(nav);


        button.setCaption("myReservation");

        /*------------------------------
        Event Handling Button
        ------------------------------*/
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

            }
        });

        /*-------------------------------
        add Components to Layout
        --------------------------------*/
        layout.addComponent(menu.getMenu(), 0, 0, 5, 0);
        layout.addComponent(button, 3, 1, 4, 1);

        setCompositionRoot(layout);
        setSizeFull();

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
