package com.devguy.devguyfx.control;

import com.devguy.devguyfx.menus.Menu;
import javafx.scene.input.KeyCode;

public class MenuController extends Controller {
    private final Menu menu;

    public MenuController(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void invokeActionFromKey(KeyCode key) {
        if (key == KeyCode.UP || (key == KeyCode.W)) {
            menu.selectUp();
        } else if (key == KeyCode.DOWN || (key == KeyCode.S)) {
            menu.selectDown();
        } else if (key == KeyCode.ENTER) {
            menu.choose();
        }
    }
}
