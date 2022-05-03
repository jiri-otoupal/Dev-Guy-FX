package com.devguy.devguyfx.menus;

import com.devguy.devguyfx.entities.textrender.MenuItemText;

import java.util.List;

public interface IMenu {
    void choose();

    List<MenuItemText> getMenuItems();

    String getSelectedValue();
}
