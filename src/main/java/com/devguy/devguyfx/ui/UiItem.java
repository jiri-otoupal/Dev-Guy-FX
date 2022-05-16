package com.devguy.devguyfx.ui;

import com.devguy.devguyfx.entities.items.Item;
import com.devguy.devguyfx.structure.Point;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

import java.net.URL;

public class UiItem {
    public Item item;
    public String representedImgPath;
    protected Point gridLocation;
    protected GridPane parentGrid;
    protected ImageView imageView;

    public UiItem(Item item, URL representedImgPath, Point gridLocation, GridPane parentGrid) {
        this.item = item;
        this.representedImgPath = String.valueOf(representedImgPath);
        this.gridLocation = gridLocation;
        this.parentGrid = parentGrid;

        imageView = new ImageView(String.valueOf(representedImgPath));
        imageView.setOnDragDetected(e -> {
            Dragboard db = imageView.startDragAndDrop(TransferMode.COPY);
            db.setDragView(new Image(String.valueOf(representedImgPath)));
            imageView.getScene().setCursor(Cursor.HAND);
            ClipboardContent cc = new ClipboardContent();
            cc.putString(item.itemName);
            db.setContent(cc);
        });

        imageView.setOnDragDone(event -> {
            Dragboard dragb = event.getDragboard();
            dragb.getDragView();
            imageView.getScene().setCursor(Cursor.DEFAULT);
        });



        //Use this for interaction with GUI thread !
        Platform.runLater(() -> parentGrid.add(imageView, gridLocation.x, gridLocation.y));
    }


    public void destroy() {
        Platform.runLater(() -> this.parentGrid.getChildren().remove(imageView));
    }
}
