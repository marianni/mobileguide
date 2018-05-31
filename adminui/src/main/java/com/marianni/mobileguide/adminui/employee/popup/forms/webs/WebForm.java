package com.marianni.mobileguide.adminui.employee.popup.forms.webs;


import com.marianni.mobileguide.interfaces.dto.WebDTO;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
/**
 * @author mariannarachelova
 */
public class WebForm extends FormLayout {
    private TextField web = new TextField("Web");


    private Binder<WebDTO> binder = new Binder<>(WebDTO.class);

    public WebForm() {
        addComponents(web);

        binder.bindInstanceFields(this);
    }

    public void refresh(final WebDTO employee) {
        binder.setBean(employee);
    }

    public WebDTO getWeb() {
        return binder.getBean();
    }

    public WebDTO save() {
        try {
            binder.writeBean(binder.getBean());
            return binder.getBean();
        } catch (ValidationException e) {
            Notification.show("Web could not be saved, " +
                    "please check error messages for each field.");
            return null;
        }
    }

}
