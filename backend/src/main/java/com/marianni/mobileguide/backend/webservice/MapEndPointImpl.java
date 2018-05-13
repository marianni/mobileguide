package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.service.MapService;
import com.marianni.mobileguide.interfaces.dto.MapDTO;
import com.marianni.mobileguide.interfaces.endpoints.MapEndpoint;

import javax.inject.Inject;
import java.util.Set;

public class MapEndPointImpl implements MapEndpoint {

    @Inject
    private MapService service;

    @Override
    public String getMap(Long id) {
        return service.getMap(id);
    }

    @Override
    public Set<MapDTO> getMaps() {
        return service.getMaps();
    }

    @Override
    public MapDTO update(MapDTO map) {
        return service.update(map);
    }

    @Override
    public MapDTO create(MapDTO map) {
        return service.create(map);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
