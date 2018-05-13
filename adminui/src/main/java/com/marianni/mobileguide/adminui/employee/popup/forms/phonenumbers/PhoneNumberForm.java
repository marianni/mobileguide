package com.marianni.mobileguide.adminui.employee.popup.forms.phonenumbers;

import com.marianni.mobileguide.interfaces.dto.PhoneNumberDTO;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class PhoneNumberForm extends FormLayout {
    private TextField phoneNumber = new TextField("Phone number");

    private Binder<PhoneNumberDTO> binder = new Binder<>(PhoneNumberDTO.class);

    public PhoneNumberForm(){
        addComponent(phoneNumber);

        binder.bindInstanceFields(this);

    }

    public void refresh(final PhoneNumberDTO employee){
        binder.setBean(employee);
    }

    public PhoneNumberDTO getPhoneNumber(){
        return binder.getBean();
    }

    public PhoneNumberDTO save(){
        try {
            binder.writeBean(binder.getBean());
            return binder.getBean();
        } catch (ValidationException e) {
            Notification.show("Phone number could not be saved, " +
                    "please check error messages for each field.");
            return null;
        }
    }
}
