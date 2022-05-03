package com.devguy.devguyfx.entities;

import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.ForceVector;
import com.devguy.devguyfx.structure.Point;

public interface IAliveEntity {
    void die();

    void Jump();

    void JumpVertical();

    void Crouch();

    void MoveLeft();

    void MoveRight();

    void Shoot();

    void spawnProjectile(Level currentLevel, float damage, float mass, boolean enableGravity, boolean applyPhysicsImpulse, Point spawnPoint, ForceVector vector);

    void sayDialog(String text);

    void sayStatic(String text);
}
