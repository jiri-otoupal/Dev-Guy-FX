package com.devguy.devguyfx.entities.props.background;

import com.devguy.devguyfx.level.Level;

public class Computer extends BackgroundProp {

    public Computer(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]{
                {' ', ' ', '_', '_', '_', ' ', ' ', ' ', '_', ' '},
                {' ', '[', '(', '_', ')', ']', ' ', '|', '=', '|'},
                {' ', ' ', '\'', '-', '`', ' ', ' ', '|', '_', '|'},
                {' ', '/', 'm', 'm', 'm', '/', ' ', ' ', '/', ' '},
        };
    }
}
