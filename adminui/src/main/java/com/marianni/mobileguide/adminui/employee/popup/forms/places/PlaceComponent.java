package com.marianni.mobileguide.adminui.employee.popup.forms.places;

import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import com.marianni.mobileguide.interfaces.dto.PlaceDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class PlaceComponent extends VerticalLayout {


    private PlaceForm form;
    private PlaceGrid grid;
    private Button createPlace;
    private EmployeeDTO employeeDTO;
    private PlaceDTO placeDto;

    public PlaceComponent() {
        form = new PlaceForm();
        grid = new PlaceGrid(form);

        createPlace = new Button("Create Place");
        HorizontalLayout horizontal = new HorizontalLayout(form, createPlace);
        addComponents(grid, horizontal);
        createPlace.addClickListener(e -> {
            PlaceDTO newPlaceDto = RestClients.employee().createPlace(form.save());
            this.employeeDTO.getPlaces().add(newPlaceDto);
            refreshPlaceCreateForm();
            grid.refresh(this.employeeDTO.getPlaces());
        });

    }

    private PlaceDTO createEmptyPlaceDto() {
        PlaceDTO placeDto = new PlaceDTO();
        placeDto.setEmployeeId(employeeDTO.getId());
        return placeDto;
    }

    public void save(){
        form.save();
    }

    public void refresh(final EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
        refreshPlaceCreateForm();
        grid.refresh(this.employeeDTO.getPlaces());
    }

    private void refreshPlaceCreateForm(){
        this.placeDto = createEmptyPlaceDto();
        form.refresh(placeDto);
    }

}
