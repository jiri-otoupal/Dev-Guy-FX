package com.devguy.devguyfx.entities.props.interactible;

import com.devguy.devguyfx.entities.Movable;
import com.devguy.devguyfx.level.Level;

public class Crate extends Movable {
    public Crate(Level currentLevel) {
        super(currentLevel, true);
        representMap = new char[][]{
                {'+', '-', '-', '-', '-', '+'},
                {'|', '\\', ' ', ' ', '/', '|'},
                {'|', '/', '+', '+', '\\', '|'},
                {'|', '_', '_', '_', '_', '|'},
        };
        appliesPhysicsImpulse = true;
        mass = 10;
    }

    /**
     * Decays item by milliseconds
     *
     * @param ticksMs milliseconds to decay from item
     */
    @Override
    public void decayEffectFromItems(long ticksMs) {

    }

    /**
     * Update Animation on event if subscribed
     *
     * @param elapsedMs between updates
     */
    @Override
    public void updateAnimation(long elapsedMs) {

    }

    /**
     * Set animation state to be animated such as shooting, jumping etc.
     *
     * @param state to be animated
     */
    @Override
    public void setAnimationState(int state) {

    }
}
