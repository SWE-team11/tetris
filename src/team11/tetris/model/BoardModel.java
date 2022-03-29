package team11.tetris.model;

import team11.tetris.blocks.*;

import java.util.ArrayList;
import java.util.Random;

import javax.rmi.ssl.SslRMIClientSocketFactory;

import java.awt.Color;
import java.lang.reflect.Method;

public class BoardModel {
    public ArrayList<Integer[]> board;
    public Block currentBlock;
    public Block nextBlock;
    public int x = 3; // Default Position.
    public int y = 0;

    public BoardModel(ConfigModel configModel) {
        initBoard(ConfigModel.WIDTH, ConfigModel.HEIGHT);

    }

    public ArrayList<Integer[]> getBoard() {
        return board;
    }

    public Color getColor() {
        return currentBlock.getColor();
    }

    public void eraseCurr() {
        for (int i = 0; i < currentBlock.width(); i++) {
            for (int j = 0; j < currentBlock.height(); j++) {
                if(currentBlock.getShape(i, j) != 0)
                    board.get(y + j)[x + i] = 0;
            }
        }
    }

    public void initBoard(int width, int height) {
        board = new ArrayList<Integer[]>(height);
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
        placeBlock();
    }

    abstract class Move {
        protected int compareAxisRange;
        protected int fixedAxisOffset;

        public void run() {
            eraseCurr();
            move();
            placeBlock();
        }

        abstract public boolean canMove();

        abstract public void move();
    }

    class Down extends Move {

        @Override
        public void move() {
            y++;
        }

        @Override
        public boolean canMove() {
            if (y + currentBlock.height() >= ConfigModel.HEIGHT)
                return false;
            int[] bottomProjection = currentBlock.getBottomProjection();
            for (int i = 0; i < bottomProjection.length; i++) {
                if (board.get(bottomProjection[i] + y + 1)[i + x] != 0)
                    return false;
            }
            return true;
        }
    }

    class Right extends Move {

        @Override
        public void move() {
            x++;
        }

        @Override
        public boolean canMove() {
            if (x + currentBlock.width() >= ConfigModel.WIDTH)
                return false;
            int[] rightProjection = currentBlock.getRightProjection();
            for (int i = 0; i < rightProjection.length; i++) {
                if (board.get(i + y)[rightProjection[i] + x + 1] != 0)
                    return false;
            }
            return true;
        }
    }

    class Left extends Move {

        @Override
        public void move() {
            x--;
        }

        @Override
        public boolean canMove() {
            if (x < 1)
                return false;
            int[] leftProjection = currentBlock.getLeftProjection();
            for (int i = 0; i < leftProjection.length; i++) {
                if (board.get(i + y)[leftProjection[i] + x - 1] != 0)
                    return false;
            }
            return true;
        }
    }

    public void placeBlock() {
        for (int i = 0; i < currentBlock.width(); i++) {
            for (int j = 0; j < currentBlock.height(); j++) {
                if (currentBlock.getShape(i, j) != 0) {
                    board.get(y + j)[x + i] = currentBlock.getShape(i, j);
                }
            }
        }
    }


    public void moveDown() {
        Down down = new Down();
        if (down.canMove()) {
            down.run();
        }
        else {
            setRandomBlock();
            placeBlock();
        }
    }

    public void moveRight() {
        Right right = new Right();
        if (right.canMove()) {
            right.run();
        }
    }

    public void moveLeft() {
        Left left = new Left();
        if(left.canMove()) {
            left.run();
        }
    }

    public void moveStraightDown() {
        Down down = new Down();
        while (down.canMove()) {
            down.run();
        }
    }
}
