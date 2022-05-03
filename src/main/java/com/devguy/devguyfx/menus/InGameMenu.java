package com.devguy.devguyfx.menus;

import com.devguy.devguyfx.entities.textrender.MenuItemText;
import com.devguy.devguyfx.level.Level;

import java.util.ArrayList;
import java.util.List;

public class InGameMenu extends Menu {
    public InGameMenu(Level currentLevel) {
        super(currentLevel);
    }

    @Override
    public void choose() {
        ArrayList<MenuItemText> items = new ArrayList<>();
        items.add(new MenuItemText(level, "Load Last Save", "load"));
        items.add(new MenuItemText(level, "Save Game", "start"));
        items.add(new MenuItemText(level, "Exit", "exit"));
        setMenuItems(items);
    }

    @Override
    public List<MenuItemText> getMenuItems() {
        return null;
    }
}
