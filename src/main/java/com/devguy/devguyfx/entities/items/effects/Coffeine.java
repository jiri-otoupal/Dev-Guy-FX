package com.devguy.devguyfx.entities.items.effects;

import com.devguy.devguyfx.entities.Player;

public class Coffeine extends ItemEffect {


    public Coffeine(int decayTime) {
        super(decayTime, "Coffeine");
    }

    @Override
    public void apply(Player instigator) {
        instigator.fireRate +=75;
    }
}
