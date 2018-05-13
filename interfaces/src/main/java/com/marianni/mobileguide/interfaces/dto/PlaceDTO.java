package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;

public class PlaceDTO implements Serializable {
    private Long id;
    private Long employeeId;
    private String place;

    public PlaceDTO() {
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}

