package com.devguy.devguyfx.entities.props.background;

import com.devguy.devguyfx.level.Level;

public class Lamp extends BackgroundProp {

    public Lamp(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]{
                {' ', ' ', '_', '_', '_', '_', ' '},
                {' ', '/', ' ', '_', '_', '_', '\\'},
                {'|', ' ', '|', ' ', ' ', ' ', ' '},
                {'|', ' ', '|', ' ', ' ', ' ', ' '},
                {'|', ' ', '|', ' ', ' ', ' ', ' '},
                {'|', ' ', '|', ' ', ' ', ' ', ' '},
                {'|', ' ', '|', '_', ' ', ' ', ' '},
                {'_', '_', '_', '_', '|', ' ', ' '},
        };
    }
}
