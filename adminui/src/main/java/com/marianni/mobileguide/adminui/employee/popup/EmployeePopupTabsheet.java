package com.marianni.mobileguide.adminui.employee.popup;

import com.marianni.mobileguide.adminui.employee.popup.forms.EmployeeGeneralForm;
import com.marianni.mobileguide.adminui.employee.popup.forms.phonenumbers.PhoneNumberComponent;
import com.marianni.mobileguide.adminui.employee.popup.forms.places.PlaceComponent;
import com.marianni.mobileguide.adminui.employee.popup.forms.publications.PublicationComponent;
import com.marianni.mobileguide.adminui.employee.popup.forms.voips.VoipComponent;
import com.marianni.mobileguide.adminui.employee.popup.forms.webs.WebComponent;
import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import com.vaadin.ui.Button;
import com.vaadin.ui.TabSheet;

public class EmployeePopupTabsheet extends TabSheet {
    private EmployeeGeneralForm generalForm;
    private PlaceComponent placeComponent;
    private PhoneNumberComponent phoneNumberComponent;
    private PublicationComponent publicationComponent;
    private WebComponent webComponent;
    private VoipComponent voipComponent;
    private EmployeeDTO employee;


    private TabSheet.Tab generalTab;
    private TabSheet.Tab placeTab;
    private TabSheet.Tab phoneNumberTab;
    private TabSheet.Tab publicationTab;
    private TabSheet.Tab webTab;
    private TabSheet.Tab voipTab;


    public EmployeePopupTabsheet() {
        generalForm = new EmployeeGeneralForm();
        generalTab = addTab(generalForm, "General Info");

        placeComponent = new PlaceComponent();
        placeTab = addTab(placeComponent, "Places");

        phoneNumberComponent = new PhoneNumberComponent();
        phoneNumberTab = addTab(phoneNumberComponent,"Phone numbers");

        publicationComponent = new PublicationComponent();
        publicationTab = addTab(publicationComponent,"Publications");

        webComponent = new WebComponent();
        webTab = addTab(webComponent,"Webs");

        voipComponent = new VoipComponent();
        voipTab = addTab(voipComponent,"Voips");
    }

    public void setVisibleTabsExceptGeneral(boolean isVisible){
        placeTab.setVisible(isVisible);
        phoneNumberTab.setVisible(isVisible);
        publicationTab.setVisible(isVisible);
        webTab.setVisible(isVisible);
        voipTab.setVisible(isVisible);
    }


    public void refresh(final EmployeeDTO employee) {
        this.employee = employee;
        generalForm.refresh(this.employee);
        placeComponent.refresh(this.employee);
        phoneNumberComponent.refresh(this.employee);
        publicationComponent.refresh(this.employee);
        webComponent.refresh(this.employee);
        voipComponent.refresh(this.employee);
    }

    public void saveAll() {
        generalForm.save();
        placeComponent.save();
        phoneNumberComponent.save();
        publicationComponent.save();
        webComponent.save();
        voipComponent.save();
    }


}
