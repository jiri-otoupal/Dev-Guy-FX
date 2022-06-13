package com.devguy.devguyfx.level;


import com.devguy.devguyfx.entities.Player;
import com.devguy.devguyfx.entities.enemies.Skeleton;
import com.devguy.devguyfx.entities.enemies.SpawnPoint;
import com.devguy.devguyfx.entities.items.EnergyDrink;
import com.devguy.devguyfx.entities.items.HolyWater;
import com.devguy.devguyfx.entities.persistent.EmptySpace;
import com.devguy.devguyfx.entities.persistent.InvisibleWall;
import com.devguy.devguyfx.entities.persistent.Wall;
import com.devguy.devguyfx.entities.portal.PortalToWin;
import com.devguy.devguyfx.entities.props.background.Bone;
import com.devguy.devguyfx.entities.props.background.Column;
import com.devguy.devguyfx.entities.props.background.TorchLight;
import com.devguy.devguyfx.entities.props.interactible.Crate;
import com.devguy.devguyfx.quests.QuestTunnelsFight;
import com.devguy.devguyfx.volumes.SpawnVolume;

import java.util.HashMap;

public class TunnelsFight extends Level {
    public TunnelsFight(int width, int height, Streamer streamer) throws InvalidTemplateMap {
        super("Tunnels Fight", width, height, streamer);
        this.mapToTranslate = new String[]{
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                         aa                       ",
                "                                                                                ",
                "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
                "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
                "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
                "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
                "wwwww       wwwwwwww       wwwwwwww       wwwwwwww       wwwwwwww       wwwwwwww",
                "wwww  b      wwwwww         wwwwww  y      wwwwww         wwwwww  n      wwwwwww",
                "www           wwww           wwww           wwww           wwww           wwwwww",
                "ww             1              2              3              4               5  i",
                "ww                                                                             i",
                "ww                                                                   l         i",
                "ww                                   h                                         i",
                "ww   p                               w                                         i",
                "ww                                   w                                         i",
                "ww                s                  w                             o           i",
                "ww                                                                             i",
                "ww        c                          w                                         i",
                "ww v                                 w                                         i",
                "ww                                                                             i",
                "                                                                                ",
        };
        this.quest = new QuestTunnelsFight();
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
            put('1', new Column(level));
            put('2', new Column(level));
            put('3', new Column(level));
            put('4', new Column(level));
            put('5', new Column(level));
            put('x', new TorchLight(level));
            put('y', new TorchLight(level));
            put('n', new TorchLight(level));
            put('b', new TorchLight(level));
            put('p', streamer.player);
            put('e', new Skeleton(level, 50, 1F, 200, 6, 0.2F));
            put('s', new Crate(level));
            put('k', new Crate(level));
            put('m', new Crate(level));
            put('f', new Bone(level));
            put('c', new EnergyDrink(level));
            put('h', new HolyWater(level));
            put('l', new PortalToWin(level, "Portal"));
            put('i', new InvisibleWall(level));
            put('v', new SpawnVolume(level, 30, 1, "Spawn"));
            put('o', new SpawnPoint(level, 1000, 2));
        }};

    }
}
