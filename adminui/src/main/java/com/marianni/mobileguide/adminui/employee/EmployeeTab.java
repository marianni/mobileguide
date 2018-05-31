package com.marianni.mobileguide.adminui.employee;

import com.marianni.mobileguide.adminui.employee.popup.EmployeeCreatePopup;
import com.marianni.mobileguide.adminui.employee.popup.forms.EmployeeGeneralForm;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
/**
 * @author mariannarachelova
 */
public class EmployeeTab extends VerticalLayout {


    private EmployeeGrid grid;
    private Button create;

    public EmployeeTab() {
        grid = new EmployeeGrid();
        grid.setSizeFull();

        create = new Button("Create employee");
        addComponents(grid, create);

        create.addClickListener(e -> {
                    EmployeeCreatePopup popup = new EmployeeCreatePopup();
                    UI.getCurrent().addWindow(popup);
                    popup.open();
                    popup.addCloseListener(closeEvent -> refresh());
                }
        );
    }


    public void refresh() {
        grid.refresh(RestClients.employee().getEmployees());

    }
}
