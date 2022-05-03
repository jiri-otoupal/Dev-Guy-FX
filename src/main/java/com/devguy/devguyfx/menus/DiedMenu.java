package com.devguy.devguyfx.menus;

import com.devguy.devguyfx.control.PlayerPawnController;
import com.devguy.devguyfx.entities.Player;
import com.devguy.devguyfx.entities.textrender.MenuItemText;
import com.devguy.devguyfx.level.CompanyFight;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.saves.SaveOperator;

import java.util.ArrayList;
import java.util.List;

public class DiedMenu extends Menu {

    public DiedMenu(Level currentLevel) {
        super(currentLevel);
    }

    @Override
    public List<MenuItemText> getMenuItems() {
        ArrayList<MenuItemText> items = new ArrayList<>();
        items.add(new MenuItemText(level, "Start New Game", "start"));
        items.add(new MenuItemText(level, "Load Game", "load"));
        items.add(new MenuItemText(level, "Exit", "exit"));
        setMenuItems(items);
        return items;
    }


    @Override
    public void choose() {
        String value = getSelectedValue();
        switch (value) {
            case "start":
                try {
                    this.level.streamer.controller = new PlayerPawnController();
                    CompanyFight levelCompanyFight = new CompanyFight(this.level.streamer.width, this.level.streamer.height, this.level.streamer);
                    this.level.streamer.loadLevel(levelCompanyFight);
                } catch (Level.InvalidTemplateMap e) {
                    e.printStackTrace();
                }
                break;
            case "load":
                Player player = SaveOperator.loadSave("save.xml", this.level.streamer);
                if (player == null)
                    return;
                this.level.streamer.controller = new PlayerPawnController();
                this.level.streamer.player = player;
                this.level.streamer.loadLevel(player.currentLevel);
                break;
            case "exit":
                System.exit(0);
        }
    }
}
