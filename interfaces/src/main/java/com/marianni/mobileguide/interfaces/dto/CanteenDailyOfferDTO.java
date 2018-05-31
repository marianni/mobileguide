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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CanteenDailyOfferDTO that = (CanteenDailyOfferDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dayAndDate != null ? !dayAndDate.equals(that.dayAndDate) : that.dayAndDate != null) return false;
        if (dishName != null ? !dishName.equals(that.dishName) : that.dishName != null) return false;
        return canteenId != null ? canteenId.equals(that.canteenId) : that.canteenId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dayAndDate != null ? dayAndDate.hashCode() : 0);
        result = 31 * result + (dishName != null ? dishName.hashCode() : 0);
        result = 31 * result + (canteenId != null ? canteenId.hashCode() : 0);
        return result;
    }
}
