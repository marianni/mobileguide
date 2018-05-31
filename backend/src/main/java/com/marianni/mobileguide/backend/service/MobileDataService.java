package com.marianni.mobileguide.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marianni.mobileguide.interfaces.dto.MobileDataDTOV1;
import com.marianni.mobileguide.interfaces.dto.MobileDataDTOV2;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
/**
 * @author mariannarachelova
 */
@Stateless
public class MobileDataService {

    @Inject
    private EmployeeService service;

    @Inject
    private FreefoodService freefoodService;

    @Inject
    private MapService mapService;

    @Inject
    private CandleService candleService;

    @PersistenceContext
    private EntityManager em;


    public String getMobileDataV1(final Long modelVersion, final Long latestDataVersion) {
        try {
            if (modelVersion == 1) {
                MobileDataDTOV1 dto = new MobileDataDTOV1();
                dto.setEmployees(service.getOnlyNewEmployees(latestDataVersion));
                dto.setDeletedEmployees(service.getNewlyDeletedEmployeeIds(latestDataVersion));
                dto.setCanteens(freefoodService.getOnlyNewCanteens(latestDataVersion));
                dto.setDeletedCanteens(freefoodService.getNewlyDeletedCanteenIds(latestDataVersion));
                dto.setMap(mapService.getOnlyNewMaps(latestDataVersion));
                dto.setDeletedMap(mapService.getNewlyDeletedMapIds(latestDataVersion));
                dto.setCandlePlaces(candleService.getOnlyNewPlaces(latestDataVersion));
                dto.setDeletedCandlePlaces(candleService.getNewlyDeletedCandlePlaceIds(latestDataVersion));
                Query q = em.createNativeQuery("SELECT last_value FROM public.data_version_sequence;");
                BigInteger version = (BigInteger) q.getSingleResult();
                dto.setCurrentVersionData(Long.valueOf(version.toString()));

                ObjectMapper map = new ObjectMapper();

                String jsonInString = map.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
                return jsonInString;

            } else {
                MobileDataDTOV2 dto = new MobileDataDTOV2();
                dto.setEmployees(service.getOnlyNewEmployees(latestDataVersion));
                dto.setDeletedEmployees(service.getNewlyDeletedEmployeeIds(latestDataVersion));
                dto.setMap(mapService.getOnlyNewMaps(latestDataVersion));
                dto.setDeletedMap(mapService.getNewlyDeletedMapIds(latestDataVersion));
                dto.setCandlePlaces(candleService.getOnlyNewPlaces(latestDataVersion));
                dto.setDeletedCandlePlaces(candleService.getNewlyDeletedCandlePlaceIds(latestDataVersion));
                Query q = em.createNativeQuery("SELECT last_value FROM public.data_version_sequence;");
                BigInteger version = (BigInteger) q.getSingleResult();
                dto.setCurrentVersionData(Long.valueOf(version.toString()));

                ObjectMapper map = new ObjectMapper();

                String jsonInString = map.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
                return jsonInString;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    return "Problem with sending response";
    }
}
