package com.devguy.devguyfx.entities.items.effects;

import com.devguy.devguyfx.DGApplication;
import com.devguy.devguyfx.entities.Player;

import java.util.HashMap;
import java.util.Map;

public abstract class ItemEffect {
    public final String effectImg;
    public final long decayTimeMax;
    public long decayTime;
    public String effectName;
    boolean applied = false;

    public Map<String, Integer> states;

    public ItemEffect(long decayTimeMs, String effectName, String effectImg) {
        this.decayTimeMax = decayTimeMs;
        this.decayTime = decayTimeMs;
        this.effectName = effectName;
        this.effectImg = String.valueOf(DGApplication.class.getResource(effectImg));
        states = new HashMap<>();
    }

    public abstract void apply(Player instigator);

    public abstract void deprecate(Player instigator);
}
