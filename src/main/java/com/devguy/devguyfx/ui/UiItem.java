package com.devguy.devguyfx.ui;

import com.devguy.devguyfx.structure.Point;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

import java.net.URL;

public class UiItem {
    public Point2D startLocation;
    public String representedImgPath;
    protected Point gridLocation;
    protected GridPane parentGrid;

    public UiItem(URL representedImgPath, Point gridLocation, GridPane parentGrid) {
        this.representedImgPath = String.valueOf(representedImgPath);
        this.gridLocation = gridLocation;
        this.parentGrid = parentGrid;

        ImageView imageView = new ImageView(String.valueOf(representedImgPath));
        imageView.setOnDragDetected(e -> {
            Dragboard db = imageView.startDragAndDrop(TransferMode.COPY);
            db.setDragView(new Image(String.valueOf(representedImgPath)));
            imageView.getScene().setCursor(Cursor.HAND);
            ClipboardContent cc = new ClipboardContent();
            cc.putString("");
            db.setContent(cc);
        });

        imageView.setOnDragDone(event -> {
            Dragboard dragb = event.getDragboard();
            dragb.getDragView();
            imageView.getScene().setCursor(Cursor.DEFAULT);
        });


        parentGrid.add(imageView, gridLocation.x, gridLocation.y);
    }
}
