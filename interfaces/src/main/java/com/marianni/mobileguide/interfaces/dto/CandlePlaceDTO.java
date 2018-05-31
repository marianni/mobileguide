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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CandlePlaceDTO that = (CandlePlaceDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        //return lectures != null ? lectures.equals(that.lectures) : that.lectures == null;

        return lectures.size() == that.lectures.size() && lectures.stream().allMatch(p -> that.lectures.contains(p));
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lectures != null ? lectures.hashCode() : 0);
        return result;
    }
}
