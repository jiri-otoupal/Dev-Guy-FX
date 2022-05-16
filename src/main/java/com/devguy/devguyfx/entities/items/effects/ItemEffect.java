package com.devguy.devguyfx.entities.items.effects;

import com.devguy.devguyfx.entities.Player;

public abstract class ItemEffect {
    public long decayTime;
    public String effectName;

    public ItemEffect(long decayTime, String effectName) {
        this.decayTime = decayTime;
        this.effectName = effectName;
    }

    public abstract void apply(Player instigator);
}
