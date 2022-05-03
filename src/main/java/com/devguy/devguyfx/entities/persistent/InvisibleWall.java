package com.devguy.devguyfx.entities.persistent;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.ForceVector;
import com.devguy.devguyfx.structure.Point;


public class InvisibleWall extends Entity1D {

    public InvisibleWall(Level currentLevel) {
        super(currentLevel);
        this.representMap = new char[1][1];
        this.representMap[0][0] = '⠀';
        this.persistent = true;
    }

    public void invokeImpactEffect(Point impactLocation) { //TODO from left and right
    }

    @Override
    public boolean applyPhysicsImpulse(float mass, ForceVector vector) {
        return false;
    }

    @Override
    public void applyDamage(float damage) {
    }

}
