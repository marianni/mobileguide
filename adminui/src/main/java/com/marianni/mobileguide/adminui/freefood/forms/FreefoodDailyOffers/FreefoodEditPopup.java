package com.marianni.mobileguide.adminui.freefood.forms.FreefoodDailyOffers;

import com.marianni.mobileguide.interfaces.dto.CanteenDailyOfferDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;



public class FreefoodEditPopup extends Window {

    private static float WIDTH_PERCENTAGE = 0.3F;
    private static float HEIGHT_PERCENTAGE = 0.3F;
    private Button saveAndClose = new Button("Save And Close");
    private FreefoodDailyOfferForm form;
    private CanteenDailyOfferDTO dto;

    public FreefoodEditPopup(){
        setWidth(Page.getCurrent().getBrowserWindowWidth() * WIDTH_PERCENTAGE, Unit.PIXELS);
        setHeight(Page.getCurrent().getBrowserWindowWidth() * HEIGHT_PERCENTAGE, Unit.PIXELS);
        float x = (Page.getCurrent().getBrowserWindowWidth() - getWidth()) / 2;
        float y = (Page.getCurrent().getBrowserWindowHeight() - getHeight())
                / 2;
        setPositionX((int) x);
        setPositionY((int) y);


        VerticalLayout content = new VerticalLayout();
        form = new FreefoodDailyOfferForm();
        content.addComponents(form, saveAndClose);
        setContent(content);

        saveAndClose.addClickListener(e -> {
            save();
            this.close();
        });
    }

    private CanteenDailyOfferDTO save() {
        CanteenDailyOfferDTO dto = form.save();
        CanteenDailyOfferDTO updatedDailyOffer= RestClients.canteen().updateDailyOffer(dto);
        return updatedDailyOffer;
    }

    public CanteenDailyOfferDTO getDailyOfferDto() {
        return dto;
    }

    public void open(CanteenDailyOfferDTO dto) {
        this.dto = dto;
        form.refresh(dto);
        focus();
    }
}
