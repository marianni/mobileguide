package com.marianni.mobileguide.backend.service;

import com.google.gson.Gson;
import com.marianni.mobileguide.backend.domain.Canteen;
import com.marianni.mobileguide.backend.domain.CanteenDailyOffer;
import com.marianni.mobileguide.backend.service.canteen.CanteenConverter;
import com.marianni.mobileguide.backend.webservice.HTMLParserFreeFood;
import com.marianni.mobileguide.interfaces.dto.CanteenDTO;
import com.marianni.mobileguide.interfaces.dto.CanteenDailyOfferDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * @author mariannarachelova
 */
@Stateless
public class FreefoodService {

    @Inject
    private HTMLParserFreeFood parser;

    @PersistenceContext
    private EntityManager em;

    public String getCanteen(Long id) {
        Canteen canteen = em.find(Canteen.class, id);
        return canteen.getName();
    }

    public String parseAndCreateFreeFood() {
        parser.parseFreefood();
        TypedQuery<Canteen> query = em.createNamedQuery(Canteen.FIND_ALL, Canteen.class);
        List<Canteen> results = query.getResultList();
        Gson gson = new Gson();
        String json = gson.toJson(results);
        return json;
    }

    public Canteen getCanteenByName(String name) {
        TypedQuery<Canteen> query = em.createNamedQuery(Canteen.FIND_BY_NAME, Canteen.class).setParameter("name", name);
        List<Canteen> results = query.getResultList();
        return results.stream().findFirst().orElse(null);
    }

    public List<Canteen> findAllWithDifferentName(Set<String> names) {
        TypedQuery<Canteen> query = em.createNamedQuery(Canteen.FIND_WITH_DIFFERENT_NAME, Canteen.class).setParameter("names", names);
        List<Canteen> results = query.getResultList();
        return results;
    }

    public Set<CanteenDTO> getAllCanteenDtos() {
        TypedQuery<Canteen> query = em.createNamedQuery(Canteen.FIND_ALL, Canteen.class);
        List<Canteen> results = query.getResultList();

        Set<CanteenDTO> dtos = new HashSet<>();
        results.forEach(canteen -> dtos.add(CanteenConverter.toDTO(canteen)));
        return dtos;
    }

    public List<Canteen> getAllCanteens() {
        TypedQuery<Canteen> query = em.createNamedQuery(Canteen.FIND_ALL, Canteen.class);
        return query.getResultList();
    }

    public Set<CanteenDTO> getOnlyNewCanteens(final Long latestVersion) {
        TypedQuery<Canteen> query = em.createNamedQuery(Canteen.FIND_ALL_NEWER_THAN_VERSION, Canteen.class).setParameter("latestDataVersion", latestVersion);
        List<Canteen> results = query.getResultList();

        Set<CanteenDTO> dtos = new HashSet<>();
        results.forEach(employee -> dtos.add(CanteenConverter.toDTO(employee)));
        return dtos;
    }

    public Set<Long> getNewlyDeletedCanteenIds(final Long latestVersion) {
        TypedQuery<Long> query = em.createNamedQuery(Canteen.FIND_ALL_DELETED_IDS_NEWER_THAN_VERSION, Long.class).setParameter("latestDataVersion", latestVersion);
        return new HashSet<>(query.getResultList());
    }

    public CanteenDTO create(CanteenDTO canteenDTO) {
        Canteen canteen = new Canteen();
        CanteenConverter.toEntity(canteen, canteenDTO);
        em.persist(canteen);
        CanteenDTO createdDto = CanteenConverter.toDTO(canteen);
        return createdDto;
    }

    public CanteenDTO update(CanteenDTO canteenDTO) {
        Canteen canteen = em.find(Canteen.class, canteenDTO.getId());
        CanteenConverter.toEntity(canteen, canteenDTO);
        return CanteenConverter.toDTO(canteen);
    }

    public void delete(final Long id) {
        Canteen canteen = em.find(Canteen.class, id);
        canteen.setDeleted(true);
    }

    public void deleteDailyOffer(Long dailyOfferId) {
        em.remove(em.find(CanteenDailyOffer.class, dailyOfferId));
    }

    public CanteenDailyOfferDTO updateDailyOffer(CanteenDailyOfferDTO dailyOfferDTO) {
        CanteenDailyOffer dailyOffer = em.find(CanteenDailyOffer.class, dailyOfferDTO.getId());
        CanteenConverter.toEntity(dailyOfferDTO, dailyOffer);
        return CanteenConverter.toDTO(dailyOfferDTO.getCanteenId(), dailyOffer);
    }

    public CanteenDailyOfferDTO createDailyOffer(CanteenDailyOfferDTO dailyOfferDTO) {
        Canteen canteen = em.find(Canteen.class, dailyOfferDTO.getCanteenId());
        CanteenDailyOffer dailyOffer = new CanteenDailyOffer();
        //CanteenConverter.toEntity(dailyOfferDTO,dailyOffer);
        dailyOffer = CanteenConverter.toEntity(dailyOfferDTO, dailyOffer);
        canteen.getDailyOffers().add(dailyOffer);
        em.persist(dailyOffer);
        return CanteenConverter.toDTO(dailyOfferDTO.getCanteenId(), dailyOffer);
    }
}
