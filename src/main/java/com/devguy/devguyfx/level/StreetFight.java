package com.devguy.devguyfx.level;

import com.devguy.devguyfx.entities.Player;
import com.devguy.devguyfx.entities.enemies.SpawnPoint;
import com.devguy.devguyfx.entities.items.HolyWater;
import com.devguy.devguyfx.entities.persistent.EmptySpace;
import com.devguy.devguyfx.entities.persistent.InvisibleWall;
import com.devguy.devguyfx.entities.persistent.Wall;
import com.devguy.devguyfx.entities.portal.PortalToTunnels;
import com.devguy.devguyfx.entities.props.background.City;
import com.devguy.devguyfx.entities.props.background.Lamp;
import com.devguy.devguyfx.entities.props.interactible.Crate;
import com.devguy.devguyfx.quests.QuestStreetFight;
import com.devguy.devguyfx.volumes.SpawnVolume;

import java.util.HashMap;

public class StreetFight extends Level {
    public StreetFight(int width, int height, Streamer streamer) throws InvalidTemplateMap {
        super("Street Fight", width, height, streamer);
        this.mapToTranslate = new String[]{
                "i      q                                                                       i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i            m                             n            e          l           i",
                "i                                                                              i",
                "i                                                                              i",
                "i        p                                                                     i",
                "i                                   s                                          i",
                "i                                                                              i",
                "i                              c                                               i",
                "i  v                                                                           i",
                "i                                                                              i",
                "                                                                                ",
        };
        this.quest = new QuestStreetFight();
    }

    @Override
    public void initializeLinker() {
        Level level = this;
        if (streamer.player == null)
            streamer.player = new Player(level, 100, 1F, 100);
        else
            streamer.player = new Player(level, streamer.player);
        this.linker = new HashMap<>() {{
            put(' ', new EmptySpace(level));
            put('w', new Wall(level));
            put('p', streamer.player);
            put('m', new Lamp(level));
            put('n', new Lamp(level));
            put('b', new Lamp(level));
            put('q', new City(level));
            put('s', new Crate(level));
            put('e', new SpawnPoint(level, 500, 3));
            put('c', new HolyWater(level));
            put('l', new PortalToTunnels(level, "Portal"));
            put('i', new InvisibleWall(level));
            put('v', new SpawnVolume(level, 30, 1, "Spawn"));
        }};
    }
}
