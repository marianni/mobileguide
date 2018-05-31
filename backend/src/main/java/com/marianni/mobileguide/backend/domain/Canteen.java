package com.marianni.mobileguide.backend.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "canteens", schema = "public")
@NamedQueries({
        @NamedQuery(name=Canteen.FIND_ALL, query="SELECT c FROM Canteen c where deleted = false"),
        @NamedQuery(name=Canteen.FIND_BY_NAME, query="SELECT c FROM Canteen c WHERE c.name = :name"),
        @NamedQuery(name=Canteen.FIND_WITH_DIFFERENT_NAME, query="SELECT c FROM Canteen c WHERE c.name NOT IN (:names)"),
        @NamedQuery(name=Canteen.FIND_ALL_NEWER_THAN_VERSION, query = "SELECT c FROM Canteen c WHERE c.dataVersion > :latestDataVersion AND deleted = false"),
        @NamedQuery(name=Canteen.FIND_ALL_DELETED_IDS_NEWER_THAN_VERSION, query = "SELECT c.id FROM Canteen c WHERE c.dataVersion > :latestDataVersion AND deleted = true")

})
public class Canteen extends SoftDeleteEntity {

    public static final String FIND_WITH_DIFFERENT_NAME = "Canteen.findAllWithDifferentName";
    public static final String FIND_ALL = "Canteen.findAll";
    public static final String FIND_BY_NAME = "Canteen.findByName";
    public static final String FIND_ALL_NEWER_THAN_VERSION = "Canteen.findAllNewerThanVersion";
    public static final String FIND_ALL_DELETED_IDS_NEWER_THAN_VERSION = "Canteen.findAllDeletedIdsNewerThanVersion";

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
