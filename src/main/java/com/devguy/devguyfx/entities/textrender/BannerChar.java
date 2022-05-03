package com.devguy.devguyfx.entities.textrender;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.Point;


public class BannerChar extends Entity1D {
    public BannerChar(Level currentLevel, char[][] fontLetter, long lifeSpan, Point location) {
        super(currentLevel);
        this.representMap = fontLetter;
        this.lifeSpan = lifeSpan;
        this.currentLevel.streamer.addListener(this);
        spawn(location);
    }

    @Override
    public void tickEvent(long elapsedMs) {
        long correctedElapsed = elapsedMs == 0 ? 1 : elapsedMs;//1 in case it takes 0ms to render frame
        if (this.lifeSpan - correctedElapsed <= 0) {
            erase();
            return;
        }
        this.lifeSpan -= correctedElapsed;

    }

    public void spawn(Point location) {
        currentLevel.streamer.assignAt(location, this);
    }
}


