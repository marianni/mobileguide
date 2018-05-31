package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "places", schema = "public")
@NamedQueries({
        @NamedQuery(name = CandlePlace.FIND_ALL, query = "SELECT c FROM CandlePlace c where deleted = false"),
        @NamedQuery(name = CandlePlace.FIND_BY_NAME, query = "SELECT c FROM CandlePlace c WHERE c.name = :name"),
        @NamedQuery(name = CandlePlace.FIND_ALL_WITH_DIFFERENT_NAME, query = "SELECT c FROM CandlePlace c WHERE c.name NOT IN (:names) "),
        @NamedQuery(name = CandlePlace.FIND_ALL_NEWER_THAN_VERSION, query = "SELECT c FROM CandlePlace c WHERE c.dataVersion > :latestDataVersion AND deleted = false"),
        @NamedQuery(name = CandlePlace.FIND_ALL_DELETED_IDS_NEWER_THAN_VERSION, query = "SELECT c.id FROM CandlePlace c WHERE c.dataVersion > :latestDataVersion AND deleted = true")
})
public class CandlePlace extends SoftDeleteEntity {


    public static final String FIND_ALL = "CandlePlace.findAll";
    public static final String FIND_BY_NAME = "CandlePlace.findByName";
    public static final String FIND_ALL_WITH_DIFFERENT_NAME = "CandlePlace.findAllWithDifferentName";
    public static final String FIND_ALL_NEWER_THAN_VERSION = "CandlePlace.findAllNewerThanVersion";
    public static final String FIND_ALL_DELETED_IDS_NEWER_THAN_VERSION = "CandlePlace.findAllDeletedIdsNewerThanVersion";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "place_id")
    private Collection<CandleLecture> candleLectures = new ArrayList<>();

    public CandlePlace() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<CandleLecture> getCandleLectures() {
        return candleLectures;
    }

    public void setCandleLectures(Collection<CandleLecture> candleLectures) {
        this.candleLectures = candleLectures;
    }
}
