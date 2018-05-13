package com.marianni.mobileguide.adminui.employee.popup;

import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class EmployeeCreatePopup extends Window {
    EmployeePopupTabsheet tabSheet;
    private static float WIDTH_PERCENTAGE = 0.7F;
    private static float HEIGHT_PERCENTAGE = 0.5F;
    private Button save = new Button("Save");
    private Button saveAndClose = new Button("Save And Close");
    private EmployeeDTO dto;


    public EmployeeCreatePopup() {
        setWidth(Page.getCurrent().getBrowserWindowWidth() * WIDTH_PERCENTAGE, Unit.PIXELS);
        setHeight(Page.getCurrent().getBrowserWindowWidth() * HEIGHT_PERCENTAGE, Unit.PIXELS);
        float x = (Page.getCurrent().getBrowserWindowWidth() - getWidth()) / 2;
        float y = (Page.getCurrent().getBrowserWindowHeight() - getHeight())
                / 2;
        setPositionX((int) x);
        setPositionY((int) y);


        tabSheet = new EmployeePopupTabsheet();
        tabSheet.setVisibleTabsExceptGeneral(false);

        VerticalLayout content = new VerticalLayout();
        content.addComponents(tabSheet, save, saveAndClose);

        setContent(content);

        save.addClickListener(e -> {
            save();
        });

        saveAndClose.addClickListener(e -> {
            save();
            this.close();
        });

    }

    private void save() {
        EmployeeDTO createdEmployee = RestClients.employee().create(dto);
        if (createdEmployee != null && createdEmployee.getId() != null){
            tabSheet.setVisibleTabsExceptGeneral(true);
        }
    }


    public void open() {
        dto = new EmployeeDTO();
        tabSheet.refresh(dto);
        focus();
    }

}
