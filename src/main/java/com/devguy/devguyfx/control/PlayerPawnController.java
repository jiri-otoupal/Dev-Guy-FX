package com.devguy.devguyfx.control;

import com.devguy.devguyfx.entities.items.Item;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.Pair;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.Nullable;

public class PlayerPawnController extends Controller {
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
        } else if (key == KeyCode.SOFTKEY_1) {
            try {
                @Nullable Pair<Item, Integer> item = controlledAliveEntity.backpack.removeItem("Coffee");
                if (item != null)
                    item.first.use(this.controlledAliveEntity);

            } catch (Level.InvalidTemplateMap e) {
                e.printStackTrace();
            }

        }
    }
}
