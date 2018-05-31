package com.marianni.mobileguide.adminui.login;

import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * @author mariannarachelova
 */

@Dependent
public class LoginTab extends VerticalLayout {

    @Inject
    private LoginForm form;

    @PostConstruct
    public void init(){
        addComponent(form);
    }

    public void refresh() {
        form.refresh();
    }
}
