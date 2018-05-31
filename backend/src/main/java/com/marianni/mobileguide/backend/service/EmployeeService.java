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

/**
 * @author mariannarachelova
 */
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

    public Employee getEmployeeByNameAndTitle(String nameAndTitle) {
        TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_BY_NAME_AND_TITLE, Employee.class).setParameter("nameAndTitle", nameAndTitle);
        List<Employee> results = query.getResultList();
        return results.stream().findFirst().orElse(null);
    }

    public String parseAndCreateEmployees() {
        parser.parseAndCreateEmployees();
        TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_ALL, Employee.class);
        List<Employee> results = query.getResultList();
        Gson gson = new Gson();
        String json = gson.toJson(results);
        return json;
    }

    public List<Employee> findAllWithDiferentNameAndTitle(Set<String> nameAndTitles) {
        TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_ALL_WITH_DIFFERENT_NAME_AND_TITLE, Employee.class).setParameter("nameAndTitles", nameAndTitles);
        List<Employee> results = query.getResultList();
        return results;
    }

    public Set<EmployeeDTO> getEmployees() {
        TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_ALL, Employee.class);
        List<Employee> results = query.getResultList();

        Set<EmployeeDTO> dtos = new HashSet<>();
        results.forEach(employee -> dtos.add(EmployeeConverter.toDTO(employee)));
        return dtos;
    }

    public Set<EmployeeDTO> getOnlyNewEmployees(final Long latestVersion) {
        TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_ALL_NEWER_THAN_VERSION, Employee.class).setParameter("latestDataVersion", latestVersion);
        List<Employee> results = query.getResultList();

        Set<EmployeeDTO> dtos = new HashSet<>();
        results.forEach(employee -> dtos.add(EmployeeConverter.toDTO(employee)));
        return dtos;
    }

    public Set<Long> getNewlyDeletedEmployeeIds(final Long latestVersion) {
        TypedQuery<Long> query = em.createNamedQuery(Employee.FIND_ALL_DELETED_IDS_NEWER_THAN_VERSION, Long.class).setParameter("latestDataVersion", latestVersion);
        return new HashSet<>(query.getResultList());
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
        Employee employee = em.find(Employee.class, id);
        employee.setDeleted(true);
    }

    public void deletePhoneNumber(Long phoneNumberId) {
        em.remove(em.find(EmployeePhoneNumber.class, phoneNumberId));
    }

    public void deletePublication(Long publicationId) {
        em.remove(em.find(EmployeePublication.class, publicationId));
    }

    public void deleteVoip(Long voipId) {
        em.remove(em.find(EmployeeVoip.class, voipId));
    }


    public void deleteWeb(Long webId) {
        em.remove(em.find(EmployeeWeb.class, webId));
    }

    public void deletePlace(Long placeId) {
        em.remove(em.find(EmployeePlace.class, placeId));
    }

    public PlaceDTO updatePlace(PlaceDTO placeDTO) {
        EmployeePlace place = em.find(EmployeePlace.class, placeDTO.getId());
        EmployeeConverter.toEntity(placeDTO, place);
        return EmployeeConverter.toDTO(placeDTO.getEmployeeId(), place);
    }

    public PlaceDTO createPlace(PlaceDTO placeDTO) {
        EmployeePlace place = new EmployeePlace();
        place = EmployeeConverter.toEntity(placeDTO, place);

        Employee employee = em.find(Employee.class, placeDTO.getEmployeeId());
        employee.getEmployeePlaces().add(place);

        em.persist(place);
        return EmployeeConverter.toDTO(placeDTO.getEmployeeId(), place);
    }

    public PhoneNumberDTO updatePhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        EmployeePhoneNumber phoneNumber = em.find(EmployeePhoneNumber.class, phoneNumberDTO.getId());
        EmployeeConverter.toEntity(phoneNumberDTO, phoneNumber);
        return EmployeeConverter.toDTO(phoneNumberDTO.getEmployeeId(), phoneNumber);
    }

    public PhoneNumberDTO createPhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        EmployeePhoneNumber phoneNumber = new EmployeePhoneNumber();
        EmployeeConverter.toEntity(phoneNumberDTO, phoneNumber);

        Employee employee = em.find(Employee.class, phoneNumberDTO.getEmployeeId());
        employee.getEmployeePhoneNumbers().add(phoneNumber);

        em.persist(phoneNumber);
        return EmployeeConverter.toDTO(phoneNumberDTO.getEmployeeId(), phoneNumber);
    }

    public PublicationDTO updatePublication(PublicationDTO publicationDTO) {
        EmployeePublication publication = em.find(EmployeePublication.class, publicationDTO.getId());
        EmployeeConverter.toEntity(publicationDTO, publication);
        return EmployeeConverter.toDTO(publicationDTO.getEmployeeId(), publication);
    }

    public PublicationDTO createPublication(PublicationDTO publicationDTO) {
        EmployeePublication publication = new EmployeePublication();
        EmployeeConverter.toEntity(publicationDTO, publication);

        Employee employee = em.find(Employee.class, publicationDTO.getEmployeeId());
        employee.getEmployeePublications().add(publication);

        em.persist(publication);
        return EmployeeConverter.toDTO(publicationDTO.getEmployeeId(), publication);
    }

    public VoipDTO updateVoip(VoipDTO voipDTO) {
        EmployeeVoip voip = em.find(EmployeeVoip.class, voipDTO.getId());
        EmployeeConverter.toEntity(voipDTO, voip);
        return EmployeeConverter.toDTO(voipDTO.getEmployeeId(), voip);
    }

    public VoipDTO createVoip(VoipDTO voipDTO) {
        EmployeeVoip voip = new EmployeeVoip();
        EmployeeConverter.toEntity(voipDTO, voip);

        Employee employee = em.find(Employee.class, voip.getEmployeeId());
        employee.getEmployeeVoips().add(voip);
        em.persist(voip);
        return EmployeeConverter.toDTO(voipDTO.getEmployeeId(), voip);
    }

    public WebDTO updateWeb(WebDTO webDTO) {
        EmployeeWeb web = em.find(EmployeeWeb.class, webDTO.getId());
        EmployeeConverter.toEntity(webDTO, web);
        return EmployeeConverter.toDTO(webDTO.getEmployeeId(), web);
    }

    public WebDTO createWeb(WebDTO webDTO) {
        EmployeeWeb web = new EmployeeWeb();
        EmployeeConverter.toEntity(webDTO, web);
        Employee employee = em.find(Employee.class, web.getEmployeeId());
        employee.getEmployeeWebs().add(web);
        em.persist(web);
        return EmployeeConverter.toDTO(webDTO.getEmployeeId(), web);
    }

}
