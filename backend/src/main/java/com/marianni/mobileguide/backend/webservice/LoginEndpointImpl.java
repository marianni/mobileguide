package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.service.login.LoginService;
import com.marianni.mobileguide.interfaces.dto.LoginDTO;
import com.marianni.mobileguide.interfaces.endpoints.LoginEndpoint;

import javax.inject.Inject;

/**
 * @author mariannarachelova
 */
public class LoginEndpointImpl implements LoginEndpoint {
    @Inject
    private LoginService loginService;

    @Override
    public LoginDTO login(LoginDTO loginDto) {
        return loginService.login(loginDto);
    }
}
