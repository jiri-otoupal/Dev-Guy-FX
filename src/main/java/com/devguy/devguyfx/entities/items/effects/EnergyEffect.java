package com.devguy.devguyfx.entities.items.effects;

import com.devguy.devguyfx.entities.Player;

public class EnergyEffect extends ItemEffect {


    public EnergyEffect(long decayTime) {
        super(decayTime, "Energy", "items/EnergyDrink.png");
    }

    @Override
    public void apply(Player instigator) {
        if (applied)
            return;
        applied = true;
        states.put("fireRate", (int) instigator.fireRate);
        instigator.fireRate -= 50;
        System.out.println(instigator.fireRate);
    }

    @Override
    public void deprecate(Player instigator) {
        if (states.isEmpty())
            return;
        instigator.fireRate = states.remove("fireRate");
        System.out.println(instigator.fireRate);
    }
}
