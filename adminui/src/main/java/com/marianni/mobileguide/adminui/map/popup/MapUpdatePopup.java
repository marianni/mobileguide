package com.marianni.mobileguide.adminui.map.popup;

import com.marianni.mobileguide.interfaces.dto.MapDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
/**
 * @author mariannarachelova
 */
public class MapUpdatePopup extends Window {

    MapPopupTabsheet tabSheet;
    private static float WIDTH_PERCENTAGE = 0.7F;
    private static float HEIGHT_PERCENTAGE = 0.5F;
    private Button save = new Button("Save");
    private MapDTO dto;


    public MapUpdatePopup() {
        setWidth(Page.getCurrent().getBrowserWindowWidth() * WIDTH_PERCENTAGE, Unit.PIXELS);
        setHeight(Page.getCurrent().getBrowserWindowWidth() * HEIGHT_PERCENTAGE, Unit.PIXELS);
        float x = (Page.getCurrent().getBrowserWindowWidth() - getWidth()) / 2;
        float y = (Page.getCurrent().getBrowserWindowHeight() - getHeight())
                / 2;
        setPositionX((int) x);
        setPositionY((int) y);


        tabSheet = new MapPopupTabsheet();

        VerticalLayout content = new VerticalLayout();
        content.addComponents(tabSheet, save);

        setContent(content);

        save.addClickListener(e -> {
            saveAll();
        });
    }

    private void saveAll() {
        RestClients.map().update(dto);
    }


    public void open(MapDTO mapDTO) {
        dto = mapDTO;
        tabSheet.refresh(dto);
        focus();
    }

}
