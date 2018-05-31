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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceDTO placeDTO = (PlaceDTO) o;

        if (id != null ? !id.equals(placeDTO.id) : placeDTO.id != null) return false;
        if (employeeId != null ? !employeeId.equals(placeDTO.employeeId) : placeDTO.employeeId != null) return false;
        return place != null ? place.equals(placeDTO.place) : placeDTO.place == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 17 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 17 * result + (place != null ? place.hashCode() : 0);
        return result;
    }


}

