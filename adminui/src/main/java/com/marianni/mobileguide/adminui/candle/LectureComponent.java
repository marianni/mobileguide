package com.marianni.mobileguide.adminui.candle;

import com.marianni.mobileguide.interfaces.dto.CandlePlaceDTO;
import com.marianni.mobileguide.interfaces.dto.LectureDTO;
import com.vaadin.ui.VerticalLayout;

public class LectureComponent extends VerticalLayout {

    private LectureGrid grid;
    private CandlePlaceDTO candlePlaceDTO;
    private LectureDTO lectureDTO;


    public LectureComponent(){
        grid = new LectureGrid();
        addComponent(grid);
    }
    public void refresh(CandlePlaceDTO candlePlaceDTO) {
        this.candlePlaceDTO = candlePlaceDTO;
        grid.refresh(this.candlePlaceDTO.getLectures());
    }
}
