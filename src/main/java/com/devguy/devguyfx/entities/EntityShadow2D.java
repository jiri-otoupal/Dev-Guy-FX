package com.devguy.devguyfx.entities;

import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.Point;


public class EntityShadow2D extends Entity1D {


    public EntityShadow2D(Level currentLevel, char c, Entity1D parent) {
        super(currentLevel);
        this.representingChar = c;
        this.shadow_parent = parent;
        this.persistent = parent.persistent;
    }


    @Override
    public char getChar() {
        return representingChar;
    }

    @Override
    public void render(Entity1D[][] map, Point cursor) {
    }


}
