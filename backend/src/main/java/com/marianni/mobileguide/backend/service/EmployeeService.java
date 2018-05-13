package com.marianni.mobileguide.backend.service;

import com.google.gson.Gson;
import com.marianni.mobileguide.backend.domain.*;
import com.marianni.mobileguide.backend.service.employee.EmployeeConverter;
import com.marianni.mobileguide.backend.webservice.HTMLParserEmployees;
import com.marianni.mobileguide.interfaces.dto.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Stateless
public class EmployeeService {

    @Inject
    private HTMLParserEmployees parser;

    @PersistenceContext
    private EntityManager em;

    public String getEmployee(Long id) {
        Employee employee = em.find(Employee.class, id);
        return employee.getNameAndTitle();
    }

    public String parseAndCreateEmployees() {
        parser.parseAndCreateEmployees();
        TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_ALL, Employee.class);
        List<Employee> results = query.getResultList();
        Gson gson = new Gson();
        String json = gson.toJson(results);
        return json;
    }

    public Set<EmployeeDTO> getEmployees() {
        TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_ALL, Employee.class);
        List<Employee> results = query.getResultList();

        Set<EmployeeDTO> dtos = new HashSet<>();
        results.forEach(employee -> dtos.add(EmployeeConverter.toDTO(employee)));
        return dtos;
    }

    public Set<EmployeeDTO> getOnlyNewEmployees(final Long latestVersion) {
        TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_ALL_NEWER_THAN_VERSION, Employee.class).setParameter("latestDataVersion",latestVersion);
        List<Employee> results = query.getResultList();

        Set<EmployeeDTO> dtos = new HashSet<>();
        results.forEach(employee -> dtos.add(EmployeeConverter.toDTO(employee)));
        return dtos;
    }

    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        Employee employee = em.find(Employee.class, employeeDTO.getId());
        EmployeeConverter.toEntity(employee, employeeDTO);
        return EmployeeConverter.toDTO(employee);
    }

    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        EmployeeConverter.toEntity(employee, employeeDTO);
        em.persist(employee);
        EmployeeDTO createdDto = EmployeeConverter.toDTO(employee);
        return createdDto;
    }

    public void delete(final Long id) {
        em.remove(em.find(Employee.class, id));

    }

    public void deletePhoneNumber(Long phoneNumberId) {
        em.remove(em.find(EmployeePhoneNumber.class,phoneNumberId));
    }

    public void deletePublication(Long publicationId){em.remove(em.find(EmployeePublication.class,publicationId));}

    public void deleteVoip(Long voipId) {em.remove(em.find(EmployeeVoip.class,voipId));}


    public void deleteWeb(Long webId) {em.remove(em.find(EmployeeWeb.class,webId));}

    public void deletePlace(Long placeId) {em.remove(em.find(EmployeePlace.class,placeId));}

    public PlaceDTO updatePlace(PlaceDTO placeDTO) {
        EmployeePlace place = em.find(EmployeePlace.class, placeDTO.getId());
        EmployeeConverter.toEntity(placeDTO,place);
        return EmployeeConverter.toDTO(placeDTO.getEmployeeId(), place);
    }

    public PlaceDTO createPlace(PlaceDTO placeDTO) {
        Employee employee = em.find(Employee.class, placeDTO.getEmployeeId());
        EmployeePlace place = new EmployeePlace();
        //EmployeeConverter.toEntity(placeDTO,place);
        place = EmployeeConverter.toEntity(placeDTO,place);
        employee.getEmployeePlaces().add(place);
        em.persist(place);
        return EmployeeConverter.toDTO(placeDTO.getEmployeeId(), place);
    }

    public PhoneNumberDTO updatePhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        EmployeePhoneNumber phoneNumber = em.find(EmployeePhoneNumber.class, phoneNumberDTO.getId());
        EmployeeConverter.toEntity(phoneNumberDTO,phoneNumber);
        return EmployeeConverter.toDTO(phoneNumberDTO.getEmployeeId(), phoneNumber);
    }

    public PhoneNumberDTO createPhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        Employee employee = em.find(Employee.class, phoneNumberDTO.getEmployeeId());
        EmployeePhoneNumber phoneNumber = new EmployeePhoneNumber();
        EmployeeConverter.toEntity(phoneNumberDTO,phoneNumber);
        employee.getEmployeePhoneNumbers().add(phoneNumber);
        em.persist(phoneNumber);
        return EmployeeConverter.toDTO(phoneNumberDTO.getEmployeeId(), phoneNumber);
    }

    public PublicationDTO updatePublication(PublicationDTO publicationDTO) {
        EmployeePublication publication = em.find(EmployeePublication.class, publicationDTO.getId());
        EmployeeConverter.toEntity(publicationDTO,publication);
        return EmployeeConverter.toDTO(publicationDTO.getEmployeeId(), publication);
    }

    public PublicationDTO createPublication(PublicationDTO publicationDTO) {
        Employee employee = em.find(Employee.class, publicationDTO.getEmployeeId());
        EmployeePublication publication = new EmployeePublication();
        EmployeeConverter.toEntity(publicationDTO,publication);
        employee.getEmployeePublications().add(publication);
        em.persist(publication);
        return EmployeeConverter.toDTO(publicationDTO.getEmployeeId(), publication);
    }

    public VoipDTO updateVoip(VoipDTO voipDTO) {
        EmployeeVoip voip = em.find(EmployeeVoip.class, voipDTO.getId());
        EmployeeConverter.toEntity(voipDTO,voip);
        return EmployeeConverter.toDTO(voipDTO.getEmployeeId(), voip);
    }

    public VoipDTO createVoip(VoipDTO voipDTO) {
        Employee employee = em.find(Employee.class, voipDTO.getEmployeeId());
        EmployeeVoip voip = new EmployeeVoip();
        EmployeeConverter.toEntity(voipDTO,voip);
        employee.getEmployeeVoips().add(voip);
        em.persist(voip);
        return EmployeeConverter.toDTO(voipDTO.getEmployeeId(), voip);
    }

    public WebDTO updateWeb(WebDTO webDTO) {
        EmployeeWeb web = em.find(EmployeeWeb.class, webDTO.getId());
        EmployeeConverter.toEntity(webDTO,web);
        return EmployeeConverter.toDTO(webDTO.getEmployeeId(), web);
    }

    public WebDTO createWeb(WebDTO webDTO) {
        Employee employee = em.find(Employee.class, webDTO.getEmployeeId());
        EmployeeWeb web = new EmployeeWeb();
        EmployeeConverter.toEntity(webDTO,web);
        employee.getEmployeeWebs().add(web);
        em.persist(web);
        return EmployeeConverter.toDTO(webDTO.getEmployeeId(), web);
    }
}
