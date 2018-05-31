package com.marianni.mobileguide.adminui.employee.popup.forms.voips;

import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import com.marianni.mobileguide.interfaces.dto.VoipDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
/**
 * @author mariannarachelova
 */
public class VoipComponent extends VerticalLayout {
    private VoipForm form;
    private VoipGrid grid;
    private Button createVoip;
    private EmployeeDTO employeeDTO;
    private VoipDTO voipDto;

    public VoipComponent() {
        form = new VoipForm();
        grid = new VoipGrid(form);
        createVoip = new Button("Create Voip");
        HorizontalLayout horizontal = new HorizontalLayout(form, createVoip);
        addComponents(grid, horizontal);
        createVoip.addClickListener(e -> {
            VoipDTO newVoipDto = RestClients.employee().createVoip(form.save());
            this.employeeDTO.getVoips().add(newVoipDto);
            refreshVoipCreateForm();
            grid.refresh(this.employeeDTO.getVoips());
        });

    }

    private VoipDTO createEmptyVoipDto() {
        VoipDTO voipDto = new VoipDTO();
        voipDto.setEmployeeId(employeeDTO.getId());
        return voipDto;
    }

    public void save(){
        form.save();
    }

    public void refresh(final EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
        refreshVoipCreateForm();
        grid.refresh(this.employeeDTO.getVoips());
    }

    private void refreshVoipCreateForm(){
        this.voipDto = createEmptyVoipDto();
        form.refresh(voipDto);
    }

}