package tetris.model;

import java.awt.event.KeyEvent;

public class ConfigModel {
    public enum GameMode {
        BASIC(1), ITEM(0.5);

        double rate;
        GameMode(double rate) {
            this.rate = rate;
        }
    }

    public enum GameDifficulty {
        EASY(0.5), NORMAL(1.0), HARD(1.5);

        double rate;
        GameDifficulty(double rate) {
            this.rate = rate;
        }
    }
    public enum PlayerKey {
        ROTATE, LEFT, RIGHT, DOWN, DROP, UNDEFINED, ESC
    }

    public static GameMode gameMode = GameMode.BASIC;
    public static GameDifficulty gameDifficulty = GameDifficulty.HARD;
    public static int boardWidth = 10;
    public static int boardHeight = 20;
    public static int gameSpeed = 9;
    public static boolean colorBlindMode = true;
    public static int[] keyBinding;

    public static PlayerKey getPlayerKey(final KeyEvent e) {
        PlayerKey[] values = PlayerKey.values();
        for (int i = 0; i < values.length; i++) {
            if (keyBinding[i] == e.getKeyCode()) {
                return values[i];
            }
        }
        return PlayerKey.UNDEFINED;
    }

    public static void initConfig() {
        keyBinding = new int[PlayerKey.values().length];
        keyBinding[PlayerKey.ROTATE.ordinal()] = KeyEvent.VK_UP;
        keyBinding[PlayerKey.LEFT.ordinal()] = KeyEvent.VK_LEFT;
        keyBinding[PlayerKey.RIGHT.ordinal()] = KeyEvent.VK_RIGHT;
        keyBinding[PlayerKey.DOWN.ordinal()] = KeyEvent.VK_DOWN;
        keyBinding[PlayerKey.DROP.ordinal()] = KeyEvent.VK_SPACE;
        keyBinding[PlayerKey.ESC.ordinal()] = KeyEvent.VK_ESCAPE;
        keyBinding[PlayerKey.UNDEFINED.ordinal()] = 0;
    }

    public static double getScoreRate() {
        return gameSpeed * gameMode.rate * gameDifficulty.rate;
    }

    private ConfigModel() {

    }
}
