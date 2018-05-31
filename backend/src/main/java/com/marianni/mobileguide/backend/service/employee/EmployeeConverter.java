package com.marianni.mobileguide.backend.service.employee;

import com.marianni.mobileguide.backend.domain.*;
import com.marianni.mobileguide.interfaces.dto.*;

import java.util.Set;
import java.util.stream.Collectors;
/**
 * @author mariannarachelova
 */
public class EmployeeConverter {

    public static EmployeeDTO toDTO(final Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setNameAndTitle(employee.getNameAndTitle());
        dto.setPhoneNumbers(employee.getEmployeePhoneNumbers().stream().map(phone -> toDTO(employee.getId(), phone)).collect(Collectors.toSet()));
        dto.setPlaces(employee.getEmployeePlaces().stream().map(place -> toDTO(employee.getId(), place)).collect(Collectors.toSet()));
        dto.setPublications(employee.getEmployeePublications().stream().map(publication -> toDTO(employee.getId(), publication)).collect(Collectors.toSet()));
        dto.setRelationName(employee.getRelationName());
        dto.setRelationPosition(employee.getRelationPosition());
        dto.setVoips(employee.getEmployeeVoips().stream().map(voip -> toDTO(employee.getId(), voip)).collect(Collectors.toSet()));
        dto.setWebs(employee.getEmployeeWebs().stream().map(web -> toDTO(employee.getId(), web)).collect(Collectors.toSet()));
        return dto;
    }

    //PLACES
    public static PlaceDTO toDTO(Long employeeId, EmployeePlace place) {
        PlaceDTO dto = new PlaceDTO();
        dto.setEmployeeId(employeeId);
        dto.setId(place.getId());
        dto.setPlace(place.getPlace());
        return dto;
    }

    public static EmployeePlace toEntity(PlaceDTO dto, EmployeePlace place) {
        place.setPlace(dto.getPlace());
        place.setEmployeeId(dto.getEmployeeId());
        return place;
    }

    public static void toEntityPlaces(Set<PlaceDTO> dtos, Employee employee) {
        Set<PlaceDTO> newPlaces = dtos.stream().filter(dto -> dto.getId() == null).collect(Collectors.toSet());

        for (PlaceDTO dto : newPlaces) {
            EmployeePlace place = new EmployeePlace();
            toEntity(dto, place);
            employee.getEmployeePlaces().add(place);
        }

        Set<PlaceDTO> placesForUpdate = dtos.stream().filter(dto -> dto.getId() != null).collect(Collectors.toSet());
        for (EmployeePlace place : employee.getEmployeePlaces()) {
            PlaceDTO dto = placesForUpdate.stream().filter(pl -> pl.getId().equals(place.getId())).findFirst().orElse(null);
            if (dto != null) {
                toEntity(dto, place);
            }
        }
    }

    //PHONE NUMBERS
    public static PhoneNumberDTO toDTO(Long employeeId, EmployeePhoneNumber phoneNumber) {
        PhoneNumberDTO dto = new PhoneNumberDTO();
        dto.setEmployeeId(employeeId);
        dto.setId(phoneNumber.getId());
        dto.setPhoneNumber(phoneNumber.getPhoneNumber());
        return dto;
    }

    public static EmployeePhoneNumber toEntity(PhoneNumberDTO dto, EmployeePhoneNumber phoneNumber) {
        phoneNumber.setPhoneNumber(dto.getPhoneNumber());
        phoneNumber.setEmployeeId(dto.getEmployeeId());
        return phoneNumber;
    }

    public static void toEntityPhoneNumbers(Set<PhoneNumberDTO> dtos, Employee employee) {
        Set<PhoneNumberDTO> newPhoneNumbers = dtos.stream().filter(dto -> dto.getId() == null).collect(Collectors.toSet());
        for (PhoneNumberDTO dto : newPhoneNumbers) {
            EmployeePhoneNumber phoneNumber = new EmployeePhoneNumber();
            toEntity(dto, phoneNumber);
            employee.getEmployeePhoneNumbers().add(phoneNumber);
        }

        Set<PhoneNumberDTO> phoneNumbersForUpdate = dtos.stream().filter(dto -> dto.getId() != null).collect(Collectors.toSet());
        for (EmployeePhoneNumber phoneNumber : employee.getEmployeePhoneNumbers()) {
            PhoneNumberDTO dto = phoneNumbersForUpdate.stream().filter(pn -> pn.getId().equals(phoneNumber.getId())).findFirst().orElse(null);
            if (dto != null) {
                toEntity(dto, phoneNumber);
            }
        }
    }

