package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="employee_phone_number",schema="public")
public class EmployeePhoneNumber extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_sequence")
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

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
}
