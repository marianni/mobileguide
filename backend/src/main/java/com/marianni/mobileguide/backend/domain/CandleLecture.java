package com.marianni.mobileguide.backend.domain;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lectures", schema = "public")
public class CandleLecture extends VersionedEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private Long id;

    @Column(name = "day")
    private String day;

    @Column(name="start_of_lesson")
    private String startOfLesson;

    @Column(name="end_of_lesson")
    private String endOfLesson;

    @Column(name="type_of_lesson")
    private String typeOfLesson;

    @Column(name="code")
    private String code;

    @Column(name="subject")
    private String subject;

    @Column(name="note")
    private String note;

    @ManyToMany(mappedBy = "candleLectures")
    private Set<Employee> employees = new HashSet<>();

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public CandleLecture() {
    }

    public CandleLecture(String day, String since, String endOfLesson, String typeOfLesson, String code, String subject, String note) {
        this.day = day;
        this.startOfLesson = since;
        this.endOfLesson = endOfLesson;
        this.typeOfLesson = typeOfLesson;
        this.code = code;
        this.subject = subject;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartOfLesson() {
        return startOfLesson;
    }

    public void setStartOfLesson(String startOfLesson) {
        this.startOfLesson = startOfLesson;
    }

    public String getEndOfLesson() {
        return endOfLesson;
    }

    public void setEndOfLesson(String endOfLesson) {
        this.endOfLesson = endOfLesson;
    }

    public String getTypeOfLesson() {
        return typeOfLesson;
    }

    public void setTypeOfLesson(String typeOfLesson) {
        this.typeOfLesson = typeOfLesson;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "CandleLecture{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", startOfLesson='" + startOfLesson + '\'' +
                ", endOfLesson='" + endOfLesson + '\'' +
                ", typeOfLesson='" + typeOfLesson + '\'' +
                ", code='" + code + '\'' +
                ", subject='" + subject + '\'' +
                ", note='" + note + '\'' +
                ", employees=" + employees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CandleLecture that = (CandleLecture) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (startOfLesson != null ? !startOfLesson.equals(that.startOfLesson) : that.startOfLesson != null)
            return false;
        if (endOfLesson != null ? !endOfLesson.equals(that.endOfLesson) : that.endOfLesson != null) return false;
        if (typeOfLesson != null ? !typeOfLesson.equals(that.typeOfLesson) : that.typeOfLesson != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        return employees != null ? employees.equals(that.employees) : that.employees == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (startOfLesson != null ? startOfLesson.hashCode() : 0);
        result = 31 * result + (endOfLesson != null ? endOfLesson.hashCode() : 0);
        result = 31 * result + (typeOfLesson != null ? typeOfLesson.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (employees != null ? employees.hashCode() : 0);
        return result;
    }
}
