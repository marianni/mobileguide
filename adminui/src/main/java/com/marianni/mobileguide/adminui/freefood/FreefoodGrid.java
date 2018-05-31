package com.marianni.mobileguide.adminui.freefood;

import com.marianni.mobileguide.interfaces.dto.CanteenDTO;
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
public class FreefoodGrid extends VerticalLayout {

    private Grid<CanteenDTO> grid;
    ListDataProvider<CanteenDTO> dataProvider;

    public FreefoodGrid(){
        this.grid = new Grid<>(CanteenDTO.class);
        addComponent(grid);
        grid.setSizeFull();
        grid.setColumns("id","name");

        grid.addColumn(x -> "delete",
                new ButtonRenderer<>(e -> {
                    CanteenDTO canteen = (CanteenDTO) e.getItem();
                    RestClients.canteen().delete(canteen.getId());
                    dataProvider.getItems().remove(canteen);
                    dataProvider.refreshAll();
                }));



        grid.addItemClickListener(e -> {
                    if (e.getMouseEventDetails().isDoubleClick()) {
                        FreefoodUpdatePopup popup = new FreefoodUpdatePopup();

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
        refresh(RestClients.canteen().getAllCanteens());
    }

    public void refresh(final Set<CanteenDTO> canteens) {
        dataProvider =  new ListDataProvider<CanteenDTO>(canteens);
        grid.setDataProvider(dataProvider);
        dataProvider.addSortOrder(canteenDTO -> canteenDTO.getName(), SortDirection.ASCENDING);
//        grid.setItems(canteens);
    }

}
