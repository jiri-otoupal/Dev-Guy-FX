package com.devguy.devguyfx.entities.props.background;

import com.devguy.devguyfx.level.Level;

public class Window extends BackgroundProp {
    public Window(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]
                {
                        {' ', '_', '_', '_', ' '},
                        {'/', ' ', ' ', ' ', '\\'},
                        {'|', ' ', ' ', ' ', '|'},
                        {'\\', '_', '_', '_', '/'},
                };

    }


}
