package com.devguy.devguyfx.entities.items;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.level.Level;

public interface IItem {
    boolean use(Entity1D instigator) throws Level.InvalidTemplateMap;

}
