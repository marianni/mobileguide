package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.service.MobileDataService;
import com.marianni.mobileguide.interfaces.dto.MobileDataDTOV1;
import com.marianni.mobileguide.interfaces.endpoints.MobileDataEndPoint;

import javax.inject.Inject;
import java.util.Set;


public class MobileDataEndpointImpl implements MobileDataEndPoint {
    @Inject private MobileDataService service;


    @Override
    public Set<MobileDataDTOV1> getMobileDataV1(final Long latestDataVersion) {
        return service.getMobileDataV1(latestDataVersion);
    }
}
