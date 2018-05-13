package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;

@Entity
@Table(name = "faculty", schema = "public")
@NamedQuery(name = "Faculty.findAll", query = "SELECT e FROM Faculty e")
public class Faculty extends VersionedEntity{



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private Long id;

    @Column(name = "map")
    private String map;

    public Faculty() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
