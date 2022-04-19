package tetris.model;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigModel {
    public enum GameMode {
        BASIC, ITEM
    }
    public enum GameDifficulty {
        EASY, NORMAL, DIFFICULT
    }
    public enum PlayerKey {
        ROTATE, LEFT, RIGHT, DOWN, DROP, UNDEFINED
    }

    public static int boardWidth = 10;
    public static int boardHeight = 20;
    public static int gameSpeed = 2;
    public static boolean colorBlindMode = true;
    public static int[] keyBinding = {
            KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
            KeyEvent.VK_DOWN, KeyEvent.VK_SPACE, 0
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

    public static void saveConfig() throws IOException{
        BufferedWriter out = null;
        List<String> strList = new ArrayList<>();
        for (Integer integer : keyBinding) {
            strList.add(String.valueOf(integer));
        }

        File f = new File(path);
        f.getParentFile().mkdir();
        f.createNewFile();
        FileWriter fStream = new FileWriter(f, false);
        out = new BufferedWriter(fStream);
        out.write(Integer.toString(boardWidth) + ",");
        out.write(Integer.toString(boardHeight) + ",");
        out.write(Integer.toString(gameSpeed) + ",");
        out.write(Boolean.toString(colorBlindMode) + ",");
        out.write(Integer.toString(keyBinding.length) + ",");
        out.write(String.join(",", strList));
        out.close();
    }

    public static void loadConfig() {
        try {
            File f = new File(path);
            FileReader fStream = new FileReader(f);
            BufferedReader bufReader = new BufferedReader(fStream);
            String line = bufReader.readLine();
            String[] configs = line.split(",");
            boardWidth = Integer.parseInt(configs[0]);
            boardHeight = Integer.parseInt(configs[1]);
            gameSpeed = Integer.parseInt(configs[2]);
            colorBlindMode = Boolean.parseBoolean(configs[3]);
            int keyBingdingLength = Integer.parseInt(configs[4]);
            for(int i=0; i<keyBingdingLength; i++) {
                keyBinding[i] = Integer.parseInt(configs[5+i]);
            }
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
