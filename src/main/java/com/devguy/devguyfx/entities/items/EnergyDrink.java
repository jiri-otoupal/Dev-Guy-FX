package com.devguy.devguyfx.entities.items;


import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.entities.Player;
import com.devguy.devguyfx.entities.items.effects.EnergyEffect;
import com.devguy.devguyfx.level.Level;

public class EnergyDrink extends ItemWithEffect {
    public EnergyDrink(Level currentLevel) {
        super(currentLevel, false, "EnergyDrink", 2500);
        this.animationState = new char[][][][]{{{{ '[', '⚡', ']'}}, {{ '[', '▁', ']'}}, {{ '[', '⚡', ']'}}, {{ '[', '▁', ']'}}, {{ '[', '⚡', ']'}}}};
        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
        this.frameDurationMs = 25;
    }

    /**
     * What happens on use of item
     *
     * @param instigator who used item
     * @return result use of item succeeded
     */
    @Override
    public boolean use(Entity1D instigator) {
        Player player = (Player) instigator;
        EnergyEffect energyEffect = new EnergyEffect(effectTicksMsLast);
        player.activeEffects.put(energyEffect.effectName, energyEffect);
        player.sayStatic("Used " + itemName);
        return true;
    }

    /**
     * Decays item by milliseconds
     *
     * @param ticksMs milliseconds to decay from item
     */
    @Override
    public void decayEffectFromItems(long ticksMs) {

    }
}
