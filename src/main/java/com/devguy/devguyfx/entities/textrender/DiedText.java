package com.devguy.devguyfx.entities.textrender;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.level.Level;

public class DiedText extends Entity1D {

    public DiedText(Level currentLevel) {
        super(currentLevel);
        this.representMap = new char[][]{
                {' ', ' ', ' ', ' ', ' ', '_', '_', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '_', '_', ' ', ' ', ' ', '_', '_', '_', ' ', ' ', ' ', ' ', ' ', '_', '_', ' ', ' ', ' ', '_', '_', '_', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '_', '_', ' ', ' '},
                {'\\', ' ', '/', ' ', '/', ' ', ' ', '\\', ' ', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '/', '\\', ' ', ' ', '|', '_', '_', ')', ' ', '|', '_', '_', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '\\', ' ', '|', '_', '_', ' ', ' ', ' ', '/', '\\', ' ', ' ', '|', ' ', ' ', '\\', ' '},
                {' ', '|', ' ', ' ', '\\', '_', '_', '/', ' ', '\\', '_', '_', '/', ' ', ' ', ' ', ' ', '/', '~', '~', '\\', ' ', '|', ' ', ' ', '\\', ' ', '|', '_', '_', '_', ' ', ' ', ' ', ' ', '|', '_', '_', '/', ' ', '|', '_', '_', '_', ' ', '/', '~', '~', '\\', ' ', '|', '_', '_', '/', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };
    }
}