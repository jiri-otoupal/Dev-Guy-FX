package com.devguy.devguyfx.entities.items.effects;

import com.devguy.devguyfx.entities.Player;

public class Caffeine extends ItemEffect {


    public Caffeine(long decayTime) {
        super(decayTime, "Caffeine", "items/coffee.png");
    }

    @Override
    public void apply(Player instigator) {
    }

    @Override
    public void deprecate(Player instigator) {

    }
}
