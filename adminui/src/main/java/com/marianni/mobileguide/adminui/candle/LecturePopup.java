package com.marianni.mobileguide.adminui.candle;

import com.marianni.mobileguide.interfaces.dto.CandlePlaceDTO;
import com.vaadin.server.Page;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * @author mariannarachelova
 */

public class LecturePopup extends Window {

    CandlePopupTabsheet tabSheet;

    private static float WIDTH_PERCENTAGE = 0.7F;
    private static float HEIGHT_PERCENTAGE = 0.5F;
    private CandlePlaceDTO dto;

    public LecturePopup(){
        setWidth(Page.getCurrent().getBrowserWindowWidth() * WIDTH_PERCENTAGE, Unit.PIXELS);
        setHeight(Page.getCurrent().getBrowserWindowWidth() * HEIGHT_PERCENTAGE, Unit.PIXELS);
        float x = (Page.getCurrent().getBrowserWindowWidth() - getWidth()) / 2;
        float y = (Page.getCurrent().getBrowserWindowHeight() - getHeight())
                / 2;
        setPositionX((int) x);
        setPositionY((int) y);


        tabSheet = new CandlePopupTabsheet();

        VerticalLayout content = new VerticalLayout();
        content.addComponent(tabSheet);

        setContent(content);
    }

    public void open(CandlePlaceDTO candlePlaceDTO) {
        dto = candlePlaceDTO;
        tabSheet.refresh(dto);
        focus();
    }
}
