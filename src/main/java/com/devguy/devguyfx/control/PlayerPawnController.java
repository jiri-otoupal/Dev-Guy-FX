package com.devguy.devguyfx.control;

import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.Pair;
import com.devguy.devguyfx.ui.UiItem;
import javafx.scene.input.KeyCode;

public class PlayerPawnController extends Controller {

    public void useHotItem(int index) {
        for (Pair<UiItem, Integer> item : controlledAliveEntity.hotbar.items.values()) {
            if (item.first.gridLocation.x == index) {
                controlledAliveEntity.hotbar.removeItem(item.first.item.itemName);
                try {
                    item.first.item.use(this.controlledAliveEntity);
                } catch (Level.InvalidTemplateMap e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void invokeActionFromKey(KeyCode key) {
        if (key == KeyCode.W) {
            controlledAliveEntity.Jump();
        } else if (key == KeyCode.A) {
            controlledAliveEntity.MoveLeft();
        } else if (key == KeyCode.S) {
            controlledAliveEntity.Crouch();
        } else if (key == KeyCode.D) {
            controlledAliveEntity.MoveRight();
        } else if (key == KeyCode.SPACE) {
            controlledAliveEntity.Shoot();
        } else if (key == KeyCode.DIGIT1) {
            useHotItem(1);
        } else if (key == KeyCode.DIGIT2) {
            useHotItem(2);
        } else if (key == KeyCode.DIGIT3) {
            useHotItem(3);
        } else if (key == KeyCode.DIGIT4) {
            useHotItem(4);
        } else if (key == KeyCode.DIGIT5) {
            useHotItem(5);
        } else if (key == KeyCode.DIGIT6) {
            useHotItem(6);
        }
    }
}
