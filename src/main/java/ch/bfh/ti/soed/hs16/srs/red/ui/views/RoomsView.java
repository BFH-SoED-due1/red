package ch.bfh.ti.soed.hs16.srs.red.ui.views;

import ch.bfh.ti.soed.hs16.srs.red.ui.helper.Menu;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;

/**
 * Created by tambur on 04.12.2016.
 */
public class RoomsView extends CustomComponent implements View {

    private GridLayout layout;
    private Menu menu;

    public RoomsView(Navigator nav) {

        /*---------------------------------
        initalize Objects
        ---------------------------------*/
        this.layout = new GridLayout(6,6);
        this.menu = new Menu(nav);


        /*---------------------------------
        add objects to root layout
        ---------------------------------*/
        layout.addComponent(menu.getMenu(), 0,0,5,0);

        setCompositionRoot(layout);
        setSizeFull();

    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

}
