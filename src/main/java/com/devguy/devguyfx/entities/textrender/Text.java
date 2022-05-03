package com.devguy.devguyfx.entities.textrender;

import com.devguy.devguyfx.entities.Animated;
import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.entities.EntityShadow2D;
import com.devguy.devguyfx.entities.Player;
import com.devguy.devguyfx.level.Level;

import com.devguy.devguyfx.structure.Point;

public abstract class Text extends Animated implements IText {
    public Entity1D owner;

    public Text(Level currentLevel, boolean enableGravity, float lifeSpan) {
        super(currentLevel, enableGravity, lifeSpan);
    }

    public Text(Level currentLevel, boolean enableGravity, float lifeSpan, Entity1D owner) {
        super(currentLevel, enableGravity, lifeSpan);
        this.owner = owner;
    }

    @Override
    public void iterationRenderFunction(Entity1D[][] map, Point cursor, int map_x, int map_y, int ent_x, int ent_y) {
        if (!currentLevel.streamer.isValidLocation(map_x, map_y) || map[map_y][map_x].persistent)
            return;
        char currentRenderedChar = representMap[ent_y][ent_x];
        map[map_y][map_x] = new EntityShadow2D(this.currentLevel, currentRenderedChar, this);
        bodyPositions.add(new Point(map_x, map_y));
    }

    public void spawnAtPlayer() {
        Player player = currentLevel.streamer.controller.controlledAliveEntity;
        Point renderPoint = player.textRenderPoint;
        if (currentLevel.streamer.getInstanceAt(renderPoint).lifeSpan <= 0)
            currentLevel.streamer.assignAt(new Point(renderPoint.x, renderPoint.y - 1), this);
        owner = player;
        owner.currentRenderedText = this;
    }

    public void spawn(Point location) {
        if (owner == null && currentLevel.streamer.getInstanceAt(location).lifeSpan <= 0)
            currentLevel.streamer.assignAt(new Point(location.x, location.y - 1), this);
        else if (owner != null && owner.currentRenderedText != null && owner.currentRenderedText.lifeSpan <= 0) {
            currentLevel.streamer.assignAt(new Point(location.x, location.y - 1), this);
            owner.currentRenderedText = this;
        } else if (owner != null && owner.currentRenderedText == null) {
            currentLevel.streamer.assignAt(new Point(location.x, location.y - 1), this);
            owner.currentRenderedText = this;
        }
    }
}
