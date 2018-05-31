package com.marianni.mobileguide.adminui;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author mariannarachelova
 */
@CDIView(value = MainView.VIEW_ID)
public class MainView extends CustomComponent implements View {

    public static final String VIEW_ID = "MainView";
    @Inject
    private MainTabSheet mainTabSheet;

    @PostConstruct
    public void init() {
        setCompositionRoot(mainTabSheet);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        mainTabSheet.refresh();
    }
}
