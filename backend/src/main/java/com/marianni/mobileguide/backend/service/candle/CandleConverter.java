package com.marianni.mobileguide.backend.service.candle;

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
        dto.setLectures(candlePlace.getCandleLectures().stream().map(lecture -> toDTO(candlePlace.getId(), lecture)).collect(Collectors.toSet()));
        return dto;
    }

    public static CandlePlace toEntity(final CandlePlace candlePlace, final CandlePlaceDTO dto) {
        candlePlace.setName(dto.getName());
        toEntityLectures(dto.getLectures(), candlePlace); //todo skontrolovat ci treba konvertovat aj aj relacie, lebo oni sa na priamo updateuju z tabov
        return candlePlace;
    }

    private static void toEntityLectures(Set<LectureDTO> lectures, CandlePlace candlePlace) {
    }


}
