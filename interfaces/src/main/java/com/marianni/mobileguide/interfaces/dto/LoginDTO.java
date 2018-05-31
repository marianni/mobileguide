package com.marianni.mobileguide.interfaces.dto;

/**
 * @author mariannarachelova on 27/05/2018
 */
public class LoginDTO {

    private String username;
    private String password;
    private UserRoleEnum role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
