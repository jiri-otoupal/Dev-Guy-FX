package com.devguy.devguyfx.menus;

import com.devguy.devguyfx.GameController;
import com.devguy.devguyfx.control.MenuController;
import com.devguy.devguyfx.control.PlayerPawnController;
import com.devguy.devguyfx.entities.Player;
import com.devguy.devguyfx.entities.textrender.MenuItemText;
import com.devguy.devguyfx.level.CompanyFight;
import com.devguy.devguyfx.level.ControlsMenuLevel;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.saves.SaveOperator;

import java.util.ArrayList;
import java.util.List;

public class StartMenu extends Menu {

    public StartMenu(Level currentLevel) {
        super(currentLevel);
    }

    @Override
    public List<MenuItemText> getMenuItems() {
        ArrayList<MenuItemText> items = new ArrayList<>();
        items.add(new MenuItemText(level, "Start New Game", "start"));
        items.add(new MenuItemText(level, "Load Last Game Save", "load"));
        items.add(new MenuItemText(level, "Controls", "controls"));
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
                    Player.resetPlayer(levelCompanyFight);
                    this.level.streamer.loadLevel(levelCompanyFight);
                } catch (Level.InvalidTemplateMap e) {
                    e.printStackTrace();
                }
                break;
            case "load":
                Player player = SaveOperator.loadSave("save.xml", this.level.streamer);
                if (player == null) {
                    GameController.showOverlay("No Save Found", 1000);
                    return;
                }
                this.level.streamer.controller = new PlayerPawnController();
                this.level.streamer.player = player;
                this.level.streamer.loadLevel(player.currentLevel);
                break;
            case "controls":
                ControlsMenu controlsMenu = new ControlsMenu(level);
                ControlsMenuLevel controlsMenuLevel = null;
                try {
                    controlsMenuLevel = new ControlsMenuLevel(this.level.streamer.width, this.level.streamer.height, this.level.streamer);
                } catch (Level.InvalidTemplateMap e) {
                    e.printStackTrace();
                }
                this.level.streamer.controller = new MenuController(controlsMenu);
                assert controlsMenuLevel != null;
                this.level.streamer.loadLevel(controlsMenuLevel);
                break;
            case "exit":
                System.exit(0);
        }
    }
}