    //PUBLICATIONS
    public static PublicationDTO toDTO(Long employeeId, EmployeePublication publication) {
        PublicationDTO dto = new PublicationDTO();
        dto.setEmployeeId(employeeId);
        dto.setId(publication.getId());
        dto.setPublicationLink(publication.getPublicationLink());
        return dto;
    }

    public static EmployeePublication toEntity(PublicationDTO dto, EmployeePublication publication) {
        publication.setPublicationLink(dto.getPublicationLink());
        publication.setEmployeeId(dto.getEmployeeId());
        return publication;
    }

    public static void toEntityPublications(Set<PublicationDTO> dtos, Employee employee) {
        Set<PublicationDTO> newPublications = dtos.stream().filter(dto -> dto.getId() == null).collect(Collectors.toSet());
        for (PublicationDTO dto : newPublications) {
            EmployeePublication publication = new EmployeePublication();
            toEntity(dto, publication);
            employee.getEmployeePublications().add(publication);
        }

        Set<PublicationDTO> publicationForUpdate = dtos.stream().filter(dto -> dto.getId() != null).collect(Collectors.toSet());
        for (EmployeePublication publication : employee.getEmployeePublications()) {
            PublicationDTO dto = publicationForUpdate.stream().filter(pb -> pb.getId().equals(publication.getId())).findFirst().orElse(null);
            if (dto != null) {
                toEntity(dto, publication);
            }
        }
    }

    //VOIPS
    public static VoipDTO toDTO(Long employeeId, EmployeeVoip voip) {
        VoipDTO dto = new VoipDTO();
        dto.setEmployeeId(employeeId);
        dto.setId(voip.getId());
        dto.setVoip(voip.getVoip().toString());
        return dto;
    }

    public static EmployeeVoip toEntity(VoipDTO dto, EmployeeVoip voip) {
        voip.setVoip(Integer.valueOf(dto.getVoip()));
        voip.setEmployeeId(dto.getEmployeeId());
        return voip;
    }

    public static void toEntityVoips(Set<VoipDTO> dtos, Employee employee) {
        Set<VoipDTO> newVoips = dtos.stream().filter(dto -> dto.getId() == null).collect(Collectors.toSet());
        for (VoipDTO dto : newVoips) {
            EmployeeVoip voip = new EmployeeVoip();
            toEntity(dto, voip);
            employee.getEmployeeVoips().add(voip);
        }

        Set<VoipDTO> voipsForUpdate = dtos.stream().filter(dto -> dto.getId() != null).collect(Collectors.toSet());
        for (EmployeeVoip voip : employee.getEmployeeVoips()) {
            VoipDTO dto = voipsForUpdate.stream().filter(pb -> pb.getId().equals(voip.getId())).findFirst().orElse(null);
            if (dto != null) {
                toEntity(dto, voip);
            }
        }
    }

    //WEBS
    public static WebDTO toDTO(Long employeeId, EmployeeWeb web) {
        WebDTO dto = new WebDTO();
        dto.setEmployeeId(employeeId);
        dto.setId(web.getId());
        dto.setWeb(web.getWeb());
        return dto;
    }

    public static EmployeeWeb toEntity(WebDTO dto, EmployeeWeb web) {
        web.setWeb(dto.getWeb());
        web.setEmployeeId(dto.getEmployeeId());
        return web;
    }

    public static void toEntityWebss(Set<WebDTO> dtos, Employee employee) {
        Set<WebDTO> newWebs = dtos.stream().filter(dto -> dto.getId() == null).collect(Collectors.toSet());
        for (WebDTO dto : newWebs) {
            EmployeeWeb web = new EmployeeWeb();
            toEntity(dto, web);
            employee.getEmployeeWebs().add(web);
        }

        Set<WebDTO> websForUpdate = dtos.stream().filter(dto -> dto.getId() != null).collect(Collectors.toSet());
        for (EmployeeWeb web : employee.getEmployeeWebs()) {
            WebDTO dto = websForUpdate.stream().filter(pb -> pb.getId().equals(web.getId())).findFirst().orElse(null);
            if (dto != null) {
                toEntity(dto, web);
            }
        }
    }

    public static Employee toEntity(final Employee employee, final EmployeeDTO dto) {
        employee.setNameAndTitle(dto.getNameAndTitle());
        employee.setRelationName(dto.getRelationName());
        employee.setRelationPosition(dto.getRelationPosition());
        toEntityPlaces(dto.getPlaces(), employee); //todo skontrolovat ci treba konvertovat aj aj relacie, lebo oni sa na priamo updateuju z tabov
        return employee;
    }
}
