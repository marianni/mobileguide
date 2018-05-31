package com.marianni.mobileguide.adminui.freefood;

import com.marianni.mobileguide.interfaces.dto.CanteenDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
/**
 * @author mariannarachelova
 */
public class FreefoodUpdatePopup extends Window{

    FreefoodPopupTabsheet tabSheet;
    private static float WIDTH_PERCENTAGE = 0.7F;
    private static float HEIGHT_PERCENTAGE = 0.5F;
    private Button save = new Button("Save");
    private CanteenDTO dto;


    public FreefoodUpdatePopup() {
        setWidth(Page.getCurrent().getBrowserWindowWidth() * WIDTH_PERCENTAGE, Unit.PIXELS);
        setHeight(Page.getCurrent().getBrowserWindowWidth() * HEIGHT_PERCENTAGE, Unit.PIXELS);
        float x = (Page.getCurrent().getBrowserWindowWidth() - getWidth()) / 2;
        float y = (Page.getCurrent().getBrowserWindowHeight() - getHeight())
                / 2;
        setPositionX((int) x);
        setPositionY((int) y);


        tabSheet = new FreefoodPopupTabsheet();

        VerticalLayout content = new VerticalLayout();
        content.addComponents(tabSheet, save);

        setContent(content);

        save.addClickListener(e -> {
            saveAll();
        });
    }

    private void saveAll() {
        RestClients.canteen().update(dto);
    }


    public void open(CanteenDTO canteenDTO) {
        dto = canteenDTO;
        tabSheet.refresh(dto);
        focus();
    }


}
