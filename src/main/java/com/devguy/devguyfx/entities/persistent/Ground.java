package com.devguy.devguyfx.entities.persistent;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.level.Level;


public class Ground extends Entity1D {
    public Ground(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]{
                {'█'}};
        persistent = true;
    }

}
