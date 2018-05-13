package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="employee_web",schema="public")
public class EmployeeWeb extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_sequence")
    private Long id;

    @Column(name = "web")
    private String web;

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
}
