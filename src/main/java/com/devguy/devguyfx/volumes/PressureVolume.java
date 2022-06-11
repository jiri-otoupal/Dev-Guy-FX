package com.devguy.devguyfx.volumes;

import com.devguy.devguyfx.GameController;
import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.level.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;


public class PressureVolume extends Volume {
    public PressureVolume(Level currentLevel, int width, int height, String name) {
        super(currentLevel, width, height, name);
    }


    @Override
    public boolean use(Entity1D instigator) {
        //This gets executed in Main thread with timeline
        final KeyFrame kf1 = new KeyFrame(Duration.seconds(0), e -> {
            GameController.getInstance().overlay.textProperty().setValue(currentLevel.name);
            GameController.getInstance().overlay.visibleProperty().setValue(true);
        });
        final KeyFrame kf2 = new KeyFrame(Duration.millis(2000), e -> {
            GameController.getInstance().overlay.visibleProperty().setValue(false);
        });

        final Timeline timeline = new Timeline(kf1, kf2);
        Platform.runLater(timeline::play);
        erase();
        return true;
    }

    /**
     * Decays item by milliseconds
     *
     * @param ticksMs milliseconds to decay from item
     */
    @Override
    public void decayEffectFromItems(long ticksMs) {

    }
}
