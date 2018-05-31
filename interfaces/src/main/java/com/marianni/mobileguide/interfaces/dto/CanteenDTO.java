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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CanteenDTO that = (CanteenDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        //return dailyOffers != null ? dailyOffers.equals(that.dailyOffers) : that.dailyOffers == null;


        if (dailyOffers.size() != that.dailyOffers.size() || !dailyOffers.stream().allMatch(p -> that.dailyOffers.contains(p))){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dailyOffers != null ? dailyOffers.hashCode() : 0);
        return result;
    }
}
