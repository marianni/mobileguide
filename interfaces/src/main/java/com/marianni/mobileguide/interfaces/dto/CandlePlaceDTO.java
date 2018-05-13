package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CandlePlaceDTO implements Serializable {

    private Long id;
    private String name;
    private Set<LectureDTO> lectures = new HashSet<LectureDTO>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<LectureDTO> getLectures() {
        return lectures;
    }

    public void setLectures(Set<LectureDTO> lectures) {
        this.lectures = lectures;
    }
}
