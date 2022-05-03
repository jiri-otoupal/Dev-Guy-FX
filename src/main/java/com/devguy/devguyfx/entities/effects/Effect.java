package com.devguy.devguyfx.entities.effects;

import com.devguy.devguyfx.entities.Animated;
import com.devguy.devguyfx.level.Level;

public class Effect extends Animated {

    public Effect(Level currentLevel, float lifeSpanMs, long animationRateMs) {
        super(currentLevel, false, lifeSpanMs);
        this.persistent = false;
        this.lifeSpan = lifeSpanMs;
        this.loops = true;
        this.frameCounter = 0;
        this.frameDurationMs = animationRateMs;
    }


    @Override
    public void decayEffectFromItems(long ticksMs) {

    }
}
