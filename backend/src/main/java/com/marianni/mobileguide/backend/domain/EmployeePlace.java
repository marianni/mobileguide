package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_places", schema = "public")
public class EmployeePlace extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private Long id;

    @Column(name = "place")
    private String place;

    public EmployeePlace(String place) {
        this.place = place;
    }

    public EmployeePlace() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        EmployeePlace that = (EmployeePlace) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return place.equals(that.place);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + place.hashCode();
        return result;
    }
}
