package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="employee_phone_number",schema="public")
public class EmployeePhoneNumber extends VersionedEntity implements ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_sequence")
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "employee_id", insertable=false, updatable=false)
    private Long employeeId;

    public EmployeePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmployeePhoneNumber() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
