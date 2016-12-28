/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.ui.helper;

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.event.CalendarEvent;


/**
 * Created by scarface on 18.12.2016.
 */
public class SubWindowReservation extends CustomComponent {


    private VerticalLayout layout;

    private Label id;
    private Label name;
    private Label roomTitle;
    private Label startTime;
    private Label endTime;

    private TextField textFieldId;
    private TextField textFieldName;
    private ComboBox rooms;
    private DateField dateFieldStart;
    private DateField dateFieldEnd;
    private Button buttonSave;
    private Button buttonDelete;

    private CalendarEvent event;

    private String CSS_NAME = "subWindow";




    public SubWindowReservation() {
        this.layout = new VerticalLayout();
        this.id = new Label("id");
        this.name = new Label("name");
        this.roomTitle = new Label("select room");
        this.startTime = new Label("start time");
        this.endTime = new Label("end time");

        this.textFieldId = new TextField();
        this.textFieldName = new TextField();
        this.rooms = new ComboBox();
        this.dateFieldStart = new DateField();
        this.dateFieldEnd = new DateField();
        this.buttonSave = new Button("save");
        this.buttonDelete = new Button("delete");

        dateFieldStart.setResolution(Resolution.MINUTE);
        dateFieldEnd.setResolution(Resolution.MINUTE);



        /*---------------------------------
        add objects to horizontal container
        ---------------------------------*/
        HorizontalLayout idContainer = new HorizontalLayout(id, textFieldId);
        HorizontalLayout nameContainer = new HorizontalLayout(name, textFieldName);
        HorizontalLayout roomContainer = new HorizontalLayout(roomTitle, rooms);
        HorizontalLayout startContainer = new HorizontalLayout(startTime, dateFieldStart);
        HorizontalLayout endContainer = new HorizontalLayout(endTime, dateFieldEnd);
        HorizontalLayout buttons = new HorizontalLayout(buttonSave, buttonDelete);


        /*---------------------------------
        add style names
        ---------------------------------*/
        layout.setPrimaryStyleName(CSS_NAME);
        idContainer.setPrimaryStyleName(CSS_NAME + "-horizontalContainer");
        nameContainer.setPrimaryStyleName(CSS_NAME + "-horizontalContainer");
        roomContainer.setPrimaryStyleName(CSS_NAME + "-horizontalContainer");
        startContainer.setPrimaryStyleName(CSS_NAME + "-horizontalContainer");
        endContainer.setPrimaryStyleName(CSS_NAME + "-horizontalContainer");
        buttons.setPrimaryStyleName(CSS_NAME + "-horizontalContainer");


        /*---------------------------------
        add to root layout
        ---------------------------------*/
        this.layout.addComponents(idContainer, nameContainer, roomContainer, startContainer, endContainer, buttons);
    }


    public TextField getTextFieldId() {
        return textFieldId;
    }

    public TextField getTextFieldName() {
        return textFieldName;
    }

    public ComboBox getRooms() {
        return rooms;
    }

    public DateField getDateFieldStart() {
        return dateFieldStart;
    }

    public DateField getDateFieldEnd() {
        return dateFieldEnd;
    }

    public Button getButtonSave() {
        return buttonSave;
    }

    public Button getButtonDelete() {
        return buttonDelete;
    }

    public VerticalLayout getLayout() {
        return layout;
    }

    public CalendarEvent getEvent() {
        return event;
    }

    public void setEvent(CalendarEvent e) {
        this.event = e;
    }

}
