package com.devguy.devguyfx.entities.items;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.entities.Player;
import com.devguy.devguyfx.entities.items.effects.HolyWaterEffect;
import com.devguy.devguyfx.level.Level;

public class HolyWater extends ItemWithEffect {

    public HolyWater(Level currentLevel) {
        super(currentLevel, false, "Holy Water", 2500);
        this.animationState = new char[][][][]{{{{'†', '[', '_', ']'}}, {{'†', '[', '▁', ']'}}, {{'†', '[', '▂', ']'}}, {{'†', '[', '▃', ']'}}, {{'†', '[', '▄', ']'}}}};
        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
        this.frameDurationMs = 35;
        this.itemImage = "hwater.png";
    }

    /**
     * What happens on use of item
     *
     * @param instigator who used item
     * @return result use of item succeeded
     */
    @Override
    public boolean use(Entity1D instigator) throws Level.InvalidTemplateMap {
        Player player = (Player) instigator;
        player.activeEffects.put(itemName, new HolyWaterEffect(500));
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
