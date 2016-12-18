/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 * Project Smart Reservation System.
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.red.ui.helper;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;



/**
 * Created by scarface on 18.12.2016.
 */
public class SubWindowReservation extends CustomComponent {


    private VerticalLayout layout;

    private Label title;
    private Label startTime;
    private Label endTime;
    private Label roomTitle;
    private TextField textField;
    private ComboBox rooms;
    private PopupDateField dateFieldStart;
    private DateField dateFieldEnd;
    private TextArea text;
    private Button save;


    public SubWindowReservation() {
        this.layout = new VerticalLayout();
        this.title = new Label("subject");
        this.startTime = new Label("start time");
        this.endTime = new Label("end time");
        this.roomTitle = new Label("select room");
        this.textField = new TextField();
        this.rooms = new ComboBox();

        this.dateFieldStart = new PopupDateField();
        this.dateFieldEnd = new DateField();
        this.text = new TextArea();
        this.save = new Button("save");


        HorizontalLayout subjectContainer = new HorizontalLayout(title, textField);
        HorizontalLayout roomContainer = new HorizontalLayout(roomTitle, rooms);
        HorizontalLayout startContainer = new HorizontalLayout(startTime, dateFieldStart);
        HorizontalLayout endContainer = new HorizontalLayout(endTime, dateFieldEnd);

        this.layout.addComponents(subjectContainer, roomContainer, startContainer, endContainer, text, save);
    }


    public Layout getLayout() {
        return layout;
    }

}
