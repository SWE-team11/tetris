package tetris.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class RecordModel {
    public static class Record implements Comparable<Record>{
        private int score;
        private int deletedLine;
        private ConfigModel.GameMode gameMode;
        private ConfigModel.GameDifficulty gameDifficulty;
        private String createdAt;
        private String name;

        public Record(int score, int deletedLine, ConfigModel.GameMode gameMode, ConfigModel.GameDifficulty gameDifficulty, String createdAt, String name) {
            this.score = score;
            this.deletedLine = deletedLine;
            this.gameMode = gameMode;
            this.gameDifficulty = gameDifficulty;
            this.createdAt = createdAt;
            this.name = name;
        }

        @Override
        public int compareTo(Record o) {
            if (o.score < score) {
                return 1;
            } else if (o.score > score) {
                return -1;
            }
            return 0;
        }
    }

    public static ArrayList<Record> rankedRecords = new ArrayList<Record>();
    private final static String path = "data/record.txt";

    public static void addRecord(int score, int deletedLine, ConfigModel.GameMode gameMode, ConfigModel.GameDifficulty gameDifficulty, String createdAt, String name) {
        rankedRecords.add(new Record(score, deletedLine, gameMode, gameDifficulty, createdAt, name));
        Collections.sort(rankedRecords);
        saveRecord();
    }

    public static void saveRecord() {
        BufferedWriter out = null;
        try {
            File f = new File(path);
            f.getParentFile().mkdir();
            f.createNewFile();
            FileWriter fStream = new FileWriter(f, false);
            out = new BufferedWriter(fStream);
            for (int i = 0; i < rankedRecords.size(); i++) {
                out.write(Integer.toString(rankedRecords.get(i).score) + ",");
                out.write(Integer.toString(rankedRecords.get(i).deletedLine) + ",");
                out.write(rankedRecords.get(i).gameMode.name() + ",");
                out.write(rankedRecords.get(i).gameDifficulty.name() + ",");
                out.write(rankedRecords.get(i).createdAt + ",");
                out.write(rankedRecords.get(i).name + "\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadRecord() {
        try {
            File f = new File(path);
            FileReader fStream = new FileReader(f);
            BufferedReader bufReader = new BufferedReader(fStream);
            String line = "";
            while((line = bufReader.readLine()) != null){
                String[] record = line.split(",");
                rankedRecords.add(new Record(
                        Integer.parseInt(record[0]),
                        Integer.parseInt(record[1]),
                        Enum.valueOf(ConfigModel.GameMode.class, record[2]),
                        Enum.valueOf(ConfigModel.GameDifficulty.class, record[3]),
                        record[4],
                        record[5]
                ));
            }
        } catch (IOException e) {
            System.out.println("저장된 기록이 없습니다.");
        }
    }
}
