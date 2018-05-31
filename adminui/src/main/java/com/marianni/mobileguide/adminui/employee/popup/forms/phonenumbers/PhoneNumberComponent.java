package com.marianni.mobileguide.adminui.employee.popup.forms.phonenumbers;

import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import com.marianni.mobileguide.interfaces.dto.PhoneNumberDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author mariannarachelova
 */

public class PhoneNumberComponent extends VerticalLayout {

    private PhoneNumberForm form;
    private PhoneNumberGrid grid;
    private Button createPhoneNumber;
    private EmployeeDTO employeeDTO;
    private PhoneNumberDTO phoneNumberDto;


    public PhoneNumberComponent(){
        form = new PhoneNumberForm();
        grid = new PhoneNumberGrid(form);
        createPhoneNumber = new Button("Create Phone Number");
        HorizontalLayout horizontal = new HorizontalLayout(form, createPhoneNumber);
        addComponents(grid, horizontal);
        createPhoneNumber.addClickListener(e -> {
            PhoneNumberDTO newPhoneNumberDto = RestClients.employee().createPhoneNumber(form.save());
            this.employeeDTO.getPhoneNumbers().add(newPhoneNumberDto);
            refreshPhoneNumberCreateForm();
            grid.refresh(this.employeeDTO.getPhoneNumbers());
        });

    }

    private PhoneNumberDTO createEmptyPhoneNumberDto() {
        PhoneNumberDTO phoneNumberDto = new PhoneNumberDTO();
        phoneNumberDto.setEmployeeId(employeeDTO.getId());
        return phoneNumberDto;
    }

    public void save(){
        form.save();
    }

    public void refresh(final EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
        refreshPhoneNumberCreateForm();
        grid.refresh(this.employeeDTO.getPhoneNumbers());
    }

    private void refreshPhoneNumberCreateForm(){
        this.phoneNumberDto = createEmptyPhoneNumberDto();
        form.refresh(phoneNumberDto);
    }

}
