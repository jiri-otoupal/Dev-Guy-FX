package com.devguy.devguyfx.entities.portal;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.entities.items.Item;
import com.devguy.devguyfx.level.Level;

public abstract class Portal extends Item {
    public Portal(Level currentLevel, String name) {
        super(currentLevel, true, name);
        this.loops = true;
        if (this.currentLevel.quest != null)
            this.currentLevel.quest.watchedEntities.add(this);
        this.animationState = new char[][][][]{{
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {'○', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', ' ', ' ', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', ' ', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {'○', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', ' ', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', ' ', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', ' ', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', '○', ' '}
                },
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', '○', ' '}
                },
                {
                        {'○', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', '○'}
                },
                {
                        {'○', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', '.', ' ', '|', ' '},
                        {'○', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', '○'}
                },
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', '○'},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', '○', ' '}
                },
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {'○', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', '○'},
                        {' ', '|', ' ', ' ', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', '○', ' '}
                },
                {
                        {'○', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
                {
                        {'○', ' ', '_', '_', '_', '_', ' ', '○'},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
                {
                        {'○', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', '.', '.', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', '○'},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
                {
                        {'○', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', '.', '.', '.', ' ', '|', ' '},
                        {' ', '|', '.', '.', '.', ' ', '|', '○'},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', '.', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', '.', '.', '.', ' ', '|', ' '},
                        {' ', '|', '.', '.', '.', ' ', '|', '○'},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {'○', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
        }};

        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
        this.frameDurationMs = 10;
    }


    @Override
    public boolean grab(Entity1D instigator) throws Level.InvalidTemplateMap {
        if (instigator.canGrabItem()) {
            if (this.instant) {
                this.use(instigator);
                return true;
            }
        }
        return false;
    }
}
