package ch.bfh.ti.soed.hs16.srs.red.ui.helper;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by scarface on 18.12.2016.
 */
public class PopUp extends CustomComponent implements View {


    private Layout layout;

    private Label title;
    private TextField textField;


    public PopUp() {
        this.layout = new VerticalLayout();
        this.title = new Label();
        this.textField = new TextField();

        this.layout.addComponents(title, textField);
    }


    public Layout getLayout() {
        return layout;
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }


}
