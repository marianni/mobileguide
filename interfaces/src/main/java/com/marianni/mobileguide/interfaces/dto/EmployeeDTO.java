package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDTO implements Serializable {

    private Long id;


    private String nameAndTitle;


    private String relationName;


    private String relationPosition;


    private Set<PhoneNumberDTO> phoneNumbers = new HashSet<PhoneNumberDTO>();

    private Set<PlaceDTO> places = new HashSet<PlaceDTO>();


    private Set<PublicationDTO> publications = new HashSet<PublicationDTO>();


    private Set<VoipDTO> voips = new HashSet<VoipDTO>();


    private Set<WebDTO> webs = new HashSet<WebDTO>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAndTitle() {
        return nameAndTitle;
    }

    public void setNameAndTitle(String nameAndTitle) {
        this.nameAndTitle = nameAndTitle;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getRelationPosition() {
        return relationPosition;
    }

    public void setRelationPosition(String relationPosition) {
        this.relationPosition = relationPosition;
    }

    public Set<PhoneNumberDTO> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumberDTO> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Set<PlaceDTO> getPlaces() {
        return places;
    }

    public void setPlaces(Set<PlaceDTO> places) {
        this.places = places;
    }

    public Set<PublicationDTO> getPublications() {
        return publications;
    }

    public void setPublications(Set<PublicationDTO> publications) {
        this.publications = publications;
    }

    public Set<VoipDTO> getVoips() {
        return voips;
    }

    public void setVoips(Set<VoipDTO> voips) {
        this.voips = voips;
    }

    public Set<WebDTO> getWebs() {
        return webs;
    }

    public void setWebs(Set<WebDTO> webs) {
        this.webs = webs;
    }
}
