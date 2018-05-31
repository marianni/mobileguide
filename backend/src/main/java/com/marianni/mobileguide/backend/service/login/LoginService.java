package com.marianni.mobileguide.backend.service.login;

import com.marianni.mobileguide.backend.domain.User;
import com.marianni.mobileguide.interfaces.dto.LoginDTO;
import com.marianni.mobileguide.interfaces.dto.UserRoleEnum;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author mariannarachelova
 */

@Stateless
public class LoginService {
    @PersistenceContext
    private EntityManager em;

    public LoginDTO login(LoginDTO loginDto) {

        User user = findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (user == null) {
            return null;
        }

        LoginDTO newLoginDto = new LoginDTO();
        newLoginDto.setRole(user.getRole());
        newLoginDto.setPassword(user.getPassword());
        newLoginDto.setUsername(user.getUsername());
        return newLoginDto;
    }


    private User findByUsernameAndPassword(String username, String password) {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_USERNAME_AND_PASSWORD, User.class).setParameter("username", username).setParameter("password", password);
        return query.getResultList().stream().findFirst().orElse(null);
    }

}
