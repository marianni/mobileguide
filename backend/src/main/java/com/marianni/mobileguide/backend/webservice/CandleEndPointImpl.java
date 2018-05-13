package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.service.CandleService;
import com.marianni.mobileguide.interfaces.dto.CandlePlaceDTO;
import com.marianni.mobileguide.interfaces.dto.LectureDTO;
import com.marianni.mobileguide.interfaces.endpoints.CandleEndPoint;

import javax.inject.Inject;
import java.util.Set;

public class CandleEndPointImpl implements CandleEndPoint {

    @Inject
    private CandleService service;

    @Override
    public String getCandlePlace(final Long id) {
        return service.getCandlePlace(id);
    }

    @Override
    public String importCandlePlaces() {
        return service.parseCandle();
    }

    @Override
    public Set<CandlePlaceDTO> getAllPlaces() {
        return service.getAllPlaces();
    }

    @Override
    public Set<LectureDTO> getCandleLectures() {
        return service.getCandleLectures();
    }
}
