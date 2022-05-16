package com.devguy.devguyfx.entities;

import com.devguy.devguyfx.DGApplication;
import com.devguy.devguyfx.entities.items.Item;
import com.devguy.devguyfx.structure.Pair;
import com.devguy.devguyfx.structure.Point;
import com.devguy.devguyfx.ui.UiItem;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;


public class Backpack {
    private final GridPane renderTarget;
    public int maxSize;
    public Map<String, Pair<UiItem, Integer>> items;


    public Backpack(int backpackSize, GridPane renderTarget) {
        this.items = new HashMap<>();
        this.maxSize = backpackSize;
        this.renderTarget = renderTarget;
    }


    private Point nextPosition() {
        if (items.isEmpty())
            return new Point(0, 0);
        int size = this.items.size();
        int columnCount = renderTarget.getColumnCount();
        return new Point(size % columnCount, size / columnCount);
    }

    public boolean insertItem(Item item) {
        if (this.items.size() >= maxSize)
            return false;
        UiItem uiItem = new UiItem(item, DGApplication.class.getResource("items/coffee.png"), nextPosition(), renderTarget);

        if (this.items.containsKey(item.itemName)) {
            Integer storedCount = this.items.get(item.itemName).second;
            this.items.put(item.itemName, new Pair<>(uiItem, storedCount + 1));
        } else {
            this.items.put(item.itemName, new Pair<>(uiItem, 1));
        }
        return true;
    }

    public @Nullable Pair<Item, Integer> removeItem(String itemName) {
        if (this.items.containsKey(itemName)) {
            Pair<UiItem, Integer> pair = this.items.remove(itemName);
            pair.first.destroy();
            return new Pair<>(pair.first.item, pair.second);
        }
        return null;
    }

    public int getAmount(String itemName) {
        if (!this.items.containsKey(itemName))
            return 0;
        return this.items.get(itemName).second;
    }

    public int getAmount(Item item) {
        if (!this.items.containsKey(item.itemName))
            return 0;
        return this.items.get(item.itemName).second;
    }
}
