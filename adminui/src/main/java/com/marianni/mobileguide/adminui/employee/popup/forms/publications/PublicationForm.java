package com.marianni.mobileguide.adminui.employee.popup.forms.publications;

import com.marianni.mobileguide.interfaces.dto.PublicationDTO;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class PublicationForm extends FormLayout{

    private TextField publicationLink = new TextField("Publication");


    private Binder<PublicationDTO> binder = new Binder<>(PublicationDTO.class);

    public PublicationForm() {
        addComponents(publicationLink);

        binder.bindInstanceFields(this);
    }

    public void refresh(final PublicationDTO employee) {
        binder.setBean(employee);
    }

    public PublicationDTO getPublicationLink() {
        return binder.getBean();
    }

    public PublicationDTO save() {
        try {
            binder.writeBean(binder.getBean());
            return binder.getBean();
        } catch (ValidationException e) {
            Notification.show("Publication could not be saved, " +
                    "please check error messages for each field.");
            return null;
        }
    }

}
