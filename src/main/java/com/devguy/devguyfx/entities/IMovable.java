package com.devguy.devguyfx.entities;

public interface IMovable {
    abstract boolean move(float deltaX, float deltaY);

    void decayEffectFromItems(long ticksMs);
}
