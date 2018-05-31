package com.marianni.mobileguide.adminui.login;

import com.marianni.mobileguide.interfaces.dto.UserRoleEnum;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * @author mariannarachelova
 */

@SessionScoped
public class LoginControl implements Serializable {

    private UserRoleEnum role;
    private String userName;

    public void login(String userName, UserRoleEnum role) {
        this.userName = userName;
        this.role = role;
    }

    public void logout() {
        this.userName = null;
        this.role = null;
    }

    public boolean isLoggedIn() {
        return this.userName != null && this.role != null;
    }

    public boolean isAdmin() {
        return this.role.equals(UserRoleEnum.ADMIN);
    }


}
