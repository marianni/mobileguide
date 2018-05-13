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
}
