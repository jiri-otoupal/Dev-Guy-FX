package com.devguy.devguyfx.menus;

import com.devguy.devguyfx.entities.textrender.MenuItemText;
import com.devguy.devguyfx.level.Level;

import java.util.List;

public abstract class Menu implements IMenu {
    protected final Level level;
    private List<MenuItemText> menuItems;
    public int cursor;

    public Menu(Level currentLevel) {
        this.level = currentLevel;
        this.cursor = 0;
    }

    public String getSelectedValue() {
        return this.menuItems.get(this.cursor).value;
    }

    public void clearSelection() {
        if (this.menuItems != null && !this.menuItems.isEmpty())
            for (MenuItemText item : menuItems)
                item.selected = false;
    }

    public void selectAtCursor() {
        if (menuItems == null)
            return;
        clearSelection();
        this.menuItems.get(this.cursor).selected = true;
    }

    public void selectDown() {
        if (cursor < this.menuItems.size() - 1) {
            this.cursor++;
            selectAtCursor();
        }
    }

    public void selectUp() {
        if (cursor > 0) {
            this.cursor--;
            selectAtCursor();
        }
    }


    public void setMenuItems(List<MenuItemText> menuItems) {
        this.menuItems = menuItems;
        selectAtCursor();
    }
}
