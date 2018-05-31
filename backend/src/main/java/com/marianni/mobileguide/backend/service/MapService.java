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

import static com.marianni.mobileguide.backend.domain.Faculty.FIND_ALL;
/**
 * @author mariannarachelova
 */
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
        Faculty faculty = em.find(Faculty.class, id);
        faculty.setDeleted(true);
    }

    //do convertera dorobit pre toto funkciu na convertovanie !!!
    public Set<MapDTO> getMaps() {
        TypedQuery<Faculty> query = em.createNamedQuery(Faculty.FIND_ALL, Faculty.class);
        List<Faculty> results = query.getResultList();

        Set<MapDTO> dtos = new HashSet<>();
        results.forEach(faculty -> dtos.add(MapConverter.toDTO(faculty)));
        return dtos;
    }


    public Set<MapDTO> getOnlyNewMaps(Long latestDataVersion) {
        TypedQuery<Faculty> query = em.createNamedQuery(Faculty.FIND_ALL_NEWER_THAN_VERSION, Faculty.class).setParameter("latestDataVersion", latestDataVersion);
        List<Faculty> results = query.getResultList();

        Set<MapDTO> dtos = new HashSet<>();
        results.forEach(map -> dtos.add(MapConverter.toDTO(map)));
        return dtos;
    }

    public Set<Long> getNewlyDeletedMapIds(final Long latestVersion) {
        TypedQuery<Long> query = em.createNamedQuery(Faculty.FIND_ALL_DELETED_IDS_NEWER_THAN_VERSION, Long.class).setParameter("latestDataVersion", latestVersion);
        return new HashSet<>(query.getResultList());
    }

    public List<Faculty> findAllWithDiferentMap(Set<String> maps) {
        TypedQuery<Faculty> query = em.createNamedQuery(Faculty.FIND_ALL_WITH_DIFFERENT_MAP, Faculty.class).setParameter("map", maps);
        List<Faculty> results = query.getResultList();
        return results;
    }

    public Faculty getMap(String map) {
        TypedQuery<Faculty> query = em.createNamedQuery(Faculty.FIND_BY_MAP, Faculty.class).setParameter("map", map);
        List<Faculty> results = query.getResultList();
        return results.stream().findFirst().orElse(null);
    }
}
