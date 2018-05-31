package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;

public class LectureDTO implements Serializable {

    private Long id;
    private Long placeId;
    private String day;
    private String startOfLesson;
    private String endOfLesson;
    private String typeOfLesson;
    private String code;
    private String subject;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LectureDTO that = (LectureDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (placeId != null ? !placeId.equals(that.placeId) : that.placeId != null) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (startOfLesson != null ? !startOfLesson.equals(that.startOfLesson) : that.startOfLesson != null)
            return false;
        if (endOfLesson != null ? !endOfLesson.equals(that.endOfLesson) : that.endOfLesson != null) return false;
        if (typeOfLesson != null ? !typeOfLesson.equals(that.typeOfLesson) : that.typeOfLesson != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        return note != null ? note.equals(that.note) : that.note == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (startOfLesson != null ? startOfLesson.hashCode() : 0);
        result = 31 * result + (endOfLesson != null ? endOfLesson.hashCode() : 0);
        result = 31 * result + (typeOfLesson != null ? typeOfLesson.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
