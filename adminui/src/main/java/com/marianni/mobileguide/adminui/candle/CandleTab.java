package com.marianni.mobileguide.adminui.candle;

import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.ui.HorizontalLayout;

public class CandleTab extends HorizontalLayout {

    private CandleGrid grid;

    public CandleTab(){

        grid = new CandleGrid();
        grid.setSizeFull();

        addComponent(grid);
    }

    public void refresh(){
        grid.refresh(RestClients.candle().getAllPlaces());
    }
}
