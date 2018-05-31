package com.marianni.mobileguide.adminui.map;

import com.marianni.mobileguide.adminui.map.popup.MapUpdatePopup;
import com.marianni.mobileguide.interfaces.dto.MapDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.util.Set;
/**
 * @author mariannarachelova
 */
public class MapGrid extends VerticalLayout {

    private Grid<MapDTO> grid;
    ListDataProvider<MapDTO> dataProvider;

    public MapGrid() {
        this.grid = new Grid<>(MapDTO.class);
        addComponent(grid);
        grid.setSizeFull();
        grid.setColumns("id", "map");

        grid.addColumn(x -> "delete",
                new ButtonRenderer<>(e -> {
                    MapDTO map = (MapDTO) e.getItem();
                    RestClients.map().delete(map.getId());
                    dataProvider.getItems().remove(map);
                    dataProvider.refreshAll();
                }));



        grid.addItemClickListener(e -> {
                    if (e.getMouseEventDetails().isDoubleClick()) {
                        MapUpdatePopup popup = new MapUpdatePopup();

                        UI.getCurrent().addWindow(popup);

                        popup.open(e.getItem());

                        popup.addCloseListener(f -> {
                            refreshFromDb();
                        });
                    }
                }
        );

    }

    private void refreshFromDb() {
        refresh(RestClients.map().getMaps());
    }

    public void refresh(final Set<MapDTO> maps) {
        dataProvider =  new ListDataProvider<MapDTO>(maps);
        grid.setDataProvider(dataProvider);
        dataProvider.addSortOrder(mapDTO -> mapDTO.getMap(), SortDirection.ASCENDING);
    }

}
