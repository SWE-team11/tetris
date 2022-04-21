package tetris.model;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
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

    public enum BoardSize {
        SMALL(8, 20), MEDIUM(10, 20), LARGE(12, 20);

        int width;
        int height;
        BoardSize(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public enum PlayerKey {
        ROTATE, LEFT, RIGHT, DOWN, DROP, ESC, UNDEFINED
    }

    public static GameMode gameMode = GameMode.BASIC;
    public static GameDifficulty gameDifficulty = GameDifficulty.EASY;
    public static BoardSize boardSize = BoardSize.MEDIUM;
    public static int boardWidth = 10;
    public static int boardHeight = 20;
    public static double gameSpeed = 1;
    public static boolean colorBlindMode = false;
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

    public static void changeGameMode(GameMode g) {
        gameMode = g;
        saveConfig();
    }

    public static void changeBoardSize(BoardSize boardSize) {
        boardWidth = boardSize.width;
        boardHeight = boardSize.height;
        saveConfig();
    }

    public static void changeGameSpeed(double g) {
        gameSpeed = g;
        saveConfig();
    }

    public static void changeColorBlindMode(boolean g) {
        colorBlindMode = g;
        saveConfig();
    }

    public static void changeKeyBinding(PlayerKey playerKey, KeyEvent e) {
        keyBinding[playerKey.ordinal()] = e.getKeyCode();
        saveConfig();
    }

    public static void initConfig() {
        gameMode = GameMode.BASIC;
        gameDifficulty = GameDifficulty.EASY;
        boardSize = BoardSize.MEDIUM;
        boardWidth = 10;
        boardHeight = 20;
        gameSpeed = 1;
        colorBlindMode = false;
        keyBinding = new int[]{
                KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
                KeyEvent.VK_DOWN, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, 0
        };
        saveConfig();
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
            out.write(boardSize.name() + ",");
            out.write(Integer.toString(boardWidth) + ",");
            out.write(Integer.toString(boardHeight) + ",");
            out.write(Double.toString(gameSpeed) + ",");
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
            boardSize = Enum.valueOf(BoardSize.class, configs[2]);
            boardWidth = Integer.parseInt(configs[3]);
            boardHeight = Integer.parseInt(configs[4]);
            gameSpeed = Double.parseDouble(configs[5]);
            colorBlindMode = Boolean.parseBoolean(configs[6]);
            int keyBingdingLength = Integer.parseInt(configs[7]);
            for(int i=0; i<keyBingdingLength; i++) {
                keyBinding[i] = Integer.parseInt(configs[8+i]);
            }
            bufReader.close();
        } catch (IOException e) {
            System.out.println("저장된 환경설정이 없습니다.");
        }
    }
    
    public static double getScoreRate() {
        return gameSpeed * gameMode.rate * gameDifficulty.rate;
    }

}
