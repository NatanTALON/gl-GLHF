package com.glhf.bomberball.config;

import com.badlogic.gdx.Input;
import com.glhf.bomberball.InputHandler;
import com.glhf.bomberball.InputHandler.ButtonAction;
import com.glhf.bomberball.InputHandler.KeyAction;

import java.util.HashMap;

/**
 * class InputsConfig
 *
 * Defines all inputs key codes and button codes
 * Used by InputHandler class
 *
 * @author nayala
 */
public class InputsConfig extends Config {

    /** Map to convert key codes to actions */
    private HashMap<Integer, KeyAction> keycodes_actions;
    /** Map to convert button codes to actions */
    private HashMap<Integer, ButtonAction> buttoncodes_actions;

    /**
     * Default constructor
     */
    public InputsConfig() {
        keycodes_actions = new HashMap<>();
        buttoncodes_actions = new HashMap<>();
    }

    /**
     * Method to generate the default InputsConfig class
     * @return default inputs configuration class
     */
    public static InputsConfig defaultConfig() {
        InputsConfig c = new InputsConfig();

        c.addKeyCodeAction(Input.Keys.DOWN, KeyAction.KEY_DOWN);
        c.addKeyCodeAction(Input.Keys.UP, KeyAction.KEY_UP);
        c.addKeyCodeAction(Input.Keys.LEFT, KeyAction.KEY_LEFT);
        c.addKeyCodeAction(Input.Keys.RIGHT, KeyAction.KEY_RIGHT);
        c.addKeyCodeAction(Input.Keys.SPACE, KeyAction.KEY_SPACE);
        c.addKeyCodeAction(Input.Keys.NUMPAD_4, KeyAction.KEY_DROP_LEFT);
        c.addKeyCodeAction(Input.Keys.NUMPAD_8, KeyAction.KEY_DROP_UP);
        c.addKeyCodeAction(Input.Keys.NUMPAD_6, KeyAction.KEY_DROP_RIGHT);
        c.addKeyCodeAction(Input.Keys.NUMPAD_2, KeyAction.KEY_DROP_DOWN);
        c.addKeyCodeAction(Input.Keys.D, KeyAction.KEY_MOVE);
        c.addKeyCodeAction(Input.Keys.B, KeyAction.KEY_BOMB);
        c.addKeyCodeAction(Input.Keys.F, KeyAction.KEY_ENDTURN);


        c.addButtonCodeAction(Input.Buttons.LEFT, ButtonAction.BUTTON_LEFT);
        c.addButtonCodeAction(Input.Buttons.RIGHT, ButtonAction.BUTTON_RIGHT);

        return c;
    }

    /**
     * Links a key code with a KeyAction
     * @param key_code
     * @param action
     */
    private void addKeyCodeAction(int key_code, KeyAction action) {
        keycodes_actions.put(key_code, action);
    }

    /**
     * Links a button code with a KeyAction
     * @param button_code
     * @param action
     */
    private void addButtonCodeAction(int button_code, ButtonAction action) {
        buttoncodes_actions.put(button_code, action);
    }

    /**
     * Converts key codes to InputHandler.KeyActions
     * @param key_code
     * @return KeyAction corresponding to key_code
     */
    public InputHandler.KeyAction getKeyActionCode(int key_code) {
        return keycodes_actions.get(key_code);
    }

    /**
     * Converts button codes to InputHandler.ButtonActions
     * @param button_code
     * @return KeyAction corresponding to key_code
     */
    public InputHandler.ButtonAction getButtonActionCode(int button_code) {
        return buttoncodes_actions.get(button_code);
    }

    /**
     * @param key_code
     * @return key_code is assigned to a KeyAction
     */
    public boolean isKeyCodeAssigned(int key_code) {
        return keycodes_actions.containsKey(key_code);
    }

    /**
     * @param button_code
     * @return button_code is assigned to a ButtonCode
     */
    public boolean isButtonCodeAssigned(int button_code) {
        return buttoncodes_actions.containsKey(button_code);
    }
}
