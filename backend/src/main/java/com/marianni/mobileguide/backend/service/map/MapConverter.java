package com.marianni.mobileguide.backend.service.map;

import com.marianni.mobileguide.backend.domain.Faculty;
import com.marianni.mobileguide.interfaces.dto.MapDTO;
/**
 * @author mariannarachelova
 */
public class MapConverter {

    public static MapDTO toDTO(final Faculty faculty) {
        MapDTO dto = new MapDTO();
        dto.setId(faculty.getId());
        dto.setMap(faculty.getMap());
        return dto;
    }

    public static Faculty toEntity(final Faculty faculty, final MapDTO dto) {
        faculty.setMap(dto.getMap());
        return faculty;
    }


}
