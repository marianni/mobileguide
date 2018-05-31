package com.marianni.mobileguide.adminui.candle;

import com.marianni.mobileguide.interfaces.dto.CandlePlaceDTO;
import com.vaadin.ui.TabSheet;

/**
 * @author mariannarachelova
 */

public class CandlePopupTabsheet extends TabSheet{

    private LectureComponent lectureComponent;
    private CandlePlaceDTO candlePlace;


    private TabSheet.Tab candleTab;
    private TabSheet.Tab lectureTab;


    public CandlePopupTabsheet() {
        lectureComponent = new LectureComponent();
        lectureTab = addTab(lectureComponent, "Lectures");
    }

    public void refresh(final CandlePlaceDTO candlePlace) {
        this.candlePlace = candlePlace;
        lectureComponent.refresh(this.candlePlace);
    }
}
