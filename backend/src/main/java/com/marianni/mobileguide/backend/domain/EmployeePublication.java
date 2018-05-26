package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="employee_publications",schema="public")
public class EmployeePublication extends VersionedEntity implements ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_sequence")
    private Long id;

    @Column(name="publications_link")
    public String publicationLink;

    @Column(name = "employee_id", insertable=false, updatable=false)
    private Long employeeId;

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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void updateParent(EntityManager em) {
        Employee employee = em.find(Employee.class, employeeId);
        employee.setChangedOn(new Date());
    }
}
