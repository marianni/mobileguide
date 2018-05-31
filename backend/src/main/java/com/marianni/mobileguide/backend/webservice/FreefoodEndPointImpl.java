package com.marianni.mobileguide.backend.webservice;


import com.marianni.mobileguide.backend.service.FreefoodService;
import com.marianni.mobileguide.interfaces.dto.CanteenDTO;
import com.marianni.mobileguide.interfaces.dto.CanteenDailyOfferDTO;
import com.marianni.mobileguide.interfaces.endpoints.CanteenEndPoint;

import javax.inject.Inject;
import java.util.Set;

public class FreefoodEndPointImpl implements CanteenEndPoint{

    @Inject
    private FreefoodService service;

    @Override
    public String getCanteen(final Long id){ return service.getCanteen(id);}

    @Override
    public String getAllMenus(){
        return service.parseAndCreateFreeFood();
    }

    @Override
    public Set<CanteenDTO> getAllCanteens(){
        return service.getAllCanteenDtos();
    }

    @Override
    public Set<CanteenDailyOfferDTO> getCanteenDailyOffer() {
        return null;
    }

    @Override
    public CanteenDTO create(CanteenDTO canteen) {
        return service.create(canteen);
    }

    @Override
    public CanteenDTO update(CanteenDTO canteenDTO) {
        return service.update(canteenDTO);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public CanteenDailyOfferDTO updateDailyOffer(CanteenDailyOfferDTO dailyOfferDTO) {
        return service.updateDailyOffer(dailyOfferDTO);
    }

    @Override
    public CanteenDailyOfferDTO createDailyOffer(CanteenDailyOfferDTO dailyOfferDTO) {
        return service.createDailyOffer(dailyOfferDTO);
    }

    @Override
    public void deleteDailyOffer(Long dailyOfferId) {
        service.deleteDailyOffer(dailyOfferId);
    }
}
