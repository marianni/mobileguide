package com.marianni.mobileguide.adminui.employee.popup;

import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class EmployeeUpdatePopup extends Window {
    EmployeePopupTabsheet tabSheet;
    private static float WIDTH_PERCENTAGE = 0.7F;
    private static float HEIGHT_PERCENTAGE = 0.5F;
    private Button save = new Button("Save");
    private EmployeeDTO dto;


    public EmployeeUpdatePopup() {
        setWidth(Page.getCurrent().getBrowserWindowWidth() * WIDTH_PERCENTAGE, Unit.PIXELS);
        setHeight(Page.getCurrent().getBrowserWindowWidth() * HEIGHT_PERCENTAGE, Unit.PIXELS);
        float x = (Page.getCurrent().getBrowserWindowWidth() - getWidth()) / 2;
        float y = (Page.getCurrent().getBrowserWindowHeight() - getHeight())
                / 2;
        setPositionX((int) x);
        setPositionY((int) y);


        tabSheet = new EmployeePopupTabsheet();

        VerticalLayout content = new VerticalLayout();
        content.addComponents(tabSheet, save);

        setContent(content);

        save.addClickListener(e -> {
            saveAll();
        });
    }

    private void saveAll() {
        RestClients.employee().update(dto);
    }


    public void open(EmployeeDTO employeeDTO) {
        dto = employeeDTO;
        tabSheet.refresh(dto);
        focus();
    }

}
