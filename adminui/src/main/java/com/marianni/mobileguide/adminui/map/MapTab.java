package com.marianni.mobileguide.adminui.map;

import com.marianni.mobileguide.adminui.map.popup.MapCreatePopup;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
/**
 * @author mariannarachelova
 */
public class MapTab extends VerticalLayout {

    private MapGrid grid;
    private Button create;


    public MapTab(){
        grid = new MapGrid();
        grid.setSizeFull();

        create = new Button("Create map");
        addComponents(grid, create);

        create.addClickListener(e -> {
                    MapCreatePopup popup = new MapCreatePopup();
                    UI.getCurrent().addWindow(popup);
                    popup.open();
                    popup.addCloseListener(closeEvent -> refresh());
                }
        );
    }







    public void refresh() {
        grid.refresh(RestClients.map().getMaps());

    }
}
