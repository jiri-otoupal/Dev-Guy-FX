package com.devguy.devguyfx.entities.props.background;

import com.devguy.devguyfx.level.Level;

public class Bone extends BackgroundProp {

    public Bone(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]{
                {'☠'},

        };
    }
}
