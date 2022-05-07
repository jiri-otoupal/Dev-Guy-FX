package com.devguy.devguyfx;

import com.devguy.devguyfx.control.MenuController;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.level.MainMenuLevel;
import com.devguy.devguyfx.level.Streamer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DGApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException, Level.InvalidTemplateMap, InterruptedException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        FXMLLoader fxmlLoader = new FXMLLoader(DGApplication.class.getResource("game-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("DevGuy FX");
        stage.setScene(scene);
        stage.show();

        GameController gameController = fxmlLoader.getController();
        TextArea game_screen = gameController.game_screen;

        Streamer streamer = new Streamer(null, 60, game_screen);

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
                try {
                    Thread.sleep(20); // If too low can cause crashes !
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                streamer.render();

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