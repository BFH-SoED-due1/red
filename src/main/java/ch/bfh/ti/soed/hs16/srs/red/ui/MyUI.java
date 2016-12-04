/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.ui;

import ch.bfh.ti.soed.hs16.srs.red.ui.views.LoginView;
import ch.bfh.ti.soed.hs16.srs.red.ui.views.MyReservationView;
import ch.bfh.ti.soed.hs16.srs.red.ui.views.RoomsView;
import ch.bfh.ti.soed.hs16.srs.red.ui.views.StartView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Label;


import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("mytheme")
public class MyUI extends UI {

    private Navigator navigator;
    public static final String NAME = "";

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        this.navigator = new Navigator(this, this);

        navigator.addView("", new LoginView(navigator));
        navigator.addView("overview", new StartView(navigator));
        navigator.addView("my Reservation", new MyReservationView(navigator));
        navigator.addView("rooms", new RoomsView(navigator));

    }

    @Override
    public Navigator getNavigator() {
        return navigator;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
