package com.marianni.mobileguide.adminui.map.popup;

import com.marianni.mobileguide.interfaces.dto.MapDTO;
import com.vaadin.ui.TabSheet;
/**
 * @author mariannarachelova
 */
public class MapPopupTabsheet extends TabSheet {

    private MapForm form;
    private MapDTO mapDTO;

    private TabSheet.Tab mapTab;

    public MapPopupTabsheet() {
        form = new MapForm();
        mapTab = addTab(form,"Map");

    }

    public void refresh(final MapDTO map) {
        this.mapDTO = map;
        form.refresh(this.mapDTO);
    }

    public void saveAll() {
        form.save();
    }
}
