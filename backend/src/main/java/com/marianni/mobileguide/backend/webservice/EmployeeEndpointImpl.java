package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.service.EmployeeService;
import com.marianni.mobileguide.interfaces.dto.*;
import com.marianni.mobileguide.interfaces.endpoints.EmployeeEndpoint;

import javax.inject.Inject;
import java.util.Set;
/**
 * @author mariannarachelova
 */
public class EmployeeEndpointImpl implements EmployeeEndpoint {

    @Inject
    private EmployeeService service;


   @Override
    public String getEmployee(final Long id) {
        return service.getEmployee(id);
    }

    @Override
    public String importEmployees() {
       return service.parseAndCreateEmployees();
    }

    @Override
    public Set<EmployeeDTO> getEmployees() {
        return service.getEmployees();
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        return service.update(employeeDTO);
    }

    @Override
    public EmployeeDTO create(EmployeeDTO employee) {
        return service.create(employee);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public void deletePhoneNumber(Long phoneNumberId) {
        service.deletePhoneNumber(phoneNumberId);
    }

    @Override
    public void deletePublication(Long publicationId) {
        service.deletePublication(publicationId);
    }

    @Override
    public void deleteVoip(Long voipId) {
        service.deleteVoip(voipId);
    }

    @Override
    public void deleteWeb(Long webId) {
        service.deleteWeb(webId);
    }

    @Override
    public void deletePlace(Long placeId) {
        service.deletePlace(placeId);
    }

    @Override
    public PlaceDTO updatePlace(PlaceDTO placeDTO) {
        return service.updatePlace(placeDTO);
    }

    @Override
    public PlaceDTO createPlace(PlaceDTO placeDTO) {
        return service.createPlace(placeDTO);
    }

    @Override
    public PhoneNumberDTO updatePhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        return service.updatePhoneNumber(phoneNumberDTO);
    }

    @Override
    public PhoneNumberDTO createPhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        return service.createPhoneNumber(phoneNumberDTO);
    }

    @Override
    public PublicationDTO updatePublication(PublicationDTO publicationDTO) {
        return service.updatePublication(publicationDTO);
    }

    @Override
    public PublicationDTO createPublication(PublicationDTO publicationDTO) {
        return service.createPublication(publicationDTO);
    }

    @Override
    public VoipDTO updateVoip(VoipDTO voipDTO) {
        return service.updateVoip(voipDTO);
    }

    @Override
    public VoipDTO createVoip(VoipDTO voipDTO) {
        return service.createVoip(voipDTO);
    }

    @Override
    public WebDTO updateWeb(WebDTO webDTO) {
        return service.updateWeb(webDTO);
    }

    @Override
    public WebDTO createWeb(WebDTO webDTO) {
        return service.createWeb(webDTO);
    }
}

