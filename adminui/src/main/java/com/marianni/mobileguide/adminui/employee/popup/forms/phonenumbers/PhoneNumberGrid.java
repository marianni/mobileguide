package com.marianni.mobileguide.adminui.employee.popup.forms.phonenumbers;

import com.marianni.mobileguide.interfaces.dto.PhoneNumberDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.util.Set;

/**
 * @author mariannarachelova
 */

public class PhoneNumberGrid extends VerticalLayout {

    private Grid<PhoneNumberDTO> grid;
    ListDataProvider<PhoneNumberDTO> dataProvider;


    public PhoneNumberGrid(final PhoneNumberForm form) {
        this.grid = new Grid<>(PhoneNumberDTO.class);
        addComponent(grid);
        grid.setSizeFull();
        grid.setColumns("id", "phoneNumber");

//        grid.addColumn("delete").setRenderer(new ButtonRenderer(e-> {
//            Long phoneNumberId = ((PhoneNumberDTO) e.getItem()).getId();
//            RestClients.employee().deletePhoneNumber(phoneNumberId);
//        }   ));

        grid.addColumn(x -> "delete",
                new ButtonRenderer<>(e -> {
                    PhoneNumberDTO phoneNumber = (PhoneNumberDTO) e.getItem();
                    RestClients.employee().deletePhoneNumber(phoneNumber.getId());
                    dataProvider.getItems().remove(phoneNumber);
                    dataProvider.getItems().removeIf(p -> p.getId().equals(phoneNumber.getId()));
                    dataProvider.refreshAll();
                }));

        grid.addItemClickListener(e -> {
                    if (e.getMouseEventDetails().isDoubleClick()) {
                        PhoneNumberEditPopup popup = new PhoneNumberEditPopup();
                        UI.getCurrent().addWindow(popup);
                        PhoneNumberDTO phoneNumberDto = e.getItem();
                        popup.open(phoneNumberDto);
                        popup.addCloseListener(closeEvent -> {
                            dataProvider.refreshAll();
                        });
                    }
                }
        );
        /*
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                // nerob nic
            } else {
                form.refresh(event.getValue());
            }
        });
        */
    }


    public void refresh(final Set<PhoneNumberDTO> phoneNumbers) {
        dataProvider = new ListDataProvider<PhoneNumberDTO>(phoneNumbers);
        grid.setDataProvider(dataProvider);
    }
}
