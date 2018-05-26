package com.marianni.mobileguide.adminui.login;

import com.vaadin.ui.VerticalLayout;

/**
 * @author mariannarachelova on 22/05/2018
 */
public class LoginTab extends VerticalLayout {

    private LoginFrom form;

    public LoginTab(){

        form = new LoginFrom();
        addComponent(form);
    }
}
