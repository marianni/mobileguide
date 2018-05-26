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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumberDTO that = (PhoneNumberDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        return phoneNumber != null ? phoneNumber.equals(that.phoneNumber) : that.phoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 17 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 17 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}
