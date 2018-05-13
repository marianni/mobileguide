package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;

public class WebDTO implements Serializable {
    private Long id;
    private Long employeeId;
    private String web;

    public WebDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
