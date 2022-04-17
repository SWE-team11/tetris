package tetris.model;

import java.awt.event.KeyEvent;

public class ConfigModel {
    public enum GameMode {
        BASIC, ITEM
    }
    public enum GameDifficulty {
        EASY, NOMAL, DIFFICULT
    }
    public enum PlayerKey {
        ROTATE, LEFT, RIGHT, DOWN, DROP, UNDEFINED
    }

    public static int boardWidth = 10;
    public static int boardHeight = 20;
    public static int gameSpeed = 1;
    public static boolean colorBindMode = true;
    public static int[] keyBinding;

    public static PlayerKey getPlayerKey(final KeyEvent e) {
        PlayerKey[] values = PlayerKey.values();
        for(int i=0; i<values.length; i++) {
            if(keyBinding[i] == e.getKeyCode()) {
                return values[i];
            }
        }
        return PlayerKey.UNDEFINED;
    }

    public static void initConfig() {
        keyBinding = new int[PlayerKey.values().length];
        keyBinding[PlayerKey.ROTATE.ordinal()] = KeyEvent.VK_UP;        keyBinding[PlayerKey.ROTATE.ordinal()] = KeyEvent.VK_UP;
        keyBinding[PlayerKey.LEFT.ordinal()] = KeyEvent.VK_LEFT;
        keyBinding[PlayerKey.RIGHT.ordinal()] = KeyEvent.VK_RIGHT;
        keyBinding[PlayerKey.DOWN.ordinal()] = KeyEvent.VK_DOWN;
        keyBinding[PlayerKey.DROP.ordinal()] = KeyEvent.VK_SPACE;
        keyBinding[PlayerKey.UNDEFINED.ordinal()] = 0;
    }
}