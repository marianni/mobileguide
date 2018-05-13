package com.marianni.mobileguide.backend.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "canteens", schema = "public")
@NamedQuery(name="Canteen.findAll", query="SELECT c FROM Canteen c")
public class Canteen extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private Long id;

    @Column(name = "name")
    private String name;


    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "canteen_id")
    private Set<CanteenDailyOffer> dailyOffers;

    public Canteen() {
    }

    public Canteen(String name) {
        this.name = name;
    }

    public Set<CanteenDailyOffer> getDailyOffers() {
        return dailyOffers;
    }

    public void setDailyOffers(Set<CanteenDailyOffer> dailyOffers) {
        this.dailyOffers = dailyOffers;
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
}
