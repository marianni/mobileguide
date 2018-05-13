package com.marianni.mobileguide.backend.service;


import com.marianni.mobileguide.backend.domain.Faculty;
import com.marianni.mobileguide.backend.service.map.MapConverter;
import com.marianni.mobileguide.interfaces.dto.MapDTO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class MapService {

    @PersistenceContext
    private EntityManager em;


    public String getMap(Long id) {
        Faculty faculty = em.find(Faculty.class, id);
        return faculty.getMap();
    }

    public MapDTO update(MapDTO mapDTO) {
        Faculty faculty = em.find(Faculty.class, mapDTO.getId());
        MapConverter.toEntity(faculty, mapDTO);
        return MapConverter.toDTO(faculty);
    }

    public MapDTO create(MapDTO mapDTO){
        Faculty faculty = new Faculty();
        MapConverter.toEntity(faculty,mapDTO);
        em.persist(faculty);
        MapDTO createdDTO = MapConverter.toDTO(faculty);
        return createdDTO;
    }

    public void delete(final Long id){
        em.remove(em.find(Faculty.class,id));
    }

    //do convertera dorobit pre toto funkciu na convertovanie !!!
    public Set<MapDTO> getMaps() {
        TypedQuery<Faculty> query = em.createNamedQuery("Faculty.findAll", Faculty.class);
        List<Faculty> results = query.getResultList();

        Set<MapDTO> dtos = new HashSet<>();
        results.forEach(faculty -> dtos.add(MapConverter.toDTO(faculty)));
        return dtos;
    }


}
