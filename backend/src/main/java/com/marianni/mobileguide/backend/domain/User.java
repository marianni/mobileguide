package com.marianni.mobileguide.backend.domain;

import com.marianni.mobileguide.interfaces.dto.UserRoleEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author mariannarachelova on 27/05/2018
 */
@Entity
@Table(name = "users", schema = "public")
@NamedQuery(name = User.FIND_BY_USERNAME_AND_PASSWORD, query = "SELECT u FROM User u WHERE username = :username AND password = :password")
public class User implements Serializable {

    public static final String FIND_BY_USERNAME_AND_PASSWORD = "User.findByUsername";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
