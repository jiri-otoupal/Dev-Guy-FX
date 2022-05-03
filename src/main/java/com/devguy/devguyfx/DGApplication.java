package com.devguy.devguyfx;

import com.devguy.devguyfx.control.MenuController;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.level.MainMenuLevel;
import com.devguy.devguyfx.level.Streamer;
import javafx.application.Application;
import javafx.concurrent.Task;
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
        //Player player = SaveOperator.readXML("test.xml",streamer);
        MainMenuLevel mainMenuLevel = new MainMenuLevel(streamer.width, streamer.height, streamer);
        streamer.controller = new MenuController(mainMenuLevel.menu);

        //CompanyFight levelCompanyFight = new CompanyFight(streamer.width, streamer.height, streamer);
        streamer.loadLevel(mainMenuLevel);
        Character pressed = 'i'; // Lanterna Lib has visible property protected, watching if handle to Character is null ! needs to be initialized
        //noinspection LoopConditionNotUpdatedInsideLoop
        //while (pressed != null) {
        streamer.render();
        //KeyStroke key = streamer.terminal.pollInput();
        //if (key != null)
        //    streamer.controller.invokeActionFromKey(key);
        //}
        game_screen.setOnKeyPressed(k -> {
            streamer.controller.invokeActionFromKey(k.getCode());
        });

        runGame(streamer);
    }

    private void runGame(Streamer streamer) {

        Task<Void> task = new Task<>() {
            @Override
            public Void call() {
                try {
                    while (true) {
                        long tts = streamer.render();
                        Thread.sleep(tts == 0 ? 1 : tts);
                    }
                } catch (IOException | InterruptedException | ClassNotFoundException | InvocationTargetException |
                         InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                    Thread.dumpStack();
                    throw new RuntimeException(e);
                }
            }
        };
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.setName("Render thread");
        t.start();


    }

    public static void main(String[] args) {
        launch();
    }
}