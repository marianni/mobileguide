package com.marianni.mobileguide.adminui.employee.popup.forms.voips;


import com.marianni.mobileguide.interfaces.dto.VoipDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VoipEditPopup extends Window {
    private static float WIDTH_PERCENTAGE = 0.3F;
    private static float HEIGHT_PERCENTAGE = 0.3F;
    private Button saveAndClose = new Button("Save And Close");
    private VoipForm form;
    private VoipDTO dto;


    public VoipEditPopup() {
        setWidth(Page.getCurrent().getBrowserWindowWidth() * WIDTH_PERCENTAGE, Unit.PIXELS);
        setHeight(Page.getCurrent().getBrowserWindowWidth() * HEIGHT_PERCENTAGE, Unit.PIXELS);
        float x = (Page.getCurrent().getBrowserWindowWidth() - getWidth()) / 2;
        float y = (Page.getCurrent().getBrowserWindowHeight() - getHeight())
                / 2;
        setPositionX((int) x);
        setPositionY((int) y);


        VerticalLayout content = new VerticalLayout();
        form = new VoipForm();
        content.addComponents(form, saveAndClose);
        setContent(content);

        saveAndClose.addClickListener(e -> {
            save();
            this.close();
        });

    }

    private VoipDTO save() {
        VoipDTO dto = form.save();
        VoipDTO updatedVoip = RestClients.employee().updateVoip(dto);
        return updatedVoip;
    }


    public void open(VoipDTO dto) {
        this.dto = dto;
        form.refresh(dto);
        focus();
    }

    public VoipDTO getVoipDto() {
        return dto;
    }

}
