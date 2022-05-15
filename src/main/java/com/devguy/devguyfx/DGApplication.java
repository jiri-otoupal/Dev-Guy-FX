package com.devguy.devguyfx;

import com.devguy.devguyfx.control.MenuController;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.level.MainMenuLevel;
import com.devguy.devguyfx.level.Streamer;
import com.devguy.devguyfx.structure.Point;
import com.devguy.devguyfx.ui.UiItem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class DGApplication extends Application {

    private Stage stage;

    public void resizeScene(double width, double height) {
        this.stage.setWidth(width);
        this.stage.setHeight(height);
    }

    @Override
    public void start(Stage stage) throws IOException, Level.InvalidTemplateMap {
        FXMLLoader fxmlLoader = new FXMLLoader(DGApplication.class.getResource("game-screenV2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        this.stage = stage;
        stage.setTitle("DevGuy FX");
        stage.setScene(scene);
        stage.show();

        GameController gameController = fxmlLoader.getController();
        UiItem uiItem = new UiItem(DGApplication.class.getResource("items/coffee.png"), new Point(0, 0), gameController.items);
        gameController.usedStage = stage;
        TextArea game_screen = gameController.game_screen;

        Streamer streamer = new Streamer(null, 50, game_screen);

        game_screen.textProperty().bind(streamer.currentFrame);

        //Player player = SaveOperator.readXML("test.xml",streamer);
        MainMenuLevel mainMenuLevel = new MainMenuLevel(streamer.width, streamer.height, streamer);
        streamer.controller = new MenuController(mainMenuLevel.menu);

        streamer.loadLevel(mainMenuLevel);

        game_screen.setOnKeyPressed(k -> {
            streamer.controller.invokeActionFromKey(k.getCode());
        });

        runGame(streamer);
    }

    private void runGame(Streamer streamer) {


        Thread t = new Thread(() -> {

            while (true) {
                long toSleep = streamer.render();

                try {
                    Thread.sleep(toSleep); // If too low can cause crashes stay above 20 !
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
        });
        t.setDaemon(true);
        t.setName("Render thread");
        t.start();


    }

    public static void main(String[] args) {
        launch();
    }
}