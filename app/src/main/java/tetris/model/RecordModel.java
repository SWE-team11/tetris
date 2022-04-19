package tetris.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class RecordModel {
    static class Record implements Comparable<Record>{
        public int score;
        public String createdAt;

        Record(int score, String createdAt) {
            this.score = score;
            this.createdAt = createdAt;
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

    public static void addRecord(int score) {
        rankedRecords.add(new Record(score, new Date().toString()));
        Collections.sort(rankedRecords);
        try {
            saveRecord();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveRecord() throws IOException {
        BufferedWriter out = null;
        File f = new File(path);
        f.getParentFile().mkdir();
        f.createNewFile();
        FileWriter fStream = new FileWriter(f, false);
        out = new BufferedWriter(fStream);
        for (int i = 0; i < rankedRecords.size(); i++) {
            out.write(Integer.toString(rankedRecords.get(i).score) + ",");
            out.write(rankedRecords.get(i).createdAt + "\n");
        }
        out.close();
    }

    public static void loadRecord() {
        try {
            File f = new File(path);
            FileReader fStream = new FileReader(f);
            BufferedReader bufReader = new BufferedReader(fStream);
            String line = "";
            while((line = bufReader.readLine()) != null){
                String[] record = line.split(",");
                rankedRecords.add(new Record(Integer.parseInt(record[0]), record[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
