package com.devguy.devguyfx.entities.portal;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.level.Streamer;
import com.devguy.devguyfx.level.WinMenuLevel;

public class PortalToWin extends Portal {
    public PortalToWin(Level currentLevel, String name) {
        super(currentLevel, name);
        this.visible = false;
    }

    @Override
    public boolean use(Entity1D instigator) throws Level.InvalidTemplateMap {
        Streamer streamer = currentLevel.streamer;
        WinMenuLevel level = new WinMenuLevel(streamer.width, streamer.height, streamer);
        streamer.loadLevel(level);
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
