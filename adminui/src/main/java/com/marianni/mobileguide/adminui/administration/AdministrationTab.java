package com.marianni.mobileguide.adminui.administration;

import com.marianni.mobileguide.interfaces.endpoints.TimerEndPoint;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;

import javax.inject.Inject;

public class AdministrationTab extends HorizontalLayout{

    private Button buttonTimer;

        /*
    @Inject
    private TimerEndPoint endpoint;
        */

    public AdministrationTab(){

        buttonTimer = new Button("Start update");
        buttonTimer.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        addComponent(buttonTimer);

        buttonTimer.addClickListener((Button.ClickEvent e) ->{
            //zavolat setter - urobit setter ako som spravila getter v servise, ednpoint,endpointimpl

            if(RestClients.timer().isRunningUpdate()){
                RestClients.timer().setTimerValue(false);
                //endpoint.setTimerValue(false);
                buttonTimer.setCaption("Start update");
                buttonTimer.addStyleName(ValoTheme.BUTTON_FRIENDLY);
                Notification.show("Automatic update is stopped");
            }
            else{
                RestClients.timer().setTimerValue(true);
                //endpoint.setTimerValue(true);
                buttonTimer.setCaption("Stop update");
                buttonTimer.addStyleName(ValoTheme.BUTTON_DANGER);
                Notification.show("Automatic update is running");
            }
            //do podmienky ak isRunning true tak taky napis inak iny napis, treba DTO pre timer
        });
    }


}
