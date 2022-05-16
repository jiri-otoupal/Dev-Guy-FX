package com.devguy.devguyfx.entities.items.effects;

import com.devguy.devguyfx.entities.Player;

public class HolyWaterEffect extends ItemEffect {


    public HolyWaterEffect(int decayTime) {
        super(decayTime, "HolyWater");
    }

    @Override
    public void apply(Player instigator) {
        instigator.fireRate += 100;
    }
}
