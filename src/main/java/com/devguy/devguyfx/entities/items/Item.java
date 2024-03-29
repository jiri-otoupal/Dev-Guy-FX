package com.devguy.devguyfx.entities.items;

import com.devguy.devguyfx.entities.Animated;
import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.entities.textrender.DialogText;
import com.devguy.devguyfx.level.Level;


public abstract class Item extends Animated implements IItem {
    public String itemImage;
    protected boolean instant; //Cannot be grabbed, its used immediately
    public String itemName; //Used for Quest id also


    public Item(Level currentLevel, boolean instant, String itemName) {
        super(currentLevel, false);
        this.loops = true;
        this.instant = instant;
        this.itemName = itemName;
    }

    @Override
    public boolean canCollide() {
        return false;
    }

    /**
     * This is called on grabbing of item
     *
     * @param instigator who grabbed item
     * @return grab succeeded
     * @throws Level.InvalidTemplateMap
     */
    @Override
    public boolean grab(Entity1D instigator) throws Level.InvalidTemplateMap {
        if (instigator.canGrabItem()) {
            if (this.instant) {
                this.use(instigator);
                this.erase();
                new DialogText(currentLevel, "Used " + itemName, false, 45, 100).spawnAtPlayer();
                this.currentLevel.quest.markProgress(this.itemName);
                return true;
            }
            if (!instigator.insertItemToBackpack(this))
                return false;
            this.erase();
            new DialogText(currentLevel, "Got " + itemName + "+1", false, 45, 100).spawnAtPlayer();
            if (currentLevel.quest != null)
                this.currentLevel.quest.markProgress(this.itemName);
            return true;
        }
        return false;
    }

}
