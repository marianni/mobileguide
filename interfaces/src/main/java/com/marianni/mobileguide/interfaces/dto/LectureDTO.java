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
}
