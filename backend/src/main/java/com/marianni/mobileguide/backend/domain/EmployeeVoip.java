package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="employee_voip",schema="public")
public class EmployeeVoip extends VersionedEntity implements ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_sequence")
    private Long id;

    @Column(name = "voip")
    private Integer voip;

    @Column(name = "employee_id", insertable=false, updatable=false)
    private Long employeeId;

    public EmployeeVoip(Integer voip) {
        this.voip = voip;
    }

    public EmployeeVoip() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVoip() {
        return voip;
    }

    public void setVoip(Integer voip) {
        this.voip = voip;
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
}
