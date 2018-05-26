package com.marianni.mobileguide.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marianni.mobileguide.interfaces.dto.MobileDataDTOV1;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;

@Stateless
public class MobileDataService {

    @Inject
    private EmployeeService service;

    @PersistenceContext
    private EntityManager em;

    //nasmulovat
    public String getMobileDataV1(final Long modelVersion, final Long latestDataVersion) {
        try {
            if (modelVersion == 1) {
                MobileDataDTOV1 dto = new MobileDataDTOV1();
                dto.setEmployees(service.getOnlyNewEmployees(latestDataVersion));
                dto.setDeletedEmployees(service.getNewlyDeletedEmployeeIds(latestDataVersion));
                Query q = em.createNativeQuery("SELECT last_value FROM public.data_version_sequence;");
                BigInteger version = (BigInteger) q.getSingleResult();
                dto.setCurrentVersionData(Long.valueOf(version.toString()));

                ObjectMapper map = new ObjectMapper();

                String jsonInString = map.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
                return jsonInString;

            } else {
                //todo tu nasimulovat  tu bude verzia dva
                return "";
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    return "Problem";
    }
}
