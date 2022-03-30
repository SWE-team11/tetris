package team11.tetris.model;

import team11.tetris.blocks.*;
import team11.tetris.utills.BoardElement;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class BoardModel {
    public ArrayList<BoardElement[]> board;
    public Block currentBlock;
    public Block nextBlock;
    public int x = 3; // Default Position.
    public int y = 0;

    // Config Model Inject
    private ConfigModel configModel;

    public BoardModel(ConfigModel configModel) {
        this.configModel = configModel;
        initBoard(configModel.WIDTH, configModel.HEIGHT);
    }

    public ArrayList<BoardElement[]> getBoard() {
        return board;
    }

    public Color getColor() {
        return currentBlock.getColor();
    }

    public void eraseCurr() {
        for (int i = x; i < x + currentBlock.width(); i++) {
            for (int j = y; j < y + currentBlock.height(); j++) {
                board.get(j)[i] = BoardElement.EMPTY;
            }
        }
    }

    public void initBoard(int width, int height) {
        this.board = new ArrayList<BoardElement[]>(height);
        for (int i = 0; i < height; i++) {
            BoardElement[] row = new BoardElement[width];
            for (int j = 0; j < width; j++)
                row[j] = BoardElement.EMPTY;
            board.add(row);
        }
    }

    public void setRandomBlock() {
        Random rnd = new Random(System.currentTimeMillis());
        int block = rnd.nextInt(6);
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
        placeBlock();
    }

    public boolean moveDown() {
        if (y < configModel.HEIGHT - currentBlock.height()) {
            eraseCurr();
            y++;
            placeBlock();
            return true;
        }
        return false;
    }

    public void moveLeft() {
        if (x > 0) {
            eraseCurr();
            x--;
            placeBlock();
        }
    }

    public void moveRight() {
        if (x < configModel.WIDTH - currentBlock.width()) {
            eraseCurr();
            x++;
            placeBlock();
        }
    }

    public void placeBlock() {
        for (int i = 0; i < currentBlock.height(); i++) {
            for (int j = 0; j < currentBlock.width(); j++) {
                board.get(y + i)[x + j] = currentBlock.getShape(j, i);
            }
        }
    }
}
