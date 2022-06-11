package com.devguy.devguyfx.entities.props.background;


import com.devguy.devguyfx.level.Level;

public class Column extends BackgroundProp {

    public Column(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]{

                {'|', ' ', '|'},
                {'|', ' ', '|'},
                {'|', ' ', '|'},
                {'|', ' ', '|'},
                {'|', ' ', '|'},
                {'|', ' ', '|'},
                {'|', ' ', '|'},
                {'|', ' ', '|'},
                {'|', ' ', '|'},
                {'_', '_', '_'},
        };
    }
}
