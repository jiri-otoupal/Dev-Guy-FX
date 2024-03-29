package com.devguy.devguyfx.entities.effects;

import com.devguy.devguyfx.level.Level;

public class EffectHitPlayer extends Effect {
    public EffectHitPlayer(Level currentLevel, float lifeSpanMs) {
        super(currentLevel, lifeSpanMs, 10);
        this.animationState = new char[][][][]{{{{'▓'}}, {{'▒'}}, {{'░'}}, {{'░'}}}};
        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
    }


}
