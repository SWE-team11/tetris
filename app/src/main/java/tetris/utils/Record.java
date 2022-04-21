package tetris.utils;

import tetris.model.ConfigModel;

public class Record implements Comparable<Record> {
    public int id;
    public int score;
    public int deletedLine;
    public ConfigModel.GameMode gameMode;
    public ConfigModel.GameDifficulty gameDifficulty;
    public String createdAt;
    public String name;

    public Record(int id, int score, int deletedLine, ConfigModel.GameMode gameMode, ConfigModel.GameDifficulty gameDifficulty,
            String createdAt, String name) {
        this.id = id;
        this.score = score;
        this.deletedLine = deletedLine;
        this.gameMode = gameMode;
        this.gameDifficulty = gameDifficulty;
        this.createdAt = createdAt;
        this.name = name;
    }

    @Override
    public int compareTo(Record o) {
        if (o.score > score) {
            return 1;
        } else if (o.score < score) {
            return -1;
        }
        return 0;
    }
}