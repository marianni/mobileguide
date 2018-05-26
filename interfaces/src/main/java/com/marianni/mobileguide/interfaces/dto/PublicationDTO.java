package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;

public class PublicationDTO implements Serializable {
    private Long id;
    private Long employeeId;
    private String publicationLink;

    public PublicationDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPublicationLink() {
        return publicationLink;
    }

    public void setPublicationLink(String publicationLink) {
        this.publicationLink = publicationLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicationDTO that = (PublicationDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        return publicationLink != null ? publicationLink.equals(that.publicationLink) : that.publicationLink == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 17 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 17 * result + (publicationLink != null ? publicationLink.hashCode() : 0);
        return result;
    }
}
