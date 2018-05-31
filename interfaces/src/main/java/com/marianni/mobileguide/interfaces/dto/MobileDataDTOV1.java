package com.marianni.mobileguide.interfaces.dto;

import sun.awt.AWTIcon32_security_icon_yellow16_png;

import java.io.Serializable;
import java.util.Set;

public class MobileDataDTOV1 implements Serializable {

    private Set<EmployeeDTO> employees;
    private Set<Long> deletedEmployees;
    private Set<CandlePlaceDTO> candlePlaces;
    private Set<Long> deletedCandlePlaces;
    private Set<CanteenDTO> canteens;
    private Set<Long> deletedCanteens;
    private Set<CanteenDailyOfferDTO> canteenDailyOffers;
    private Set<MapDTO> map;
    private Set<Long> deletedMap;
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

    public Set<CandlePlaceDTO> getCandlePlaces() {
        return candlePlaces;
    }

    public void setCandlePlaces(Set<CandlePlaceDTO> candlePlaces) {
        this.candlePlaces = candlePlaces;
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

    public Set<Long> getDeletedEmployees() {
        return deletedEmployees;
    }

    public void setDeletedEmployees(Set<Long> deletedEmployees) {
        this.deletedEmployees = deletedEmployees;
    }

    public Set<MapDTO> getMap() {
        return map;
    }

    public void setMap(Set<MapDTO> map) {
        this.map = map;
    }

    public Set<Long> getDeletedCandlePlaces() {
        return deletedCandlePlaces;
    }

    public void setDeletedCandlePlaces(Set<Long> deletedCandlePlaces) {
        this.deletedCandlePlaces = deletedCandlePlaces;
    }

    public Set<Long> getDeletedCanteens() {
        return deletedCanteens;
    }

    public void setDeletedCanteens(Set<Long> deletedCanteens) {
        this.deletedCanteens = deletedCanteens;
    }

    public Set<Long> getDeletedMap() {
        return deletedMap;
    }

    public void setDeletedMap(Set<Long> deletedMap) {
        this.deletedMap = deletedMap;
    }

}
