package com.marianni.mobileguide.adminui.login;

import com.marianni.mobileguide.adminui.MainView;
import com.marianni.mobileguide.interfaces.dto.LoginDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * @author mariannarachelova
 */
@Dependent
public class LoginForm extends FormLayout {

    @Inject
    private LoginControl loginControl;

    private Binder<LoginDTO> binder = new Binder<>(LoginDTO.class);
    private TextField username;
    private PasswordField password;
    private Button loginButton;

    @PostConstruct
    public void init() {
        username = new TextField("Username");
        password = new PasswordField("Password");
        loginButton = new Button("Login");

        binder.bindInstanceFields(this);


        loginButton.addClickListener(e -> {
            if (loginControl.isLoggedIn()) {
                loginControl.logout();
                UI.getCurrent().getNavigator().navigateTo(MainView.VIEW_ID);
                return;
            }

            LoginDTO loginDTO = bindLoginData(); // binds data from login form to DTO
            LoginDTO backendLoginDto = RestClients.login().login(loginDTO); // goes to backend and tries to login with data from DB


            if (backendLoginDto == null) {
                Notification.show("Username or password not correct", Notification.Type.WARNING_MESSAGE);
                return;
            }
            loginControl.login(backendLoginDto.getUsername(), backendLoginDto.getRole());
            loginButton.setCaption("Logout");
            username.setVisible(false);
            password.setVisible(false);
            UI.getCurrent().getNavigator().navigateTo(MainView.VIEW_ID);
        });
        addComponents(username, password, loginButton);
    }

    public LoginDTO bindLoginData() {
        try {
            binder.writeBean(binder.getBean());
            return binder.getBean();
        } catch (ValidationException e) {
            Notification.show("Login problem");
            return null;
        }
    }

    public void refresh() {
        binder.setBean(new LoginDTO());
        if (loginControl.isLoggedIn()){
            renderLoggeIn();
        } else {
            renderNotLoggeIn();
        }
    }

    private void renderLoggeIn(){
        loginButton.setCaption("Logout");
        username.setVisible(false);
        password.setVisible(false);
    }

    private void renderNotLoggeIn(){
        loginButton.setCaption("Login");
        username.setVisible(true);
        password.setVisible(true);
    }





}
