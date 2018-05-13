package com.marianni.mobileguide.adminui.employee.popup.forms.phonenumbers;

import com.marianni.mobileguide.interfaces.dto.PhoneNumberDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PhoneNumberEditPopup extends Window {

    private static float WIDTH_PERCENTAGE = 0.3F;
    private static float HEIGHT_PERCENTAGE = 0.3F;
    private Button saveAndClose = new Button("Save And Close");
    private PhoneNumberForm form;
    private PhoneNumberDTO dto;


    public PhoneNumberEditPopup() {
        setWidth(Page.getCurrent().getBrowserWindowWidth() * WIDTH_PERCENTAGE, Unit.PIXELS);
        setHeight(Page.getCurrent().getBrowserWindowWidth() * HEIGHT_PERCENTAGE, Unit.PIXELS);
        float x = (Page.getCurrent().getBrowserWindowWidth() - getWidth()) / 2;
        float y = (Page.getCurrent().getBrowserWindowHeight() - getHeight())
                / 2;
        setPositionX((int) x);
        setPositionY((int) y);


        VerticalLayout content = new VerticalLayout();
        form = new PhoneNumberForm();
        content.addComponents(form, saveAndClose);
        setContent(content);

        saveAndClose.addClickListener(e -> {
            save();
            this.close();
        });

    }

    private PhoneNumberDTO save() {
        PhoneNumberDTO dto = form.save();
        PhoneNumberDTO updatedPhoneNumber= RestClients.employee().updatePhoneNumber(dto);
        return updatedPhoneNumber;
    }


    public void open(PhoneNumberDTO dto) {
        this.dto = dto;
        form.refresh(dto);
        focus();
    }

    public PhoneNumberDTO getPhoneNumberDto() {
        return dto;
    }
}
