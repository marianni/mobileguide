package com.marianni.mobileguide.adminui.freefood.forms.FreefoodDailyOffers;

import com.marianni.mobileguide.interfaces.dto.CanteenDTO;
import com.marianni.mobileguide.interfaces.dto.CanteenDailyOfferDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class FreefoodDailyOfferComponent extends VerticalLayout {

    private FreefoodDailyOfferForm form;
    private FreefoodDailyOfferGrid grid;
    private Button createDailyOffer;
    private CanteenDTO canteenDTO;
    private CanteenDailyOfferDTO dailyOfferDTO;


    public FreefoodDailyOfferComponent(){
        form = new FreefoodDailyOfferForm();
        grid = new FreefoodDailyOfferGrid(form);
        createDailyOffer = new Button("Create Daily Offer");
        HorizontalLayout horizontal = new HorizontalLayout(form, createDailyOffer);
        addComponents(grid, horizontal);
        createDailyOffer.addClickListener(e -> {
            CanteenDailyOfferDTO newdailyOfferDto = RestClients.canteen().createDailyOffer(form.save());
            this.canteenDTO.getDailyOffers().add(newdailyOfferDto);
            refreshCanteenDailyOfferCreateForm();
            grid.refresh(this.canteenDTO.getDailyOffers());
        });

    }

    private CanteenDailyOfferDTO createEmptyDailyOfferDto() {
        CanteenDailyOfferDTO dailyOfferDto = new CanteenDailyOfferDTO();
        dailyOfferDto.setCanteenId(canteenDTO.getId());
        return dailyOfferDto;
    }

    public void save(){
        form.save();
    }

    public void refresh(final CanteenDTO canteenDTO) {
        this.canteenDTO = canteenDTO;
        refreshCanteenDailyOfferCreateForm();
        grid.refresh(this.canteenDTO.getDailyOffers());
    }

    private void refreshCanteenDailyOfferCreateForm(){
        this.dailyOfferDTO = createEmptyDailyOfferDto();
        form.refresh(dailyOfferDTO);
    }

}
