package com.marianni.mobileguide.adminui.employee.popup.forms.places;

import com.marianni.mobileguide.interfaces.dto.PlaceDTO;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class PlaceForm extends FormLayout {

    private TextField place = new TextField("Place Name");


    private Binder<PlaceDTO> binder = new Binder<>(PlaceDTO.class);

    public PlaceForm() {
        addComponents(place);

        binder.bindInstanceFields(this);
    }

    public void refresh(final PlaceDTO employee) {
        binder.setBean(employee);
    }

    public PlaceDTO getPlace() {
        return binder.getBean();
    }

    public PlaceDTO save() {
        try {
            binder.writeBean(binder.getBean());
            return binder.getBean();
        } catch (ValidationException e) {
            Notification.show("Place could not be saved, " +
                    "please check error messages for each field.");
            return null;
        }
    }
}