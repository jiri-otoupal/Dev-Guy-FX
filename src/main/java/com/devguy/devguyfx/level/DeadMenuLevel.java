package com.devguy.devguyfx.level;

import com.devguy.devguyfx.control.MenuController;
import com.devguy.devguyfx.entities.persistent.EmptySpace;
import com.devguy.devguyfx.entities.textrender.DiedText;
import com.devguy.devguyfx.entities.textrender.LogoText;
import com.devguy.devguyfx.entities.textrender.MenuItemText;
import com.devguy.devguyfx.menus.DiedMenu;
import com.devguy.devguyfx.menus.Menu;
import com.devguy.devguyfx.menus.StartMenu;

import java.util.HashMap;
import java.util.List;

public class DeadMenuLevel extends Level {
    public Menu menu;

    public DeadMenuLevel(int width, int height, Streamer streamer) throws InvalidTemplateMap {
        super("Test Level", width, height, streamer);
        this.doGroundFilling = false;
        this.mapToTranslate = new String[]{
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "        n                                                                       ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "       d                                                                        ",
                "                                                                                ",
                "                                                                                ",
                "        s                                                                       ",
                "                                                                                ",
                "        l                                                                       ",
                "                                                                                ",
                "        e                                                                       ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
        };
    }

    @Override
    public void initializeLinker() {
        Level level = this;

        menu = new DiedMenu(level);
        List<MenuItemText> menuItemList = menu.getMenuItems();

        this.linker = new HashMap<>() {{
            put(' ', new EmptySpace(level));
            put('n', new DiedText(level));
            put('s', menuItemList.get(0));
            put('l', menuItemList.get(1));
            put('e', menuItemList.get(2));
        }};
        this.streamer.controller = new MenuController(menu);
    }
}
