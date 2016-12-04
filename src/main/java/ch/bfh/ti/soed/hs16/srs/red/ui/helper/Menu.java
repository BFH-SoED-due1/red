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
    private Button overviewButton;
    private Button myReservationButton;
    private Button roomsButton;
    private Navigator navigator;
    private String CLASSNAME = "menu";

    public Menu(Navigator nav) {

        /*---------------------------------
        initalize Objects
        ---------------------------------*/
        this.navigator = nav;
        this.menu = new HorizontalLayout();
        this.overviewButton = new Button("overview");
        this.myReservationButton = new Button("my reservation");
        this.roomsButton = new Button("rooms");

        /*-------------------------------
        add to css
        -------------------------------*/
        menu.setPrimaryStyleName(CLASSNAME);
        overviewButton.setPrimaryStyleName(CLASSNAME + "-button");
        myReservationButton.setPrimaryStyleName(CLASSNAME + "-button");
        roomsButton.setPrimaryStyleName(CLASSNAME + "-button");

        /*-------------------------------
        add Buttons to Layout
        --------------------------------*/
        menu.addComponent(overviewButton);
        menu.addComponent(myReservationButton);
        menu.addComponent(roomsButton);

        /*------------------------------
        Event Handling Buttons
        ------------------------------*/
        overviewButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                navigator.navigateTo("overview");
            }
        });
        myReservationButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                navigator.navigateTo("my Reservation");
            }
        });
        roomsButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                navigator.navigateTo("rooms");
            }
        });

    }

    public Layout getMenu() {
        return menu;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
