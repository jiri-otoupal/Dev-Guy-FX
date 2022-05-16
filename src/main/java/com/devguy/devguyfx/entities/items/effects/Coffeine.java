package com.devguy.devguyfx.entities.items.effects;

import com.devguy.devguyfx.entities.Player;

public class Coffeine extends ItemEffect {


    public Coffeine(long decayTime) {
        super(decayTime, "Coffeine");
    }

    @Override
    public void apply(Player instigator) {
        instigator.fireRate =75;
    }
}
