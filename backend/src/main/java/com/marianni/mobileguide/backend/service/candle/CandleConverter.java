package com.marianni.mobileguide.backend.service.candle;

import com.marianni.mobileguide.backend.domain.CandleLecture;
import com.marianni.mobileguide.backend.domain.CandlePlace;
import com.marianni.mobileguide.interfaces.dto.CandlePlaceDTO;
import com.marianni.mobileguide.interfaces.dto.LectureDTO;

import java.util.Set;
import java.util.stream.Collectors;

public class CandleConverter {

    public static CandlePlaceDTO toDTO(final CandlePlace candlePlace){
        CandlePlaceDTO dto = new CandlePlaceDTO();
        dto.setId(candlePlace.getId());
        dto.setName(candlePlace.getName());
        dto.setLectures(candlePlace.getCandleLectures().stream().map(lecture -> toDTO(candlePlace.getId(),lecture)).collect(Collectors.toSet()));
        return dto;
    }

    public static CandlePlace toEntity(final CandlePlace candlePlace, final CandlePlaceDTO dto) {
        candlePlace.setName(dto.getName());
        toEntityLectures(dto.getLectures(), candlePlace); //todo skontrolovat ci treba konvertovat aj aj relacie, lebo oni sa na priamo updateuju z tabov
        return candlePlace;
    }

    public static LectureDTO toDTO(Long placeId, CandleLecture lecture) {
        LectureDTO dto = new LectureDTO();
        dto.setPlaceId(placeId);
        dto.setId(lecture.getId());
        dto.setDay(lecture.getDay());
        dto.setStartOfLesson(lecture.getStartOfLesson());
        dto.setEndOfLesson(lecture.getEndOfLesson());
        dto.setTypeOfLesson(lecture.getTypeOfLesson());
        dto.setCode(lecture.getCode());
        dto.setSubject(lecture.getSubject());
        dto.setNote(lecture.getNote());
        return dto;
    }

    public static CandleLecture toEntity(LectureDTO dto, CandleLecture lecture) {
        lecture.setDay(dto.getDay());
        lecture.setStartOfLesson(dto.getStartOfLesson());
        lecture.setEndOfLesson(dto.getEndOfLesson());
        lecture.setTypeOfLesson(dto.getTypeOfLesson());
        lecture.setCode(dto.getCode());
        lecture.setSubject(dto.getSubject());
        lecture.setNote(dto.getNote());
        return lecture;
    }

    private static void toEntityLectures(Set<LectureDTO> dtos, CandlePlace candlePlace) {
        Set<LectureDTO> newLectures = dtos.stream().filter(dto -> dto.getId() == null).collect(Collectors.toSet());
        for (LectureDTO dto : newLectures) {
            CandleLecture lecture = new CandleLecture();
            toEntity(dto, lecture);
            candlePlace.getCandleLectures().add(lecture);
        }

        Set<LectureDTO> lecturesForUpdate = dtos.stream().filter(dto -> dto.getId() != null).collect(Collectors.toSet());
        for (CandleLecture lecture : candlePlace.getCandleLectures()) {
            LectureDTO dto = lecturesForUpdate.stream().filter(pn -> pn.getId().equals(lecture.getId())).findFirst().orElse(null);
            if (dto != null) {
                toEntity(dto, lecture);
            }
        }
    }


}
