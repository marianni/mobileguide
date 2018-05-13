package com.marianni.mobileguide.adminui.employee.popup.forms.places;

import com.marianni.mobileguide.interfaces.dto.PlaceDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PlaceEditPopup extends Window {
    private static float WIDTH_PERCENTAGE = 0.3F;
    private static float HEIGHT_PERCENTAGE = 0.3F;
    private Button saveAndClose = new Button("Save And Close");
    private PlaceForm form;
    private PlaceDTO dto;


    public PlaceEditPopup() {
        setWidth(Page.getCurrent().getBrowserWindowWidth() * WIDTH_PERCENTAGE, Unit.PIXELS);
        setHeight(Page.getCurrent().getBrowserWindowWidth() * HEIGHT_PERCENTAGE, Unit.PIXELS);
        float x = (Page.getCurrent().getBrowserWindowWidth() - getWidth()) / 2;
        float y = (Page.getCurrent().getBrowserWindowHeight() - getHeight())
                / 2;
        setPositionX((int) x);
        setPositionY((int) y);


        VerticalLayout content = new VerticalLayout();
        form = new PlaceForm();
        content.addComponents(form, saveAndClose);
        setContent(content);

        saveAndClose.addClickListener(e -> {
            save();
            this.close();
        });

    }

    private PlaceDTO save() {
        PlaceDTO dto = form.save();
        PlaceDTO updatedPlace = RestClients.employee().updatePlace(dto);
        return updatedPlace;
    }


    public void open(PlaceDTO dto) {
        this.dto = dto;
        form.refresh(dto);
        focus();
    }

    public PlaceDTO getPlaceDto() {
        return dto;
    }

}
