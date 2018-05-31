package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * @author mariannarachelova
 */
public class MobileDataDTOV2 implements Serializable{

    private Set<EmployeeDTO> employees;
    private Set<Long> deletedEmployees;
    private Set<CandlePlaceDTO> candlePlaces;
    private Set<Long> deletedCandlePlaces;
    private Set<MapDTO> map;
    private Set<Long> deletedMap;
    private Long currentVersionData;

    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public Set<Long> getDeletedEmployees() {
        return deletedEmployees;
    }

    public void setDeletedEmployees(Set<Long> deletedEmployees) {
        this.deletedEmployees = deletedEmployees;
    }

    public Set<CandlePlaceDTO> getCandlePlaces() {
        return candlePlaces;
    }

    public void setCandlePlaces(Set<CandlePlaceDTO> candlePlaces) {
        this.candlePlaces = candlePlaces;
    }

    public Set<Long> getDeletedCandlePlaces() {
        return deletedCandlePlaces;
    }

    public void setDeletedCandlePlaces(Set<Long> deletedCandlePlaces) {
        this.deletedCandlePlaces = deletedCandlePlaces;
    }

    public Set<MapDTO> getMap() {
        return map;
    }

    public void setMap(Set<MapDTO> map) {
        this.map = map;
    }

    public Set<Long> getDeletedMap() {
        return deletedMap;
    }

    public void setDeletedMap(Set<Long> deletedMap) {
        this.deletedMap = deletedMap;
    }

    public Long getCurrentVersionData() {
        return currentVersionData;
    }

    public void setCurrentVersionData(Long currentVersionData) {
        this.currentVersionData = currentVersionData;
    }
}
