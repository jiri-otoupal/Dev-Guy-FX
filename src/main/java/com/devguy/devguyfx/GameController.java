package com.devguy.devguyfx;

import com.devguy.devguyfx.entities.Player;
import com.devguy.devguyfx.entities.items.Item;
import com.devguy.devguyfx.level.Level;
import com.devguy.devguyfx.structure.Pair;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.Dragboard;
import javafx.scene.input.PickResult;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    private static GameController single_instance = null;

    public static GameController getInstance() {
        if (single_instance == null)
            single_instance = new GameController();

        return single_instance;
    }


    public void initialize() {
        single_instance = this;
        game_screen.setFont(Font.font("monospace"));
        //Init button text
        toggleInventory(null);


        window.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            PickResult a = event.getPickResult();
            Node c = a.getIntersectedNode();


            Integer columnIndex = GridPane.getColumnIndex(c);
            Integer rowIndex = GridPane.getRowIndex(c);
            event.consume();
        });

        window.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                System.out.println("Dropped: " + db.getString());
                success = true;
            }
            event.setDropCompleted(success);

            PickResult a = event.getPickResult();
            Node c = a.getIntersectedNode();

            Player player = Player.getInstance();
            if (c.getClass().equals(Text.class)) {
                try {
                    Pair<Item, Integer> item = player.backpack.removeItem(db.getString());
                    if (item != null) {
                        item.first.use(player);
                        return;
                    }
                    item = player.hotbar.removeItem(db.getString());
                    if (item != null) {
                        item.first.use(player);
                        return;
                    }
                } catch (Level.InvalidTemplateMap e) {
                    throw new RuntimeException(e);
                }
            } else if (c.getClass().equals(GridPane.class) && ((GridPane) c).getRowCount() == 1 &&
                    player.hotbar.items.size() <= player.hotbar.maxSize) { //hot bar
                Pair<Item, Integer> backpackItem = player.backpack.removeItem(db.getString());
                if (backpackItem != null)
                    player.hotbar.insertItem(backpackItem.first);
            } else if (c.getClass().equals(GridPane.class) && ((GridPane) c).getRowCount() > 2 && player.hotbar.getAmount(db.getString()) > 0) { //items
                Pair<Item, Integer> backpackItem = player.hotbar.removeItem(db.getString());
                if (backpackItem != null)
                    player.backpack.insertItem(backpackItem.first);
            }

            //Integer columnIndex = GridPane.getColumnIndex(c);
            //Integer rowIndex = GridPane.getRowIndex(c);

            //System.out.println("x".concat(columnIndex.toString()).concat(" ").concat("y").concat(rowIndex.toString()));

            //UiItem uiItem = new UiItem(null, DGApplication.class.getResource("items/coffee.png"), new Point(columnIndex, rowIndex), this.items);

            event.consume();
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