package com.devguy.devguyfx.entities.props.background;

import com.devguy.devguyfx.level.Level;

public class TorchLight extends BackgroundProp {

    public TorchLight(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]{
                {' ', ' ', ')', ' ', ' '},
                {' ', ')', ' ', '\\', ' '},
                {'/', ' ', ')', ' ', '('},
                {'\\', '(', '_', ')', '/'},
        };
    }
}
