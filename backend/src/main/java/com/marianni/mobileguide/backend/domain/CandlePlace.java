package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "places", schema = "public")
@NamedQuery(name="CandlePlace.findAll", query="SELECT c FROM CandlePlace c")
public class CandlePlace implements Serializable{

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
