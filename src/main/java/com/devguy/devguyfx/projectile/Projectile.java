package com.devguy.devguyfx.projectile;

import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.entities.Movable;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.ForceVector;
import com.devguy.devguyfx.structure.Point;


public class Projectile extends Movable {
    public ForceVector vector;

    public float damage;
    public float mass;

    public Projectile(Level currentLevel, float damage, float mass, boolean enableGravity, boolean applyPhysicsImpulse, Point spawnPoint, ForceVector vector) {
        super(currentLevel, enableGravity);
        this.absPosition = spawnPoint;
        currentLevel.streamer.assignAt(spawnPoint, this);
        representMap = new char[][]{{'☕'}};
        this.gravity = 0.003F * (mass / 10);
        this.vector = vector;
        this.persistent = false;
        this.appliesPhysicsImpulse = applyPhysicsImpulse;
        this.damage = damage;
        this.mass = mass;
    }

    /**
     * Applies projectile movement from initialized force vector
     */
    public void applyProjectileMovement() {
        if (vector == null || this.currentLevel.streamer == null)
            return;
        if (!move(this.vector.x, this.vector.y) || !this.falling) {
            erase(); // Destroy Instance
            for (Point collidingDelta : this.collisionDirections.keySet()) {
                Point absCollisionPos = new Point(this.absPosition.x + (collidingDelta.x) * representMap[0].length, this.absPosition.y + collidingDelta.y);
                if (currentLevel.streamer == null || !currentLevel.streamer.isValidLocation(absCollisionPos))
                    return;
                Entity1D usedInstance = this.currentLevel.streamer.getInstanceAt(absCollisionPos).shadow_parent;
                if (usedInstance == null)
                    continue;
                if (this.appliesPhysicsImpulse)
                    usedInstance.applyPhysicsImpulse(this.mass, new ForceVector(collidingDelta));
                usedInstance.applyDamage(this.damage);
                usedInstance.invokeImpactEffect(absPosition);
            }
        }
    }

    @Override
    public void decayEffectFromItems(long ticksMs) {

    }

    @Override
    public void tickEvent(long elapsedMs) {
        if (currentLevel.streamer == null) {
            this.erase();
            return;
        }
        if (this.enableGravity)
            applyGravity();
        applyProjectileMovement();
    }

    @Override
    public void updateAnimation(long elapsedMs) {

    }

    @Override
    public void setAnimationState(int state) {

    }
}
