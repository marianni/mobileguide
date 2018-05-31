package com.marianni.mobileguide.adminui.employee.popup.forms.webs;

import com.marianni.mobileguide.interfaces.dto.WebDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
/**
 * @author mariannarachelova
 */
public class WebEditPopup extends Window {

    private static float WIDTH_PERCENTAGE = 0.3F;
    private static float HEIGHT_PERCENTAGE = 0.3F;
    private Button saveAndClose = new Button("Save And Close");
    private WebForm form;
    private WebDTO dto;


    public WebEditPopup() {
        setWidth(Page.getCurrent().getBrowserWindowWidth() * WIDTH_PERCENTAGE, Unit.PIXELS);
        setHeight(Page.getCurrent().getBrowserWindowWidth() * HEIGHT_PERCENTAGE, Unit.PIXELS);
        float x = (Page.getCurrent().getBrowserWindowWidth() - getWidth()) / 2;
        float y = (Page.getCurrent().getBrowserWindowHeight() - getHeight())
                / 2;
        setPositionX((int) x);
        setPositionY((int) y);


        VerticalLayout content = new VerticalLayout();
        form = new WebForm();
        content.addComponents(form, saveAndClose);
        setContent(content);

        saveAndClose.addClickListener(e -> {
            save();
            this.close();
        });

    }

    private WebDTO save() {
        WebDTO dto = form.save();
        WebDTO updatedWeb = RestClients.employee().updateWeb(dto);
        return updatedWeb;
    }


    public void open(WebDTO dto) {
        this.dto = dto;
        form.refresh(dto);
        focus();
    }

    public WebDTO getWebDto() {
        return dto;
    }

}
