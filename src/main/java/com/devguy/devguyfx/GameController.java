package com.devguy.devguyfx;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class GameController {
    @FXML
    public TextArea game_screen;


    public void initialize() {
        game_screen.setFont(Font.font("monospace"));
        game_screen.setWrapText(true);
        game_screen.setCache(false);
    }
}