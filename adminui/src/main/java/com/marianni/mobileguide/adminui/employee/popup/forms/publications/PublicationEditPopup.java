package com.marianni.mobileguide.adminui.employee.popup.forms.publications;

import com.marianni.mobileguide.interfaces.dto.PublicationDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PublicationEditPopup extends Window {

    private static float WIDTH_PERCENTAGE = 0.3F;
    private static float HEIGHT_PERCENTAGE = 0.3F;
    private Button saveAndClose = new Button("Save And Close");
    private PublicationForm form;
    private PublicationDTO dto;


    public PublicationEditPopup() {
        setWidth(Page.getCurrent().getBrowserWindowWidth() * WIDTH_PERCENTAGE, Unit.PIXELS);
        setHeight(Page.getCurrent().getBrowserWindowWidth() * HEIGHT_PERCENTAGE, Unit.PIXELS);
        float x = (Page.getCurrent().getBrowserWindowWidth() - getWidth()) / 2;
        float y = (Page.getCurrent().getBrowserWindowHeight() - getHeight())
                / 2;
        setPositionX((int) x);
        setPositionY((int) y);


        VerticalLayout content = new VerticalLayout();
        form = new PublicationForm();
        content.addComponents(form, saveAndClose);
        setContent(content);

        saveAndClose.addClickListener(e -> {
            save();
            this.close();
        });

    }

    private PublicationDTO save() {
        PublicationDTO dto = form.save();
        PublicationDTO updatedPublication = RestClients.employee().updatePublication(dto);
        return updatedPublication;
    }


    public void open(PublicationDTO dto) {
        this.dto = dto;
        form.refresh(dto);
        focus();
    }

    public PublicationDTO getPublicationDto() {
        return dto;
    }

}
