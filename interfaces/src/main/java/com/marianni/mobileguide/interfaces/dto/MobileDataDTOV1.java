package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;
import java.util.Set;

public class MobileDataDTOV1 implements Serializable {

    private Set<EmployeeDTO> employees;
    private Set<CanteenDTO> canteens;
    private Set<CanteenDailyOfferDTO> canteenDailyOffers;
    private Long currentVersionData;


    public Long getCurrentVersionData() {
        return currentVersionData;
    }

    public void setCurrentVersionData(Long currentVersionData) {
        this.currentVersionData = currentVersionData;
    }

    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public Set<CanteenDTO> getCanteens() {
        return canteens;
    }

    public void setCanteens(Set<CanteenDTO> canteens) {
        this.canteens = canteens;
    }

    public Set<CanteenDailyOfferDTO> getCanteenDailyOffers() {
        return canteenDailyOffers;
    }

    public void setCanteenDailyOffers(Set<CanteenDailyOfferDTO> canteenDailyOffers) {
        this.canteenDailyOffers = canteenDailyOffers;
    }
}
