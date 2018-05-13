package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;

public class CanteenDailyOfferDTO implements Serializable {

    private Long id;

    private String dayAndDate;

    private String dishName;

    private Long canteenId;

    public Long getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(Long canteenId) {
        this.canteenId = canteenId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDayAndDate() {
        return dayAndDate;
    }

    public void setDayAndDate(String dayAndDate) {
        this.dayAndDate = dayAndDate;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
}
