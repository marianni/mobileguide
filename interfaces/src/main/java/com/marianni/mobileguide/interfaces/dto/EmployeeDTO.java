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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeDTO that = (EmployeeDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nameAndTitle != null ? !nameAndTitle.equals(that.nameAndTitle) : that.nameAndTitle != null) return false;
        if (relationName != null ? !relationName.equals(that.relationName) : that.relationName != null) return false;
        if (relationPosition != null ? !relationPosition.equals(that.relationPosition) : that.relationPosition != null)
            return false;

        if (phoneNumbers.size() != that.phoneNumbers.size() || !phoneNumbers.stream().allMatch(p -> that.phoneNumbers.contains(p))) {
            return false;
        }

        if (places.size() != that.places.size() || !places.stream().allMatch(p -> that.places.contains(p))) {
            return false;
        }

        if (publications.size() != that.publications.size() || !publications.stream().allMatch(p -> that.publications.contains(p))) {
            return false;
        }

        if (voips.size() != that.voips.size() || !voips.stream().allMatch(p -> that.voips.contains(p))) {
            return false;
        }
        return webs.size() == that.webs.size() && webs.stream().allMatch(p -> that.webs.contains(p));
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nameAndTitle != null ? nameAndTitle.hashCode() : 0);
        result = 31 * result + (relationName != null ? relationName.hashCode() : 0);
        result = 31 * result + (relationPosition != null ? relationPosition.hashCode() : 0);
        result = 31 * result + phoneNumbers.hashCode();
        result = 31 * result + places.hashCode();
        result = 31 * result + publications.hashCode();
        result = 31 * result + voips.hashCode();
        result = 31 * result + webs.hashCode();
        return result;
    }
}
