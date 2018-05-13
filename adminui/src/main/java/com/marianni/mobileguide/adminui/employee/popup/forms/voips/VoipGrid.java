package com.marianni.mobileguide.adminui.employee.popup.forms.voips;

import com.marianni.mobileguide.interfaces.dto.VoipDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.util.Set;

public class VoipGrid extends VerticalLayout {

    private Grid<VoipDTO> grid;
    ListDataProvider<VoipDTO> dataProvider;

    public VoipGrid(final VoipForm form) {
        this.grid = new Grid<>(VoipDTO.class);
        addComponent(grid);
        grid.setSizeFull();
        grid.setColumns("id", "voip");

        grid.addColumn(x -> "delete",
                new ButtonRenderer<>(e -> {
                    VoipDTO voip = (VoipDTO) e.getItem();
                    RestClients.employee().deleteVoip(voip.getId());
                    dataProvider.getItems().remove(voip);
                    dataProvider.refreshAll();
                }));

        grid.addItemClickListener(e -> {
                    if (e.getMouseEventDetails().isDoubleClick()) {
                        VoipEditPopup popup = new VoipEditPopup();
                        UI.getCurrent().addWindow(popup);
                        VoipDTO voipDto = e.getItem();
                        popup.open(voipDto);
                        popup.addCloseListener(closeEvent -> {
                            dataProvider.getItems().remove(voipDto);
                            dataProvider.getItems().add(popup.getVoipDto());
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

    public void refresh(final Set<VoipDTO> voips) {
        dataProvider =  new ListDataProvider<VoipDTO>(voips);
        grid.setDataProvider(dataProvider);
    }
}
