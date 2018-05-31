package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.service.MobileDataService;
import com.marianni.mobileguide.interfaces.dto.MobileDataDTOV1;
import com.marianni.mobileguide.interfaces.endpoints.MobileDataEndPoint;

import javax.inject.Inject;
import java.util.Set;
/**
 * @author mariannarachelova
 */

public class MobileDataEndpointImpl implements MobileDataEndPoint {
    @Inject private MobileDataService service;

    @Override
    public String getMobileDataV1(Long modelVersion, Long latestDataVersion) {
        return service.getMobileDataV1(modelVersion,latestDataVersion);
    }
}
