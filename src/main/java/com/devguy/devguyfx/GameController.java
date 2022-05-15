package com.devguy.devguyfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameController {
    public Stage usedStage = null;
    @FXML
    public TextArea game_screen;
    @FXML
    public ToggleButton inventory_toggle;
    @FXML
    public AnchorPane inventory_pane;
    @FXML
    public GridPane items;
    @FXML
    public GridPane hotbar;
    public VBox window;


    public void initialize() {
        game_screen.setFont(Font.font("monospace"));
        //Init button text
        toggleInventory(null);

        inventory_pane.setOnDragDropped(e->{
            e.getAcceptedTransferMode();
        });
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void toggleInventory(ActionEvent actionEvent) {
        inventory_toggle.setText((inventory_toggle.isSelected() ? "Close" : "Open").concat(" Inventory"));
        inventory_pane.setVisible(inventory_toggle.isSelected());
        if (inventory_toggle.isSelected() && this.usedStage != null) {
            usedStage.setWidth(usedStage.getWidth() + inventory_pane.getPrefWidth());
        } else if (this.usedStage != null) {
            usedStage.setWidth(usedStage.getWidth() - inventory_pane.getPrefWidth());
        }
        game_screen.requestFocus();
    }
}