package com.marianni.mobileguide.adminui.employee.popup.forms.webs;

import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import com.marianni.mobileguide.interfaces.dto.WebDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class WebComponent extends VerticalLayout {

    private WebForm form;
    private WebGrid grid;
    private Button createWeb;
    private EmployeeDTO employeeDTO;
    private WebDTO webDto;

    public WebComponent() {
        form = new WebForm();
        grid = new WebGrid(form);
        createWeb = new Button("Create Web");
        HorizontalLayout horizontal = new HorizontalLayout(form, createWeb);
        addComponents(grid, horizontal);
        createWeb.addClickListener(e -> {
            WebDTO newWebDto = RestClients.employee().createWeb(form.save());
            this.employeeDTO.getWebs().add(newWebDto);
            refreshWebCreateForm();
            grid.refresh(this.employeeDTO.getWebs());
        });

    }

    private WebDTO createEmptyWebDto() {
        WebDTO webDto = new WebDTO();
        webDto.setEmployeeId(employeeDTO.getId());
        return webDto;
    }

    public void save(){
        form.save();
    }

    public void refresh(final EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
        refreshWebCreateForm();
        grid.refresh(this.employeeDTO.getWebs());
    }

    private void refreshWebCreateForm(){
        this.webDto = createEmptyWebDto();
        form.refresh(webDto);
    }

}
