package com.devguy.devguyfx.entities.items;

import com.devguy.devguyfx.level.Level;

public abstract class ItemWithEffect extends Item {
    protected long effectTicksMsLast;

    public ItemWithEffect(Level currentLevel, boolean instant, String quest_name, long effectTicksMsLast) {
        super(currentLevel, instant, quest_name);
        this.effectTicksMsLast = effectTicksMsLast;
    }
}
