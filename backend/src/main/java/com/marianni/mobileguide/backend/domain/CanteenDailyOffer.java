package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="canteen_daily_offers",schema="public")
@NamedQuery(name="CanteenDailyOffer.findAll", query="SELECT d FROM CanteenDailyOffer d")
public class CanteenDailyOffer extends VersionedEntity implements ChildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_sequence")
    private Long id;

    @Column(name = "day_and_date")
    private String dayAndDate;

    @Column(name="dish_name")
    private String dishName;

    @Column(name = "canteen_id", insertable=false, updatable=false)
    private Long canteenId;


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

    public Long getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(Long canteenId) {
        this.canteenId = canteenId;
    }

    @Override
    public void updateParent(EntityManager em) {
        Canteen canteen = em.find(Canteen.class, canteenId);
        canteen.setChangedOn(new Date());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CanteenDailyOffer that = (CanteenDailyOffer) o;

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
