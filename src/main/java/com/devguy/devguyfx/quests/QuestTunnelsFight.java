package com.devguy.devguyfx.quests;


import com.devguy.devguyfx.entities.Entity1D;

public class QuestTunnelsFight extends Quest {
    public QuestTunnelsFight() {
        super();
        goalList.put("skeleton", 1);
    }

    @Override
    public void finishedQuest() {
        Entity1D entity = watchedEntities.get(0);
        entity.visible = true;
        entity.render(entity.currentLevel.map, entity.absPosition);
    }
}
