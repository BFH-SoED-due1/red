/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.ui.views;

import ch.bfh.ti.soed.hs16.srs.red.service.UserController;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

/**
 * Created by tambur on 04.12.2016.
 */
public class LoginView extends CustomComponent implements View {

    private Button loginButton;
    private TextField loginName;
    private PasswordField passwordField;
    private GridLayout layout;
    private Panel root;
    private UserController userController;
    private Label error;

    public LoginView(Navigator nav) {

        /*---------------------------------
        initalize Objects
        ---------------------------------*/
        this.loginButton = new Button("Login");
        this.loginName = new TextField("username");
        this.passwordField = new PasswordField("password");
        this.layout = new GridLayout(6,6);
        this.root = new Panel();
        //this.userController = new UserController();
        //TODO: handle with user controller
        this.error = new Label();

        /*-------------------------------
        add to css
        -------------------------------*/
        loginButton.setStyleName("buttonLogin");
        error.setPrimaryStyleName("labelLogin");


        /*-------------------------------
        add Components to Layout
        --------------------------------*/
        layout.addComponent(loginName, 2,0,3,0);
        layout.addComponent(passwordField, 2,1,3,1);
        layout.addComponent(loginButton, 2,2,3,2);
        layout.addComponent(error, 2,3,3,3);

        root.setContent(layout);
        root.setSizeFull();
        setCompositionRoot(root);



        loginButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                //TODO: handle Login
                nav.navigateTo("overview");


            }
        });
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
