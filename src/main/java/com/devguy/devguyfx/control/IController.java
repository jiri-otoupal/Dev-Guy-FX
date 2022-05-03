package com.devguy.devguyfx.control;

import javafx.scene.input.KeyCode;

public interface IController {
    /**
     * Invokes action bind on key
     *
     * @param key Key pressed
     */
    void invokeActionFromKey(KeyCode key);
}
