package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees", schema = "public")
@NamedQueries({
        @NamedQuery(name = Employee.FIND_ALL, query = "SELECT e FROM Employee e")
        //@NamedQuery(name = Employee.FIND_ALL, query = "SELECT e FROM Employee e WHERE deleted = false"),
        //@NamedQuery(name = Employee.FIND_ALL_NEWER_THAN_VERSION, query = "SELECT e FROM Employee e WHERE e.dataVersion > :latestDataVersion AND deleted = false"),
        //@NamedQuery(name = Employee.FIND_ALL_DELETED_IDS_NEWER_THAN_VERSION, query = "SELECT e.id FROM Employee e WHERE e.dataVersion > :latestDataVersion AND deleted = true")
})

// comment
public class Employee extends SoftDeleteEntity {


    public static final String FIND_ALL = "Employee.findAll";
    public static final String FIND_ALL_NEWER_THAN_VERSION = "Employee.findAllNewerThanVersion";
    public static final String FIND_ALL_DELETED_IDS_NEWER_THAN_VERSION = "Employee.findAllDeletedIdsNewerThanVersion";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private Long id;

    @Column(name = "name_and_title")
    private String nameAndTitle;

    @Column(name = "relation_name")
    private String relationName;

    @Column(name = "relation_position")
    private String relationPosition;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "employee_id")
    private Set<EmployeePhoneNumber> employeePhoneNumbers = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "employee_id")
    private Set<EmployeePlace> employeePlaces = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "employee_id")
    private Set<EmployeePublication> employeePublications = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "employee_id")
    private Set<EmployeeVoip> employeeVoips = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "employee_id")
    private Set<EmployeeWeb> employeeWebs = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="teacher_lecture",
            joinColumns = {@JoinColumn(name="employee_id")},
            inverseJoinColumns = {@JoinColumn(name="lecture_id")}
    )
    private Set<CandleLecture> candleLectures = new HashSet<>();

    public Set<CandleLecture> getCandleLectures() {
        return candleLectures;
    }

    public void setCandleLectures(Set<CandleLecture> candleLectures) {
        this.candleLectures = candleLectures;
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

    public Set<EmployeeWeb> getEmployeeWebs() {
        return employeeWebs;
    }

    public void setEmployeeWebs(Set<EmployeeWeb> employeeWebs) {
        this.employeeWebs = employeeWebs;
    }

    public Set<EmployeeVoip> getEmployeeVoips() {
        return employeeVoips;
    }

    public void setEmployeeVoips(Set<EmployeeVoip> employeeVoips) {
        this.employeeVoips = employeeVoips;
    }

    public Set<EmployeePublication> getEmployeePublications() {
        return employeePublications;
    }

    public void setEmployeePublications(Set<EmployeePublication> employeePublications) {
        this.employeePublications = employeePublications;
    }

    public Set<EmployeePlace> getEmployeePlaces() {
        return employeePlaces;
    }

    public void setEmployeePlaces(Set<EmployeePlace> employeePlaces) {
        this.employeePlaces = employeePlaces;
    }

    public Set<EmployeePhoneNumber> getEmployeePhoneNumbers() {
        return employeePhoneNumbers;
    }

    public void setEmployeePhoneNumbers(Set<EmployeePhoneNumber> employeePhoneNumbers) {
        this.employeePhoneNumbers = employeePhoneNumbers;
    }

    public Employee() {
    }


    public Employee(String nameAndTitle) {
        this.nameAndTitle = nameAndTitle;
    }


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

    /*
    public void addNumber(EmployeePhoneNumber number){
        getEmployeePhoneNumbers().add(number);
        //setChangedOn(new Date());
    }

    public void addPlace(EmployeePlace place){
        getEmployeePlaces().add(place);
        //setChangedOn(new Date());
    }

    public void addWeb(EmployeeWeb web){
        getEmployeeWebs().add(web);
        //setChangedOn(new Date());
    }

    public void addPublication(EmployeePublication publication){
        getEmployeePublications().add(publication);
        //setChangedOn(new Date());
    }

    public void addVoip(EmployeeVoip voip){
        getEmployeeVoips().add(voip);
        //setChangedOn(new Date());
    }
*/

}
