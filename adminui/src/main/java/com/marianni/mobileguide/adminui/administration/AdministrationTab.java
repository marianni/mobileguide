package com.marianni.mobileguide.adminui.administration;

import com.marianni.mobileguide.interfaces.dto.TimerDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author mariannarachelova
 */

public class AdministrationTab extends HorizontalLayout {

    private Button buttonTimer;

    public AdministrationTab() {

        buttonTimer = new Button("Start update");
        addComponent(buttonTimer);


        buttonTimer.addClickListener((Button.ClickEvent e) -> {

            if (RestClients.timer().shouldSynchronizeFromExternalSource().getRunning()) {
                TimerDTO dto = new TimerDTO();
                dto.setRunning(false);
                RestClients.timer().setTimerValue(dto);
                Notification.show("Automatic update is stopped");
                refresh();
            } else {
                TimerDTO dto = new TimerDTO();
                dto.setRunning(true);
                RestClients.timer().setTimerValue(dto);
                Notification.show("Automatic update is running");
                refresh();
            }
        });
    }

    public void refresh() {
        Boolean isRunning = RestClients.timer().shouldSynchronizeFromExternalSource().getRunning();
        if (isRunning) {
            renderNotRunning();
        } else {
            renderRunning();
        }
    }

    public void renderRunning() {
        buttonTimer.setCaption("Start update");
        buttonTimer.setStyleName(ValoTheme.BUTTON_FRIENDLY);
    }

    public void renderNotRunning() {
        buttonTimer.setCaption("Stop update");
        buttonTimer.setStyleName(ValoTheme.BUTTON_DANGER);
    }


}
