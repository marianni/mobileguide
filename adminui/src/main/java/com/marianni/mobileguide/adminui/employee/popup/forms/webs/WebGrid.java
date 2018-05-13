package com.marianni.mobileguide.adminui.employee.popup.forms.webs;

import com.marianni.mobileguide.interfaces.dto.WebDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.util.Set;

public class WebGrid extends VerticalLayout{

    private Grid<WebDTO> grid;
    ListDataProvider<WebDTO> dataProvider;

    public WebGrid(final WebForm form) {
        this.grid = new Grid<>(WebDTO.class);
        addComponent(grid);
        grid.setSizeFull();
        grid.setColumns("id", "web");

        grid.addColumn(x -> "delete",
                new ButtonRenderer<>(e -> {
                    WebDTO web = (WebDTO) e.getItem();
                    RestClients.employee().deleteWeb(web.getId());
                    dataProvider.getItems().remove(web);
                    dataProvider.refreshAll();
                }));

        grid.addItemClickListener(e -> {
                    if (e.getMouseEventDetails().isDoubleClick()) {
                        WebEditPopup popup = new WebEditPopup();
                        UI.getCurrent().addWindow(popup);
                        WebDTO webDto = e.getItem();
                        popup.open(webDto);
                        popup.addCloseListener(closeEvent -> {
                            dataProvider.getItems().remove(webDto);
                            dataProvider.getItems().add(popup.getWebDto());
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

    public void refresh(final Set<WebDTO> webs) {
        dataProvider =  new ListDataProvider<WebDTO>(webs);
        grid.setDataProvider(dataProvider);
    }
}
