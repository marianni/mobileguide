package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="employee_voip",schema="public")
public class EmployeeVoip extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_sequence")
    private Long id;

    @Column(name = "voip")
    private Integer voip;

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
}
