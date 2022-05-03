package com.devguy.devguyfx.entities.persistent;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.entities.effects.EffectPersistentImpact;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.ForceVector;
import com.devguy.devguyfx.structure.Point;


public class BarsWall extends Entity1D {
    public BarsWall(Level currentLevel) {
        super(currentLevel);
        this.representMap = new char[1][1];
        this.representMap[0][0] = 'ꔖ';
        this.persistent = true;
    }

    public void invokeImpactEffect(Point impactLocation) {
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
