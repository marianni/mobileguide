package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;

public class MapDTO implements Serializable {

    private Long id;


    private String map;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapDTO mapDTO = (MapDTO) o;

        if (id != null ? !id.equals(mapDTO.id) : mapDTO.id != null) return false;
        return map != null ? map.equals(mapDTO.map) : mapDTO.map == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (map != null ? map.hashCode() : 0);
        return result;
    }
}
