package com.marianni.mobileguide.adminui.freefood.forms;

import com.marianni.mobileguide.interfaces.dto.CanteenDTO;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
/**
 * @author mariannarachelova
 */
public class FreefoodForm extends FormLayout{

    private TextField name = new TextField("Name");


    private Button save = new Button("Save");
    private Button delete = new Button ("Delete");


    private Binder<CanteenDTO> binder = new Binder<>(CanteenDTO.class);

    public FreefoodForm(){
        addComponents(name);
        binder.bindInstanceFields(this);
    }

    public void refresh(final CanteenDTO canteen) {
        binder.setBean(canteen);
    }

    public CanteenDTO getCanteen(){
        return binder.getBean();
    }



    public CanteenDTO save() {
        try {
            binder.writeBean(binder.getBean());
            return binder.getBean();
        } catch (ValidationException e) {
            Notification.show("Canteen General Info could not be saved, " +
                    "please check error messages for each field.");
            return null;
        }
    }
}
