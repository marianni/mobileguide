package com.marianni.mobileguide.adminui.login;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

/**
 * @author mariannarachelova on 22/05/2018
 */
public class LoginFrom extends FormLayout{

    private TextField username;
    private TextField password;
    private Button login;

    public LoginFrom() {
        username = new TextField("Username");
        password = new TextField("Password");
        login = new Button("Login");
        addComponents(username,password,login);
    }

}
