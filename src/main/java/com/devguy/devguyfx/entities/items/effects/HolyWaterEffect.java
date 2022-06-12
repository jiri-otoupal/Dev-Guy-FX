package com.devguy.devguyfx.entities.items.effects;

import com.devguy.devguyfx.entities.Player;

public class HolyWaterEffect extends ItemEffect {


    public HolyWaterEffect(long decayTime) {
        super(decayTime, "Holy Water", "items/hwater.png");
    }

    @Override
    public void apply(Player instigator) {
        if (applied)
            return;
        applied = true;
        states.put("dmg", (int) instigator.projectileDamage);
        states.put("mass", instigator.projectileMass);
        states.put("speed", (int) instigator.projectileSpeed);
        instigator.projectileDamage += 10;
        instigator.projectileMass += 25;
        instigator.projectileSpeed += 1.2;
    }

    @Override
    public void deprecate(Player instigator) {
        if (states.isEmpty())
            return;
        instigator.projectileDamage = states.remove("dmg");
        instigator.projectileMass = states.remove("mass");
        instigator.projectileSpeed = states.remove("speed");
    }
}
