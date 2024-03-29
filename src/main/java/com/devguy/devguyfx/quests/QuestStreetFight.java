package com.devguy.devguyfx.quests;

import com.devguy.devguyfx.entities.Entity1D;

public class QuestStreetFight extends Quest {
    public QuestStreetFight() {
        super();
        goalList.put("skeleton", 5);
    }

    @Override
    public void finishedQuest() {
        Entity1D entity = watchedEntities.get(0);
        entity.visible = true;
        entity.render(entity.currentLevel.map, entity.absPosition);
    }
}
