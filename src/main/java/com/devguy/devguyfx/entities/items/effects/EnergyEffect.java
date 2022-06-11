package com.devguy.devguyfx.entities.items.effects;

import com.devguy.devguyfx.entities.Player;

public class EnergyEffect extends ItemEffect {


    public EnergyEffect(long decayTime) {
        super(decayTime, "Energy", "items/EnergyDrink.png");
    }

    @Override
    public void apply(Player instigator) {
        instigator.fireRate = 85;
    }
}
