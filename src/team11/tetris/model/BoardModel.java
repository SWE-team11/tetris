package team11.tetris.model;

import team11.tetris.blocks.*;

import java.util.ArrayList;
import java.util.Random;

public class BoardModel {
    public ArrayList<Integer[]> board;
    public Block currentBlock;
    public Block nextBlock;
    public int x = 3; // Default Position.
    public int y = 0;

    // Config Model Inject
    private ConfigModel configModel;

    public BoardModel(ConfigModel configModel) {
        this.configModel = configModel;
    }

    public void eraseCurr() {
        for (int i = x; i < x + currentBlock.width(); i++) {
            for (int j = y; j < y + currentBlock.height(); j++) {
                board.get(j)[i] = 0;
            }
        }
    }

    public void initBoard(int width, int height) {
        this.board = new ArrayList<Integer[]>(height);
        for (int i = 0; i < height; i++) {
            Integer[] row = new Integer[width];
            for (int j = 0; j < width; j++)
                row[j] = 0;
            board.add(row);
        }
    }

    public void setRandomBlock() {
        Random rnd = new Random(System.currentTimeMillis());
        int block = rnd.nextInt(6);
        System.out.println(block);
        switch (block) {
            case 0:
                currentBlock = new IBlock();
                break;
            case 1:
                currentBlock = new JBlock();
                break;
            case 2:
                currentBlock = new LBlock();
                break;
            case 3:
                currentBlock = new ZBlock();
                break;
            case 4:
                currentBlock = new SBlock();
                break;
            case 5:
                currentBlock = new TBlock();
                break;
            case 6:
                currentBlock = new OBlock();
                break;
        }
        x = 3;
        y = 0;
    }

    public boolean moveDownAndSuccess() {
        eraseCurr();
        if (y < configModel.HEIGHT - currentBlock.height()) {
            y++;
            return true;
        } else {
            return false;
        }
    }

    public void moveLeft() {
        eraseCurr();
        if (x > 0) {
            x--;
        }
    }

    public void moveRight() {
        eraseCurr();
        if (x < configModel.WIDTH - currentBlock.width()) {
            x++;
        }
    }
}
