package com.devguy.devguyfx.entities.props.background;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.entities.EntityShadow2D;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.Point;


public abstract class BackgroundProp extends Entity1D {

    public BackgroundProp(Level currentLevel) {
        super(currentLevel);
        currentLevel.backgroundProps.add(this);
    }

    @Override
    public void iterationRenderFunction(Entity1D[][] map, Point cursor, int map_x, int map_y, int ent_x, int ent_y) {
        if (map[map_y][map_x].getChar() != ' ')
            return;
        map[map_y][map_x] = new EntityShadow2D(this.currentLevel, representMap[ent_y][ent_x], this);
        bodyPositions.add(new Point(map_x, map_y));
    }

    @Override
    public boolean canCollide() {
        return false;
    }
}
