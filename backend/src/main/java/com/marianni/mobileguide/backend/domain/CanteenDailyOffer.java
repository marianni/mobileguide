package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="canteen_daily_offers",schema="public")
@NamedQuery(name="CanteenDailyOffer.findAll", query="SELECT d FROM CanteenDailyOffer d")
public class CanteenDailyOffer extends VersionedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_sequence")
    private Long id;

    @Column(name = "day_and_date")
    private String dayAndDate;

    @Column(name="dish_name")
    private String dishName;

    public CanteenDailyOffer() {
    }

    public CanteenDailyOffer(String dayAndDate, String dishName) {
        this.dayAndDate = dayAndDate;
        this.dishName = dishName;
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
