package com.devguy.devguyfx.entities.items;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.entities.Player;
import com.devguy.devguyfx.entities.items.effects.Coffeine;
import com.devguy.devguyfx.level.Level;

public class Coffee extends ItemWithEffect {
    public Coffee(Level currentLevel) {
        super(currentLevel, false, "Coffee", 2500);
        this.animationState = new char[][][][]{{{{'c', '[', '_', ']'}}, {{'c', '[', '▁', ']'}}, {{'c', '[', '▂', ']'}}, {{'c', '[', '▃', ']'}}, {{'c', '[', '▄', ']'}}}};
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
        // Only player can use coffee this cast is safe
        Player player = (Player) instigator;
        Coffeine coffeine = new Coffeine(effectTicksMsLast);
        player.activeEffects.put(coffeine.effectName, coffeine);
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
