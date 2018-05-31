package com.marianni.mobileguide.adminui;


import com.marianni.mobileguide.adminui.administration.AdministrationTab;
import com.marianni.mobileguide.adminui.candle.CandleTab;
import com.marianni.mobileguide.adminui.employee.EmployeeTab;
import com.marianni.mobileguide.adminui.freefood.FreeFoodTab;
import com.marianni.mobileguide.adminui.login.LoginControl;
import com.marianni.mobileguide.adminui.login.LoginTab;
import com.marianni.mobileguide.adminui.map.MapTab;
import com.vaadin.ui.TabSheet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * @author mariannarachelova
 */
@Dependent
public class MainTabSheet extends TabSheet {

    @Inject
    private LoginControl loginControl;

    @Inject
    private LoginTab loginTab;

    private EmployeeTab employeeTab;
    private CandleTab candleTab;
    private AdministrationTab administrationTab;
    private MapTab mapTab;
    private FreeFoodTab freefoodTab;


    @PostConstruct
    public void init() {
        employeeTab = new EmployeeTab();
        candleTab = new CandleTab();
        freefoodTab = new FreeFoodTab();
        administrationTab = new AdministrationTab();
        mapTab = new MapTab();



    }

    private void addTabs(boolean isAdmin){
        if (isAdmin) {
            addTab(employeeTab, "Employee");
            addTab(candleTab, "Candle");
            addTab(freefoodTab, "Freefood");
            addTab(mapTab, "Map");
            addTab(administrationTab, "Administration");
        } else {
            addTab(freefoodTab, "Freefood");
        }
    }



    public void refresh() {

        if (loginControl.isLoggedIn()) {
            addLoginTAB();


            addTabs(loginControl.isAdmin());
            employeeTab.refresh();
            candleTab.refresh();
            freefoodTab.refresh();
            mapTab.refresh();
            administrationTab.refresh();
        } else {
            removeAllComponents();
            addLoginTAB();
        }

    }

    public void addLoginTAB() {
        addTab(loginTab, "Login");
        loginTab.setVisible(true);
        loginTab.refresh();
    }
}
