package com.marianni.mobileguide.adminui.employee.popup.forms.publications;

import com.marianni.mobileguide.interfaces.dto.PlaceDTO;
import com.marianni.mobileguide.interfaces.dto.PublicationDTO;
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
public class PublicationGrid extends VerticalLayout {

    private Grid<PublicationDTO> grid;
    ListDataProvider<PublicationDTO> dataProvider;

    public PublicationGrid(final PublicationForm form) {
        this.grid = new Grid<>(PublicationDTO.class);
        addComponent(grid);
        grid.setSizeFull();
        grid.setColumns("id", "publicationLink");

        grid.addColumn(x -> "delete",
                new ButtonRenderer<>(e -> {
                    PublicationDTO publication = (PublicationDTO) e.getItem();
                    RestClients.employee().deletePublication(publication.getId());
                    dataProvider.getItems().remove(publication);
                    dataProvider.refreshAll();
                }));

        grid.addItemClickListener(e -> {
                    if (e.getMouseEventDetails().isDoubleClick()) {
                        PublicationEditPopup popup = new PublicationEditPopup();
                        UI.getCurrent().addWindow(popup);
                        PublicationDTO publicationDto = e.getItem();
                        popup.open(publicationDto);
                        popup.addCloseListener(closeEvent -> {
                            dataProvider.getItems().remove(publicationDto);
                            dataProvider.getItems().add(popup.getPublicationDto());
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

    public void refresh(final Set<PublicationDTO> publications) {
        dataProvider =  new ListDataProvider<PublicationDTO>(publications);
        grid.setDataProvider(dataProvider);
    }
}
