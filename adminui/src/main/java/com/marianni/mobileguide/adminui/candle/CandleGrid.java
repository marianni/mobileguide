package com.marianni.mobileguide.adminui.candle;

import com.marianni.mobileguide.interfaces.dto.CandlePlaceDTO;
import com.marianni.mobileguide.interfaces.dto.PlaceDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.util.Set;

public class CandleGrid extends VerticalLayout {

    private Grid<CandlePlaceDTO> grid;
    ListDataProvider<CandlePlaceDTO> dataProvider;

    public CandleGrid(){
        this.grid = new Grid<>(CandlePlaceDTO.class);
        addComponent(grid);
        grid.setSizeFull();
        grid.setColumns("id","name");

        grid.addItemClickListener(e -> {
                    if (e.getMouseEventDetails().isDoubleClick()) {
                        LecturePopup popup = new LecturePopup();

                        UI.getCurrent().addWindow(popup);

                        popup.open(e.getItem());
                    }
                }
        );

    }

    public void refresh(final Set<CandlePlaceDTO> candlePlaces) {
        dataProvider =  new ListDataProvider<CandlePlaceDTO>(candlePlaces);
        grid.setDataProvider(dataProvider);
        dataProvider.addSortOrder(candlePlaceDTO -> candlePlaceDTO.getName(), SortDirection.ASCENDING);
        //grid.setItems(candlePlaces);
    }
}
