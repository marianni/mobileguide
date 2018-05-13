package com.marianni.mobileguide.adminui.freefood;

import com.marianni.mobileguide.adminui.freefood.forms.FreefoodDailyOffers.FreefoodDailyOfferComponent;
import com.marianni.mobileguide.adminui.freefood.forms.FreefoodForm;
import com.marianni.mobileguide.interfaces.dto.CanteenDTO;
import com.vaadin.ui.TabSheet;

public class CanteenPopupTabsheet extends TabSheet {

    private FreefoodForm generalForm;
    private FreefoodDailyOfferComponent dailyOfferComponent;
    private CanteenDTO canteen;


    private TabSheet.Tab generalTab;
    private TabSheet.Tab dailyOfferTab;



    public CanteenPopupTabsheet() {
        generalForm = new FreefoodForm();
        generalTab = addTab(generalForm, "General Info");

        dailyOfferComponent = new FreefoodDailyOfferComponent();
        dailyOfferTab = addTab(dailyOfferComponent, "Daily offers");

    }

    public void setVisibleTabsExceptGeneral(boolean isVisible){
        dailyOfferTab.setVisible(isVisible);
    }


    public void refresh(final CanteenDTO canteen) {
        this.canteen = canteen;
        generalForm.refresh(this.canteen);
        dailyOfferComponent.refresh(this.canteen);
    }

    public void saveAll() {
        generalForm.save();
        dailyOfferComponent.save();
    }

}
