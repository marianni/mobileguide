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

@Stateless
public class CanteenService {

    @Inject
    private HTMLParserFreeFood parser;

    @PersistenceContext
    private EntityManager em;

    public String getCanteen(Long id){
        Canteen canteen = em.find(Canteen.class, id);
        return canteen.getName();
    }
    public String parseAndCreateFreeFood(){
        parser.parseFreefood();
        TypedQuery<Canteen> query = em.createNamedQuery("Canteen.findAll", Canteen.class);
        List<Canteen> results = query.getResultList();
        Gson gson = new Gson();
        String json = gson.toJson(results);
        return json;
    }

    public Set<CanteenDTO> getAllCanteens(){
        TypedQuery<Canteen> query = em.createNamedQuery("Canteen.findAll", Canteen.class);
        List<Canteen> results = query.getResultList();

        Set<CanteenDTO> dtos = new HashSet<>();
        results.forEach(canteen -> dtos.add(CanteenConverter.toDTO(canteen)));
        return dtos;
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
        em.remove(em.find(Canteen.class, id));

    }

    public void deleteDailyOffer(Long dailyOfferId){
        em.remove(em.find(CanteenDailyOffer.class,dailyOfferId));
    }

    public CanteenDailyOfferDTO updateDailyOffer(CanteenDailyOfferDTO dailyOfferDTO) {
        CanteenDailyOffer dailyOffer = em.find(CanteenDailyOffer.class, dailyOfferDTO.getId());
        CanteenConverter.toEntity(dailyOfferDTO,dailyOffer);
        return CanteenConverter.toDTO(dailyOfferDTO.getCanteenId(), dailyOffer);
    }

    public CanteenDailyOfferDTO createDailyOffer(CanteenDailyOfferDTO dailyOfferDTO) {
        Canteen canteen = em.find(Canteen.class, dailyOfferDTO.getCanteenId());
        CanteenDailyOffer dailyOffer = new CanteenDailyOffer();
        //CanteenConverter.toEntity(dailyOfferDTO,dailyOffer);
        dailyOffer = CanteenConverter.toEntity(dailyOfferDTO,dailyOffer);
        canteen.getDailyOffers().add(dailyOffer);
        em.persist(dailyOffer);
        return CanteenConverter.toDTO(dailyOfferDTO.getCanteenId(), dailyOffer);
    }
}
