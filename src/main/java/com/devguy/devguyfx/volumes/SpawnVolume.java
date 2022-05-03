package com.devguy.devguyfx.volumes;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.entities.textrender.BannerText;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.Point;


public class SpawnVolume extends Volume {
    public SpawnVolume(Level currentLevel, int width, int height, String name) {
        super(currentLevel, width, height, name);
    }


    @Override
    public boolean use(Entity1D instigator) {
        new BannerText(currentLevel, currentLevel.name, 300, new Point(8, 2));
        erase();
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
