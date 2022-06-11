package com.devguy.devguyfx.entities;

import com.devguy.devguyfx.GameController;
import com.devguy.devguyfx.entities.effects.EffectHitPlayer;
import com.devguy.devguyfx.entities.items.Item;
import com.devguy.devguyfx.entities.items.effects.ItemEffect;
import com.devguy.devguyfx.entities.textrender.StaticText;
import com.devguy.devguyfx.level.DeadMenuLevel;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.level.Streamer;
import com.devguy.devguyfx.projectile.Cpp;
import com.devguy.devguyfx.projectile.Csharp;
import com.devguy.devguyfx.structure.ForceVector;
import com.devguy.devguyfx.structure.Point;
import javafx.application.Platform;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class Player extends AliveEntity {
    public FloatProperty healthBarWidth = new SimpleFloatProperty();
    private static Player instance;
    public Backpack hotbar;
    public Backpack backpack;
    protected StaticText displayedHealth;
    public Map<String, ItemEffect> activeEffects;

    static public void resetPlayer(Level spawn) {
        if (instance == null)
            return;
        instance.activeEffects.clear();
        instance.backpack.clearItems();
        instance.hotbar.clearItems();
        instance = new Player(spawn, 100, 1F, 100);
    }

    static public Player getInstance() {
        return Player.instance;
    }

    public Player(@NotNull Level currentLevel, int health, float speed, long fireRateMs) {
        super(currentLevel, health, speed, fireRateMs, 6, 0.15F);
        GameController.getInstance().healthBar.widthProperty().bind(this.healthBarWidth);
        updateHealthBar();
        Player.instance = this;
        this.absPosition = null;
        this.backpack = new Backpack(12, GameController.getInstance().items);
        this.hotbar = new Backpack(6, GameController.getInstance().hotbar);
        this.currentLevel.streamer.controller.controlledAliveEntity = this;
        this.frameDurationMs = 50;
        this.loops = true;
        activeEffects = new HashMap<>();
        animationState = new char[][][][]{
                {//Idle
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        }
                },
                {//Falling
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {'.', '¨', '.'},
                                {'¨', ' ', '¨'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {'/', '¨', '\\'},
                                {'¨', ' ', '¨'}
                        },
                },
                {//Moving
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'(', ' ', ')'}
                        }
                },
                {//Shooting
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        }
                },
                {//Crouching
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {'/', ' ', '\\'}
                        }
                },
                {//Walking + Crouching
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {'(', ' ', ')'}
                        }
                },
                {//Pushing Right
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', '☺'},
                                {' ', '|', 'c'},
                                {' ', '*', 'c'},
                                {' ', '/', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', '☺'},
                                {' ', '|', 'c'},
                                {' ', '*', 'c'},
                                {' ', '(', ')'}
                        },
                },
                {//Pushing Left
                        {
                                {' ', 'T', ' '},
                                {'☺', ' ', ' '},
                                {'c', '|', ' '},
                                {'c', '*', ' '},
                                {'/', '\\', ' '}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', '☺'},
                                {' ', '|', 'c'},
                                {' ', '*', 'c'},
                                {' ', '(', ')'}
                        },
                },
        };
        selectedAnimationFrames = animationState[0];

    }

    public Player(Level currentLevel, Player player) {
        this(currentLevel, (int) player.health, player.speed, player.fireRate);
        this.backpack = player.backpack;
        this.activeEffects = player.activeEffects;
    }


    private String getColorFromPercentage(float p) {
        var red = p < 50 ? 255 : Math.round(256 - (p - 50) * 5.12);
        var green = p > 50 ? 255 : Math.round((p) * 5.12);
        return "rgb(" + red + "," + green + ",0)";
    }

    private void updateEffects() {
        HBox effectBar = GameController.getInstance().effectBar;

        Platform.runLater(() ->
                effectBar.getChildren().clear());
        for (ItemEffect itemEffect : activeEffects.values()) {
            ImageView effectImg = new ImageView(itemEffect.effectImg);
            effectImg.setFitHeight(effectBar.getHeight());
            effectImg.setPreserveRatio(true);
            effectImg.setOpacity((float) itemEffect.decayTime / itemEffect.decayTimeMax);
            Platform.runLater(() -> effectBar.getChildren().add(effectImg));
        }


    }

    private void updateHealthBar() {
        float hp_percent = health / this.max_health;
        Platform.runLater(() ->
                GameController.getInstance().healthBar.fillProperty()
                        .setValue(Paint.valueOf(getColorFromPercentage((int) (hp_percent * 100)))));
        healthBarWidth.setValue(GameController.getInstance().window.getWidth() * hp_percent);
    }

    /**
     * Player to die
     */
    @Override
    public void die() {
        Streamer streamer = currentLevel.streamer;
        try {
            streamer.loadLevel(new DeadMenuLevel(streamer.width, streamer.height, streamer));
        } catch (Level.InvalidTemplateMap e) {
            e.printStackTrace();
        }
    }

    /**
     * @param item to insert
     * @return True on succeed
     */
    @Override
    public boolean insertItemToBackpack(Item item) {
        return this.backpack.insertItem(item);
    }

    @Override
    public boolean canGrabItem() {
        return true;
    }

    /**
     * Applies Damage to player
     *
     * @param damage to apply
     */
    @Override
    public void applyDamage(float damage) {
        this.health -= damage;
        updateHealthBar();
        if (this.health > 0) {
            if (displayedHealth != null)
                displayedHealth.erase();

            displayedHealth = new StaticText(this.currentLevel, "Health " + this.health, 1000);
            displayedHealth.spawn(new Point(2, 2));
            return;
        }
        die();
    }

    /**
     * @param ticksMs milliseconds to decay from item
     */
    @Override
    public void decayEffectFromItems(long ticksMs) {

        if (activeEffects == null)
            return;

        for (int i = 0; i < activeEffects.size(); i++) {
            Object key = activeEffects.keySet().toArray()[i];
            ItemEffect itemEffect = activeEffects.get(key);
            if (itemEffect != null && itemEffect.decayTime > 0) {
                itemEffect.apply(this);
                itemEffect.decayTime -= ticksMs;
            } else if (itemEffect != null) { // If duration is eq or less than 0 remove item from effects
                activeEffects.remove(itemEffect.effectName);
                this.sayStatic("Deprecated " + itemEffect.effectName);
            }
        }
        updateEffects();
    }

    /**
     * @param currentLevel        Level to spawn in
     * @param damage              of projectile
     * @param mass                of projectile
     * @param enableGravity       for projectile
     * @param applyPhysicsImpulse on hit to object
     * @param spawnPoint          where to spawn projectile in streamer
     * @param vector              to move in projectile
     */
    @Override
    public void spawnProjectile(Level currentLevel, float damage, float mass, boolean enableGravity, boolean applyPhysicsImpulse, Point spawnPoint, ForceVector vector) {
        ItemEffect itemCoffeeDecay = null;
        if (activeEffects.size() > 0)
            itemCoffeeDecay = activeEffects.get("Coffeine");
        if (itemCoffeeDecay != null && itemCoffeeDecay.decayTime > 0)
            new Cpp(currentLevel, damage * 2, mass * 2, enableGravity, applyPhysicsImpulse, spawnPoint, vector.multiply(2F));
        else
            new Csharp(currentLevel, damage, mass, enableGravity, applyPhysicsImpulse, spawnPoint, vector.multiply(1.5F));
    }

    /**
     * Resolve animation state to be rendered
     * such as jumping animation, shooting etc.
     *
     * @return current animation state to be animated
     */
    @Override
    public int resolveAnimationState() {
        if (falling) {
            return 1;
        } else if (moving && !crouching) {
            return 2;
        } else if (shooting) {
            return 3;
        } else if (!moving && crouching) {
            return 4;
        } else if (moving && crouching) {
            return 5;
        } else if (!facingLeft && pushing) {
            return 6;
        } else if (facingLeft && pushing) {
            return 7;
        }
        //Idle State if none of previous
        return 0;
    }

    @Override
    public void invokeImpactEffect(Point impactLocation) { //TODO from left and right
        if (this.currentLevel != null && this.currentLevel.streamer != null)
            this.currentLevel.streamer.assignAt(impactLocation, new EffectHitPlayer(this.currentLevel, 40));
    }
}
