package com.marianni.mobileguide.backend.service;

import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import com.marianni.mobileguide.interfaces.dto.MobileDataDTOV1;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class MobileDataService {

    @Inject
    private EmployeeService service;

    @PersistenceContext
    private EntityManager em;

    public Set<MobileDataDTOV1> getMobileDataV1(final Long latestDataVersion) {

        Set<MobileDataDTOV1> dtos = new HashSet<>();
        MobileDataDTOV1 mobileDataDTOV1 = new MobileDataDTOV1();
        mobileDataDTOV1.setEmployees(service.getOnlyNewEmployees(latestDataVersion));
        Query q = em.createNativeQuery( "SELECT currval('public.data_version_sequence')");
        BigInteger version = (BigInteger) q.getSingleResult();
        mobileDataDTOV1.setCurrentVersionData(Long.valueOf(version.toString()));
        dtos.add(mobileDataDTOV1);

        return dtos;
    }
}
