package com.devguy.devguyfx.level;

import com.devguy.devguyfx.control.Controller;
import com.devguy.devguyfx.entities.Entity1D;
import com.devguy.devguyfx.entities.IEntity;
import com.devguy.devguyfx.entities.Player;
import com.devguy.devguyfx.entities.persistent.EmptySpace;
import com.devguy.devguyfx.entities.props.background.BackgroundProp;
import com.devguy.devguyfx.entities.textrender.StaticText;
import com.devguy.devguyfx.saves.SaveOperator;
import com.devguy.devguyfx.structure.Point;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Streamer {
    public int slowDownGameByMultiplayer;
    public TextArea terminal;

    private com.devguy.devguyfx.level.Level loadedLevel = null;
    public Controller controller;
    private final List<IEntity> listeners;
    private final int target_fps;
    public int width;
    public int height;
    public Entity1D[][] map;
    public Player player = null;
    public StringProperty currentFrame = new SimpleStringProperty();
    private final StringBuilder renderOutput;

    public Streamer(Controller controller, int target_fps, TextArea terminal) throws IOException {
        this.terminal = terminal;
        this.controller = controller;
        this.target_fps = target_fps;
        this.width = 80;
        this.height = 24;
        this.map = new Entity1D[height][width];
        this.listeners = new ArrayList<>();
        this.clear();
        renderOutput = new StringBuilder(this.width * this.height);
        slowDownGameByMultiplayer = 10;
    }

    /**
     * Clear Streamer
     */
    public void clear() {
        for (int line = 0; line < this.height; line++) {
            Arrays.fill(this.map[line], new EmptySpace(this.loadedLevel));
        }
    }

    /**
     * Subscribe listener to events broadcast
     *
     * @param toAdd entity to be called
     */
    public void addListener(IEntity toAdd) {
        listeners.add(toAdd);
    }

    /**
     * UnSubscribe listener to events broadcast
     *
     * @param toRemove entity to be called
     */
    public void removeListener(IEntity toRemove) {
        listeners.remove(toRemove);
    }

    /**
     * Broadcasts tick to listeners
     *
     * @param elapsedMs between events
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public void broadcastTick(long elapsedMs) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        for (int i = 0; i < listeners.size(); i++) { // Needs to be in this for because of removing during iterations
            IEntity listener = listeners.get(i);
            if (listener.getLifeSpan() < 0) {
                listener.erase();
                continue;
            }
            listener.tickEvent(elapsedMs);
        }

    }

    /**
     * @param coords location
     * @param value  entity to assign
     * @return True on success
     */
    public boolean assignAt(Point coords, Entity1D value) {
        if (coords != null && isValidLocation(coords)) {
            this.map[coords.y][coords.x] = value;
            return true;
        }
        if (coords != null)
            System.out.println("Could not Assign to " + coords);
        return false;
    }

    public boolean isValidLocation(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    public boolean isValidLocation(Point location) {
        return location != null && location.x >= 0 && location.x < this.width && location.y >= 0 && location.y < this.height;
    }

    /**
     * @param coords Point
     * @return Entity on location
     */
    public Entity1D getInstanceAt(@NotNull Point coords) {
        return this.map[coords.y][coords.x];
    }

    /**
     * @param level Level to load
     *              <p>
     *              This method sets pointer of current level to streamer to NULL !
     */
    public void loadLevel(@NotNull com.devguy.devguyfx.level.Level level) {
        if (level.width != width || level.height != height) {
            System.out.printf("Inconsistent Level dimensions Width %s!=%s Height %s!=%s%n", level.width, width, level.height, height);
        }

        // Clear Streamer ref from previous level
        if (this.loadedLevel != null) {
            this.listeners.clear();
            this.loadedLevel.streamer = null;
        }
        //Set ref to new level
        this.map = level.map;
        this.loadedLevel = level;
        try {
            this.loadedLevel.compileMap();
        } catch (Level.InvalidTemplateMap e) {
            e.printStackTrace();
        }
        if (this.player != null && !level.name.equals("Escape")) {
            SaveOperator.saveGame("save.xml", this.player);
            new StaticText(level, "Saved Game", 200).spawn(new Point(1, 2));
        }
        System.gc();
    }

    /**
     * Render loaded level with all objects and signals called
     */
    public long render() {
        long frame_time = 1000 / target_fps;


        renderOutput.setLength(0);

        for (int line = 0; line < this.height; line++) { //-1 because of ground
            for (int column = 0; column < this.width; column++) {
                Entity1D obj = this.map[line][column];
                if (obj == null)
                    continue;
                Point position = new Point(column, line);
                if (obj.absPosition == null)
                    obj.absPosition = position;
                if (!obj.visible)
                    continue;
                obj.useLight();
                obj.render(this.map, position);
                // Makes object translate itself to projection screen before being drawn
                // Adds rendered body positions
                renderOutput.append(this.map[line][column].getChar());

            }
            renderOutput.append(System.lineSeparator());
        }

        // Background Props need to be rendered again to compensate overwrites
        for (BackgroundProp prop : this.loadedLevel.backgroundProps)
            if (prop.absPosition != null && prop.visible)
                prop.render(this.map, prop.absPosition);

        //Platform.runLater(() -> terminal.setText(renderOutput.toString()));
        currentFrame.setValue(renderOutput.toString());

        try {
            this.broadcastTick(frame_time / slowDownGameByMultiplayer);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


        return frame_time;
    }

}