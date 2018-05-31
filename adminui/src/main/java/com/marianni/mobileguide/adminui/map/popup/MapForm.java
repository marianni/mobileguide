package com.marianni.mobileguide.adminui.map.popup;

import com.marianni.mobileguide.interfaces.dto.MapDTO;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;

/**
 * @author mariannarachelova
 */

public class MapForm extends FormLayout {

    private TextArea map = new TextArea("Map");

    private Binder<MapDTO> binder = new Binder<>(MapDTO.class);

    public MapForm() {
        map.setSizeFull();
        map.setHeight("700");
        addComponents(map);
        binder.bindInstanceFields(this);
    }

    public void refresh(final MapDTO map){
        binder.setBean(map);
    }

    public MapDTO getMap(){
        return binder.getBean();
    }

    public MapDTO save() {
        try {
            binder.writeBean(binder.getBean());
            return binder.getBean();
        } catch (ValidationException e) {
            Notification.show("Map could not be saved, " +
                    "please check error messages for each field.");
            return null;
        }
    }
}
