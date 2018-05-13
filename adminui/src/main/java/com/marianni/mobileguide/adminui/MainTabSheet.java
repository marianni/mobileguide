package com.marianni.mobileguide.adminui;


import com.marianni.mobileguide.adminui.administration.AdministrationTab;
import com.marianni.mobileguide.adminui.candle.CandleTab;
import com.marianni.mobileguide.adminui.employee.EmployeeTab;
import com.marianni.mobileguide.adminui.freefood.FreeFoodTab;
import com.marianni.mobileguide.adminui.map.MapTab;
import com.vaadin.ui.TabSheet;

public class MainTabSheet extends TabSheet{

    public MainTabSheet() {


        EmployeeTab employeeTab = new EmployeeTab();
        CandleTab candleTab = new CandleTab();
        FreeFoodTab freefoodTab = new FreeFoodTab();
        AdministrationTab administrationTab = new AdministrationTab();
        MapTab mapTab = new MapTab();
        addTab(employeeTab,"Employee");
        addTab(candleTab,"Candle");
        addTab(freefoodTab,"Freefood");
        addTab(mapTab,"Map");
        addTab(administrationTab,"Administration");


        // refresh popup data
        employeeTab.refresh();
        mapTab.refresh();
    }
}
