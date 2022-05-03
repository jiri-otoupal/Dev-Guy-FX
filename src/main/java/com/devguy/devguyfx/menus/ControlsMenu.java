package com.devguy.devguyfx.menus;

import com.devguy.devguyfx.control.MenuController;
import com.devguy.devguyfx.entities.textrender.MenuItemText;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.level.MainMenuLevel;

import java.util.ArrayList;
import java.util.List;

public class ControlsMenu extends Menu {
    public ControlsMenu(Level currentLevel) {
        super(currentLevel);
    }

    @Override
    public void choose() {
        String value = getSelectedValue();
        if ("back".equals(value)) {
            StartMenu controlsMenu = new StartMenu(level);
            MainMenuLevel controlsMenuLevel = null;
            try {
                controlsMenuLevel = new MainMenuLevel(this.level.streamer.width, this.level.streamer.height, this.level.streamer);
            } catch (Level.InvalidTemplateMap e) {
                e.printStackTrace();
            }
            this.level.streamer.controller = new MenuController(controlsMenu);
            assert controlsMenuLevel != null;
            this.level.streamer.loadLevel(controlsMenuLevel);
        }
    }


    @Override
    public List<MenuItemText> getMenuItems() {
        ArrayList<MenuItemText> items = new ArrayList<>();
        items.add(new MenuItemText(level, "Back", "back"));
        setMenuItems(items);
        return items;
    }
}
