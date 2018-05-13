package com.marianni.mobileguide.adminui.employee;

import com.marianni.mobileguide.adminui.employee.popup.EmployeeUpdatePopup;
import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.util.Set;


public class EmployeeGrid extends VerticalLayout {

    private Grid<EmployeeDTO> grid;
    ListDataProvider<EmployeeDTO> dataProvider;

    public EmployeeGrid() {
        this.grid = new Grid<>(EmployeeDTO.class);
        addComponent(grid);
        grid.setSizeFull();
        grid.setColumns("id", "nameAndTitle", "relationName", "relationPosition");

        grid.addColumn(x -> "delete",
                new ButtonRenderer<>(e -> {
                    EmployeeDTO employee = (EmployeeDTO) e.getItem();
                    RestClients.employee().delete(employee.getId());
                    dataProvider.getItems().remove(employee);
                    dataProvider.refreshAll();
                }));



        grid.addItemClickListener(e -> {
                    if (e.getMouseEventDetails().isDoubleClick()) {
                        EmployeeUpdatePopup popup = new EmployeeUpdatePopup();

                        UI.getCurrent().addWindow(popup);

                        popup.open(e.getItem());
                    }
                }
        );

//        grid.asSingleSelect().addValueChangeListener(event -> {
//            if (event.getValue() == null) {
//                // nerob nic
//            } else {
//                form.refresh(event.getValue());
//            }
//        });
    }

    public void refresh(final Set<EmployeeDTO> employees) {
        dataProvider =  new ListDataProvider<EmployeeDTO>(employees);
        grid.setDataProvider(dataProvider);
        dataProvider.addSortOrder(employeeDTO -> employeeDTO.getNameAndTitle(), SortDirection.ASCENDING);
        //grid.setItems(employees);
    }
}
