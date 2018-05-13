package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="employee_publications",schema="public")
public class EmployeePublication extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_sequence")
    private Long id;

    @Column(name="publications_link")
    public String publicationLink;

    public EmployeePublication(String publicationLink) {
        this.publicationLink = publicationLink;
    }

    public EmployeePublication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicationLink() {
        return publicationLink;
    }

    public void setPublicationLink(String publicationLink) {
        this.publicationLink = publicationLink;
    }
}
