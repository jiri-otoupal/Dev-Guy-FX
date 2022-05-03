package com.devguy.devguyfx.entities.persistent;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.level.Level;

public class EmptySpace extends Entity1D {
    public EmptySpace(Level currentLevel) {
        super(currentLevel);
        representMap = new char[1][1];
        representMap[0][0] = ' ';
        persistent = false;
    }

    @Override
    public boolean canCollide() {
        return false;
    }
}
