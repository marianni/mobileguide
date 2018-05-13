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
}
