package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;

public class PhoneNumberDTO implements Serializable{
    private Long id;
    private Long employeeId;
    private String phoneNumber;

    public PhoneNumberDTO() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
