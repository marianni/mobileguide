package com.marianni.mobileguide.adminui.freefood.forms.FreefoodDailyOffers;

import com.marianni.mobileguide.interfaces.dto.CanteenDailyOfferDTO;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
/**
 * @author mariannarachelova
 */
public class FreefoodDailyOfferForm extends FormLayout{

    private TextField dayAndDate = new TextField("Day and date");
    private TextField dishName = new TextField("Dish name");

    private Binder<CanteenDailyOfferDTO> binder = new Binder<>(CanteenDailyOfferDTO.class);

    public FreefoodDailyOfferForm(){
        addComponent(dayAndDate);
        addComponent(dishName);

        binder.bindInstanceFields(this);
    }

    public void refresh(CanteenDailyOfferDTO value) {
        binder.setBean(value);
    }

    public CanteenDailyOfferDTO getDailyOffer(){
        return binder.getBean();
    }

    public CanteenDailyOfferDTO save() {

        try {
            binder.writeBean(binder.getBean());
            return binder.getBean();
        } catch (ValidationException e) {
            Notification.show("Daily offer could not be saved, " +
                    "please check error messages for each field.");
            return null;
        }
    }
}
