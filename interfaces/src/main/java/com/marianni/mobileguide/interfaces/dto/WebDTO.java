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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebDTO webDTO = (WebDTO) o;

        if (id != null ? !id.equals(webDTO.id) : webDTO.id != null) return false;
        if (employeeId != null ? !employeeId.equals(webDTO.employeeId) : webDTO.employeeId != null) return false;
        return web != null ? web.equals(webDTO.web) : webDTO.web == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 17 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 17 * result + (web != null ? web.hashCode() : 0);
        return result;
    }
}
