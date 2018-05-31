package com.marianni.mobileguide.adminui.employee.popup.forms.places;

import com.marianni.mobileguide.adminui.employee.popup.EmployeeUpdatePopup;
import com.marianni.mobileguide.interfaces.dto.PhoneNumberDTO;
import com.marianni.mobileguide.interfaces.dto.PlaceDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.util.Set;

/**
 * @author mariannarachelova
 */

public class PlaceGrid extends VerticalLayout {

    private Grid<PlaceDTO> grid;
    ListDataProvider<PlaceDTO> dataProvider;


    public PlaceGrid(final PlaceForm form) {
        this.grid = new Grid<>(PlaceDTO.class);
        addComponent(grid);
        grid.setSizeFull();
        grid.setColumns("id", "place");

        grid.addColumn(x -> "delete",
                new ButtonRenderer<>(e -> {
                    PlaceDTO place = (PlaceDTO) e.getItem();
                    RestClients.employee().deletePlace(place.getId());
                    dataProvider.getItems().remove(place);
                    dataProvider.refreshAll();
                }));


        grid.addItemClickListener(e -> {
                    if (e.getMouseEventDetails().isDoubleClick()) {
                        PlaceEditPopup popup = new PlaceEditPopup();
                        UI.getCurrent().addWindow(popup);
                        PlaceDTO placeDto = e.getItem();
                        popup.open(placeDto);
                        popup.addCloseListener(closeEvent -> {
                            dataProvider.getItems().remove(placeDto);
                            dataProvider.getItems().add(popup.getPlaceDto());
                            dataProvider.refreshAll();
                        });
                    }
                }
        );
    }

    public void refresh(final Set<PlaceDTO> places) {
        dataProvider =  new ListDataProvider<PlaceDTO>(places);
        grid.setDataProvider(dataProvider);
    }
}
