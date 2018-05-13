package com.marianni.mobileguide.adminui.employee.popup.forms;

import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import com.marianni.mobileguide.interfaces.dto.PlaceDTO;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;


public class EmployeeGeneralForm extends FormLayout {

    private TextField nameAndTitle = new TextField("nameAndTitle");
    private TextField relationName = new TextField("relationName");
    private TextField relationPosition = new TextField("relationPosition");



    private Binder<EmployeeDTO> binder = new Binder<>(EmployeeDTO.class);

    public EmployeeGeneralForm() {
        addComponents(nameAndTitle, relationName, relationPosition);
        binder.bindInstanceFields(this);
    }

    public void refresh(final EmployeeDTO employee){
        binder.setBean(employee);
    }

    public EmployeeDTO getEmployee(){
        return binder.getBean();
    }

    public EmployeeDTO save() {
        try {
            binder.writeBean(binder.getBean());
            return binder.getBean();
        } catch (ValidationException e) {
            Notification.show("Employee General Info could not be saved, " +
                    "please check error messages for each field.");
            return null;
        }
    }

}
