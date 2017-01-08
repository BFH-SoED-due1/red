/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.ui.helper;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

/**
 * Created by tambur on 04.12.2016.
 */
public class Menu extends CustomComponent implements View {


    private Layout menu;
    private Button myReservationButton;
    private Navigator navigator;
    private String CLASSNAME = "menu";

    public Menu(Navigator nav) {

        /*---------------------------------
        initalize Objects
        ---------------------------------*/
        this.navigator = nav;
        this.menu = new HorizontalLayout();
        this.myReservationButton = new Button("my reservation");

        /*-------------------------------
        add to css
        -------------------------------*/
        menu.setPrimaryStyleName(CLASSNAME);
        myReservationButton.setPrimaryStyleName(CLASSNAME + "-button");

        /*-------------------------------
        add Buttons to Layout
        --------------------------------*/
        menu.addComponent(myReservationButton);

        /*------------------------------
        Event Handling Buttons
        ------------------------------*/
        myReservationButton.addClickListener((Button.ClickListener) event -> navigator.navigateTo("my Reservation"));

    }

    public Layout getMenu() {
        return menu;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
