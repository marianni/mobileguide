package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="employee_web",schema="public")
public class EmployeeWeb extends VersionedEntity implements ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_sequence")
    private Long id;

    @Column(name = "web")
    private String web;

    @Column(name = "employee_id", insertable=false, updatable=false)
    private Long employeeId;

    public EmployeeWeb(String web) {
        this.web = web;
    }

    public EmployeeWeb() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
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
