package com.marianni.mobileguide.adminui.employee.popup.forms.voips;

import com.marianni.mobileguide.interfaces.dto.VoipDTO;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class VoipForm extends FormLayout {

    private TextField voip = new TextField("Voip");


    private Binder<VoipDTO> binder = new Binder<>(VoipDTO.class);

    public VoipForm() {
        addComponents(voip);

        binder.bindInstanceFields(this);
    }

    public void refresh(final VoipDTO employee) {
        binder.setBean(employee);
    }

    public VoipDTO getVoip() {
        return binder.getBean();
    }

    public VoipDTO save() {
        try {
            binder.writeBean(binder.getBean());
            return binder.getBean();
        } catch (ValidationException e) {
            Notification.show("Voip could not be saved, " +
                    "please check error messages for each field.");
            return null;
        }
    }
}
