package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CanteenDTO implements Serializable {

    private Long id;

    private String name;

    private Set<CanteenDailyOfferDTO> dailyOffers = new HashSet<CanteenDailyOfferDTO>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CanteenDailyOfferDTO> getDailyOffers() {
        return dailyOffers;
    }

    public void setDailyOffers(Set<CanteenDailyOfferDTO> dailyOffers) {
        this.dailyOffers = dailyOffers;
    }
}
