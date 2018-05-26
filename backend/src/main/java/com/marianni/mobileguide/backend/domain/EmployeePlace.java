package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employee_places", schema = "public")
public class EmployeePlace extends VersionedEntity implements ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private Long id;

    @Column(name = "place")
    private String place;

    @Column(name = "employee_id", insertable=false, updatable=false)
    private Long employeeId;


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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void updateParent(EntityManager em) {
        Employee employee = em.find(Employee.class, employeeId);
        employee.setChangedOn(new Date());
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
