package com.devguy.devguyfx.entities.persistent;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.entities.effects.EffectPersistentImpact;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.ForceVector;
import com.devguy.devguyfx.structure.Point;


public class Wall extends Entity1D {

    public Wall(Level currentLevel) {
        super(currentLevel);
        this.representMap = new char[1][1];
        this.representMap[0][0] = '█';
        this.persistent = true;
    }

    public void invokeImpactEffect(Point impactLocation) { //TODO from left and right
        this.currentLevel.streamer.assignAt(impactLocation, new EffectPersistentImpact(this.currentLevel, 50));
    }

    @Override
    public boolean applyPhysicsImpulse(float mass, ForceVector vector) {
        return false;
    }

    @Override
    public void applyDamage(float damage) {
    }

}
