package com.marianni.mobileguide.adminui.freefood.forms.FreefoodDailyOffers;

import com.marianni.mobileguide.interfaces.dto.CanteenDailyOfferDTO;
import com.marianni.mobileguide.interfaces.dto.PlaceDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.util.Set;

public class FreefoodDailyOfferGrid extends VerticalLayout {

    private Grid<CanteenDailyOfferDTO> grid;
    ListDataProvider<CanteenDailyOfferDTO> dataProvider;

    public FreefoodDailyOfferGrid(final FreefoodDailyOfferForm form) {
        this.grid = new Grid<>(CanteenDailyOfferDTO.class);
        addComponent(grid);
        grid.setSizeFull();
        grid.setColumns("id", "dayAndDate", "dishName");

        grid.addColumn(x -> "delete",
                new ButtonRenderer<>(e -> {
                    CanteenDailyOfferDTO dailyOffer = (CanteenDailyOfferDTO) e.getItem();
                    RestClients.canteen().deleteDailyOffer(dailyOffer.getId());
                    dataProvider.getItems().remove(dailyOffer);
                    dataProvider.refreshAll();
                }));

        grid.addItemClickListener(e -> {
                    if (e.getMouseEventDetails().isDoubleClick()) {
                        FreefoodEditPopup popup = new FreefoodEditPopup();
                        UI.getCurrent().addWindow(popup);
                        CanteenDailyOfferDTO dailyOfferDto = e.getItem();
                        popup.open(dailyOfferDto);
                        popup.addCloseListener(closeEvent -> {
                            dataProvider.getItems().remove(dailyOfferDto);
                            dataProvider.getItems().add(popup.getDailyOfferDto());
                            dataProvider.refreshAll();
                        });
                    }
                }
        );

        /*
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                // nerob nic
            } else {
                form.refresh(event.getValue());
            }
        });
        */
    }

    public void refresh(final Set<CanteenDailyOfferDTO> dailyOffers) {
        dataProvider =  new ListDataProvider<CanteenDailyOfferDTO>(dailyOffers);
        grid.setDataProvider(dataProvider);
    }
}
