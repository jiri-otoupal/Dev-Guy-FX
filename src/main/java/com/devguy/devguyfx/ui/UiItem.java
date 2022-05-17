package com.devguy.devguyfx.ui;

import com.devguy.devguyfx.entities.items.Item;
import com.devguy.devguyfx.structure.Point;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;

public class UiItem {
    public Item item;
    public String representedImgPath;
    public Point gridLocation;
    protected GridPane parentGrid;
    protected StackPane itemPane;

    private final Label label;


    public UiItem(Item item, URL representedImgPath, Point gridLocation, GridPane parentGrid, int count) {
        this.item = item;
        this.representedImgPath = String.valueOf(representedImgPath);
        this.gridLocation = gridLocation;
        this.parentGrid = parentGrid;


        ImageView imageView = new ImageView(String.valueOf(representedImgPath));
        label = new Label(String.valueOf(count));
        label.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        this.itemPane = new StackPane(imageView, label);

        itemPane.setOnDragDetected(e -> {
            Dragboard db = imageView.startDragAndDrop(TransferMode.COPY);
            db.setDragView(new Image(String.valueOf(representedImgPath)));
            imageView.getScene().setCursor(Cursor.HAND);
            ClipboardContent cc = new ClipboardContent();
            cc.putString(item.itemName);
            db.setContent(cc);
        });

        itemPane.setOnDragDone(event -> {
            Dragboard dragb = event.getDragboard();
            dragb.getDragView();
            imageView.getScene().setCursor(Cursor.DEFAULT);
        });


        //Use this for interaction with GUI thread !
        Platform.runLater(() -> parentGrid.add(itemPane, gridLocation.x, gridLocation.y));
    }

    public void setCount(int count) {
        Platform.runLater(() -> label.setText(String.valueOf(count)));
    }

    public void destroy() {
        System.out.println("Parent ".concat(String.valueOf(this.parentGrid.getChildren().size())));
        Platform.runLater(() -> this.parentGrid.getChildren().remove(this.itemPane));
    }
}
