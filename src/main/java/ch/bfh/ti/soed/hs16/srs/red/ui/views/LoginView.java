/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.ui.views;

//TODO clean imports
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by tambur on 04.12.2016.
 */
public class LoginView extends CustomComponent implements View {


    private VerticalLayout vertical;
    private TextField loginName;
    private PasswordField passwordField;
    private Button loginButton;
    private Label error;

    public LoginView(Navigator nav) {

        /*---------------------------------
        initalize Objects
        ---------------------------------*/
        this.vertical = new VerticalLayout();
        this.loginName = new TextField("username");
        this.passwordField = new PasswordField("password");
        this.loginButton = new Button("Login");

        //this.userController = new UserController();
        //TODO: handle with user controller
        this.error = new Label();

        /*-------------------------------
        add to css
        -------------------------------*/
        vertical.setPrimaryStyleName("rootLogin");
        loginButton.setStyleName("buttonLogin");
        error.setPrimaryStyleName("labelLogin");


        /*-------------------------------
        add Components to Layout
        --------------------------------*/
        vertical.addComponents(loginName, passwordField, loginButton, error);
        setCompositionRoot(vertical);


        loginButton.addClickListener((Button.ClickListener) clickEvent -> {
            //TODO: handle Login
            nav.navigateTo("my Reservation");
        });
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
