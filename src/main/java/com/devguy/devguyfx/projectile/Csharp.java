package com.devguy.devguyfx.projectile;

import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.ForceVector;
import com.devguy.devguyfx.structure.Point;


public class Csharp extends Projectile {
    public Csharp(Level currentLevel, float damage, float mass, boolean enableGravity, boolean applyPhysicsImpulse, Point spawnPoint, ForceVector vector) {
        super(currentLevel, damage, mass, enableGravity, applyPhysicsImpulse, spawnPoint, vector);
        representMap = new char[][]{{'C', '#'}};
    }
}
