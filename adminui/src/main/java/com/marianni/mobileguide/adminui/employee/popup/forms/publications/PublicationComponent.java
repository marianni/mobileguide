package com.marianni.mobileguide.adminui.employee.popup.forms.publications;

import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import com.marianni.mobileguide.interfaces.dto.PublicationDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class PublicationComponent extends VerticalLayout {

    private PublicationForm form;
    private PublicationGrid grid;
    private Button createPublication;
    private EmployeeDTO employeeDTO;
    private PublicationDTO publicationDto;


    public PublicationComponent() {
        form = new PublicationForm();
        grid = new PublicationGrid(form);
        createPublication = new Button("Create Publication");
        HorizontalLayout horizontal = new HorizontalLayout(form, createPublication);
        addComponents(grid, horizontal);
        createPublication.addClickListener(e -> {
            PublicationDTO newPublicationDto = RestClients.employee().createPublication(form.save());
            this.employeeDTO.getPublications().add(newPublicationDto);
            refreshPublicationCreateForm();
            grid.refresh(this.employeeDTO.getPublications());
        });

    }

    private PublicationDTO createEmptyPublicationDto() {
        PublicationDTO publicationDto = new PublicationDTO();
        publicationDto.setEmployeeId(employeeDTO.getId());
        return publicationDto;
    }

    public void save(){
        form.save();
    }

    public void refresh(final EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
        refreshPublicationCreateForm();
        grid.refresh(this.employeeDTO.getPublications());
    }

    private void refreshPublicationCreateForm(){
        this.publicationDto = createEmptyPublicationDto();
        form.refresh(publicationDto);
    }

}
