package com.devguy.devguyfx.entities.items.effects;

import com.devguy.devguyfx.DGApplication;
import com.devguy.devguyfx.entities.Player;

public abstract class ItemEffect {
    public final String effectImg;
    public final long decayTimeMax;
    public long decayTime;
    public String effectName;

    public ItemEffect(long decayTimeMs, String effectName, String effectImg) {
        this.decayTimeMax = decayTimeMs;
        this.decayTime = decayTimeMs;
        this.effectName = effectName;
        this.effectImg = String.valueOf(DGApplication.class.getResource(effectImg));
    }

    public abstract void apply(Player instigator);
}
