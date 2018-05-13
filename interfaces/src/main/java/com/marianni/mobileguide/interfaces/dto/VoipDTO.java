package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;

public class VoipDTO implements Serializable {
    private Long id;
    private Long employeeId;
    private String voip;

    public VoipDTO() {
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

    public String getVoip() {
        return voip;
    }

    public void setVoip(String voip) {
        this.voip = voip;
    }
}
