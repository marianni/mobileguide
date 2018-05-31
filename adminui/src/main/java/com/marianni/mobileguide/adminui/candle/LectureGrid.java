package com.marianni.mobileguide.adminui.candle;

import com.marianni.mobileguide.interfaces.dto.LectureDTO;
import com.marianni.mobileguide.interfaces.restclients.RestClients;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.util.Set;

/**
 * @author mariannarachelova
 */

public class LectureGrid extends VerticalLayout{

    private Grid<LectureDTO> grid;
    ListDataProvider<LectureDTO> dataProvider;

    public LectureGrid() {
        this.grid = new Grid<>(LectureDTO.class);
        addComponent(grid);
        grid.setSizeFull();
        grid.setColumns("id", "day", "startOfLesson","endOfLesson","typeOfLesson","code","subject","note");

    }

    public void refresh(Set<LectureDTO> lectures) {
        dataProvider = new ListDataProvider<LectureDTO>(lectures);
        grid.setDataProvider(dataProvider);
    }
}
