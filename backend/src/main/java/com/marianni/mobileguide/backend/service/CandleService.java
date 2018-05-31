package com.marianni.mobileguide.backend.service;


import com.google.gson.Gson;
import com.marianni.mobileguide.backend.domain.CandlePlace;
import com.marianni.mobileguide.backend.service.candle.CandleConverter;
import com.marianni.mobileguide.backend.webservice.HTMLParserCandle;
import com.marianni.mobileguide.interfaces.dto.CandlePlaceDTO;
import com.marianni.mobileguide.interfaces.dto.LectureDTO;

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
public class CandleService {

    @Inject
    private HTMLParserCandle parser;

    @PersistenceContext
    private EntityManager em;

    public String getCandlePlace(Long id) {
        CandlePlace candlePlace = em.find(CandlePlace.class, id);
        return candlePlace.getName();
    }

    public String parseCandle() {
        parser.parseCandle();
        TypedQuery<CandlePlace> query = em.createNamedQuery(CandlePlace.FIND_ALL, CandlePlace.class);
        List<CandlePlace> results = query.getResultList();
        Gson gson = new Gson();
        String json = gson.toJson(results);
        return json;
    }


    public Set<CandlePlaceDTO> getAllPlaces() {
        TypedQuery<CandlePlace> query = em.createNamedQuery(CandlePlace.FIND_ALL, CandlePlace.class);
        List<CandlePlace> results = query.getResultList();

        Set<CandlePlaceDTO> dtos = new HashSet<>();
        results.forEach(candlePlace -> dtos.add(CandleConverter.toDTO(candlePlace)));
        return dtos;
    }

    public List<CandlePlace> findAllWithDiferentName(Set<String> names) {
        TypedQuery<CandlePlace> query = em.createNamedQuery(CandlePlace.FIND_ALL_WITH_DIFFERENT_NAME, CandlePlace.class).setParameter("name", names);
        List<CandlePlace> results = query.getResultList();
        return results;
    }

    public Set<CandlePlaceDTO> getOnlyNewPlaces(final Long latestVersion) {
        TypedQuery<CandlePlace> query = em.createNamedQuery(CandlePlace.FIND_ALL_NEWER_THAN_VERSION, CandlePlace.class).setParameter("latestDataVersion", latestVersion);
        List<CandlePlace> results = query.getResultList();

        Set<CandlePlaceDTO> dtos = new HashSet<>();
        results.forEach(employee -> dtos.add(CandleConverter.toDTO(employee)));
        return dtos;
    }

    public Set<Long> getNewlyDeletedCandlePlaceIds(final Long latestVersion) {
        TypedQuery<Long> query = em.createNamedQuery(CandlePlace.FIND_ALL_DELETED_IDS_NEWER_THAN_VERSION, Long.class).setParameter("latestDataVersion", latestVersion);
        return new HashSet<>(query.getResultList());
    }

    public CandlePlace getCandlePlaceByName(String name) {
        TypedQuery<CandlePlace> query = em.createNamedQuery(CandlePlace.FIND_BY_NAME, CandlePlace.class).setParameter("name", name);
        List<CandlePlace> results = query.getResultList();
        return results.stream().findFirst().orElse(null);
    }
}
