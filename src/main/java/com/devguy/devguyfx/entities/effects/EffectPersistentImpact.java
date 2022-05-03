package com.devguy.devguyfx.entities.effects;

import com.devguy.devguyfx.level.Level;

public class EffectPersistentImpact extends Effect {
    public EffectPersistentImpact(Level currentLevel, float lifeSpanMs) {
        super(currentLevel, lifeSpanMs, 20);
        this.animationState = new char[][][][]{{{{'>'}}, {{':'}}}};
        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
    }


}
