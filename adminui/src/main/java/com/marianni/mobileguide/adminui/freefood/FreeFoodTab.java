package com.marianni.mobileguide.adminui.freefood;

import com.marianni.mobileguide.adminui.freefood.forms.FreefoodForm;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class FreeFoodTab extends VerticalLayout {


    private FreefoodGrid grid;
    private Button create;

    public FreeFoodTab(){
        grid = new FreefoodGrid();
        grid.setSizeFull();

        create = new Button("Create canteen");
        addComponents(grid, create);

        create.addClickListener(e -> {
                    CanteenCreatePopup popup = new CanteenCreatePopup();
                    UI.getCurrent().addWindow(popup);
                    popup.open();
                    popup.addCloseListener(closeEvent -> refresh());
                }
        );
    }

    public void refresh(){
        grid.refresh(RestClients.canteen().getAllCanteens());
    }
}
