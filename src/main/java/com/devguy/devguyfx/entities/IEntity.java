package com.devguy.devguyfx.entities;

import com.devguy.devguyfx.entities.items.Item;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.ForceVector;
import com.devguy.devguyfx.structure.Point;


import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface IEntity {
    void useLight(); // Erase Shadows from movement

    void render(Entity1D[][] map, Point cursor);

    void iterationRenderFunction(Entity1D[][] map, Point cursor, int map_x, int map_y, int ent_x, int ent_y);

    void tickEvent(long elapsedMs) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    void applyDamage(float damage);

    boolean applyPhysicsImpulse(float mass, ForceVector vector);

    float getLifeSpan();

    void erase();

    void invokeImpactEffect(Point impactLocation) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    Map<Point, Entity1D> isColliding();

    void removeConnections();

    boolean canGrabItem();

    boolean grab(Entity1D instigator) throws Level.InvalidTemplateMap;

    boolean insertItemToBackpack(Item item);

    boolean canCollide();
}
