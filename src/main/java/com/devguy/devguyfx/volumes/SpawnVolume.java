package com.devguy.devguyfx.volumes;

import com.devguy.devguyfx.GameController;
import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.level.Level;


public class SpawnVolume extends Volume {
    public SpawnVolume(Level currentLevel, int width, int height, String name) {
        super(currentLevel, width, height, name);
    }


    @Override
    public boolean use(Entity1D instigator) {
        //This gets executed in Main thread with timeline
        GameController.showOverlay(currentLevel.name, 2000);
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
