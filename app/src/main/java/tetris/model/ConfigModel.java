package tetris.model;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        ROTATE, LEFT, RIGHT, DOWN, DROP, ESC, UNDEFINED
    }

    public static GameMode gameMode = GameMode.BASIC;
    public static GameDifficulty gameDifficulty = GameDifficulty.EASY;
    public static int boardWidth = 10;
    public static int boardHeight = 20;
    public static int gameSpeed = 1;
    public static boolean colorBlindMode = true;
    public static int[] keyBinding = {
            KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
            KeyEvent.VK_DOWN, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, 0
    };
    private final static String path = "data/config.txt";

    private ConfigModel() {

    }

    public static PlayerKey getPlayerKey(final KeyEvent e) {
        PlayerKey[] values = PlayerKey.values();
        for (int i = 0; i < values.length; i++) {
            if (keyBinding[i] == e.getKeyCode()) {
                return values[i];
            }
        }
        return PlayerKey.UNDEFINED;
    }

    public static void saveConfig() {
        BufferedWriter out = null;
        List<String> strList = new ArrayList<>();
        for (Integer integer : keyBinding) {
            strList.add(String.valueOf(integer));
        }

        try {
            File f = new File(path);
            f.getParentFile().mkdir();
            f.createNewFile();
            FileWriter fStream = new FileWriter(f, false);
            out = new BufferedWriter(fStream);
            out.write(gameMode.name() + ",");
            out.write(gameDifficulty.name() + ",");
            out.write(Integer.toString(boardWidth) + ",");
            out.write(Integer.toString(boardHeight) + ",");
            out.write(Integer.toString(gameSpeed) + ",");
            out.write(Boolean.toString(colorBlindMode) + ",");
            out.write(Integer.toString(keyBinding.length) + ",");
            out.write(String.join(",", strList));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void loadConfig() {
        try {
            File f = new File(path);
            FileReader fStream = new FileReader(f);
            BufferedReader bufReader = new BufferedReader(fStream);
            String line = bufReader.readLine();
            String[] configs = line.split(",");
            gameMode = Enum.valueOf(GameMode.class, configs[0]);
            gameDifficulty = Enum.valueOf(GameDifficulty.class, configs[1]);
            boardWidth = Integer.parseInt(configs[2]);
            boardHeight = Integer.parseInt(configs[3]);
            gameSpeed = Integer.parseInt(configs[4]);
            colorBlindMode = Boolean.parseBoolean(configs[5]);
            int keyBingdingLength = Integer.parseInt(configs[6]);
            for(int i=0; i<keyBingdingLength; i++) {
                keyBinding[i] = Integer.parseInt(configs[7+i]);
            }
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static double getScoreRate() {
        return gameSpeed * gameMode.rate * gameDifficulty.rate;
    }

}
