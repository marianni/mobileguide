package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;

@Entity
@Table(name = "faculty", schema = "public")
@NamedQueries({
        @NamedQuery(name = Faculty.FIND_ALL, query = "SELECT f FROM Faculty f WHERE deleted = false"),
        @NamedQuery(name = Faculty.FIND_BY_MAP, query = "SELECT f FROM Faculty f WHERE f.map= :map"),
        @NamedQuery(name = Faculty.FIND_ALL_WITH_DIFFERENT_MAP, query = "SELECT f FROM Faculty f WHERE f.map NOT IN (:map) "),
        @NamedQuery(name = Faculty.FIND_ALL_NEWER_THAN_VERSION, query = "SELECT f FROM Faculty f WHERE f.dataVersion > :latestDataVersion AND deleted = false"),
        @NamedQuery(name = Faculty.FIND_ALL_DELETED_IDS_NEWER_THAN_VERSION, query = "SELECT f.id FROM Faculty f WHERE f.dataVersion > :latestDataVersion AND deleted = true")

})
public class Faculty extends SoftDeleteEntity{

    public static final String FIND_ALL = "Faculty.findAll";
    public static final String FIND_BY_MAP = "Faculty.findByMap";
    public static final String FIND_ALL_WITH_DIFFERENT_MAP= "Faculty.findAllWithDifferentMap";
    public static final String FIND_ALL_NEWER_THAN_VERSION = "Faculty.findAllNewerThanVersion";
    public static final String FIND_ALL_DELETED_IDS_NEWER_THAN_VERSION = "Faculty.findAllDeletedIdsNewerThanVersion";

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
