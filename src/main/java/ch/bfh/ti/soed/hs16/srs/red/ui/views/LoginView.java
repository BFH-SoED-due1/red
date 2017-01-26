/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.ui.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

//TODO clean imports
import ch.bfh.ti.soed.hs16.srs.red.data.Password;
import ch.bfh.ti.soed.hs16.srs.red.data.User;
import ch.bfh.ti.soed.hs16.srs.red.development.DevDemo;
import ch.bfh.ti.soed.hs16.srs.red.service.UserController;

/**
 * Created by tambur on 04.12.2016.
 */
public class LoginView extends CustomComponent implements View, Button.ClickListener {

	private VerticalLayout vertical;
	private TextField loginName;
	private PasswordField passwordField;
	private Button loginButton;

	private Navigator nav;

	public LoginView(Navigator nav) {

		try {
			// @TODO remove before release
			DevDemo devdemo = new DevDemo();
		} catch (Exception ex) {
			Notification.show("Demo Entries failed to initialize.", ex.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		/*---------------------------------
		initialize Objects
		---------------------------------*/
		this.nav = nav;
		this.vertical = new VerticalLayout();
		this.loginName = new TextField("username");
		this.passwordField = new PasswordField("password");
		this.loginButton = new Button("Login", this);

		/*-------------------------------
		add to css
		-------------------------------*/
		vertical.setPrimaryStyleName("rootLogin");
		loginButton.setStyleName("buttonLogin");

		/*-------------------------------
		add Components to Layout
		--------------------------------*/
		vertical.addComponents(loginName, passwordField, loginButton);
		setCompositionRoot(vertical);
	}

	@Override
	public void buttonClick(Button.ClickEvent clickEvent) {

		// Store the current user in the service session
		Password pw = Password.getInstance();
		UserController uc = new UserController();
		try {
			User u = uc.logIn(loginName.getValue(), passwordField.getValue());
			getSession().setAttribute("username", u.getName());
			getSession().setAttribute("id", u.getID());
			getSession().setAttribute("role", u.getRole());
			// navigate to my reservation view
			nav.navigateTo("my Reservation");
		} catch (Exception ex) {
			Notification.show("Wrong Password or Username.", "Try again.", Notification.Type.WARNING_MESSAGE);
			passwordField.clear();
		}

	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

	}

}
