package team11.tetris.model;

import team11.tetris.blocks.*;
import team11.tetris.utills.BoardElement;

import java.util.ArrayList;
import java.util.Random;

public class BoardModel {
    public ConfigModel configModel;
    public ArrayList<BoardElement[]> board;
    public Block currentBlock;
    public Block nextBlock;
    public int x = 3; // Default Position.
    public int y = 0;

    public BoardModel(ConfigModel configModel) {
        this.configModel = configModel;
        initBoard(configModel.WIDTH, configModel.HEIGHT);
    }

    public ArrayList<BoardElement[]> getBoard() {
        return board;
    }

    public void initBoard(int width, int height) {
        board = new ArrayList<BoardElement[]>(height);
        for (int i = 0; i < height; i++) {
            BoardElement[] row = new BoardElement[width];
            for (int j = 0; j < width; j++)
                row[j] = BoardElement.EMPTY;
            board.add(row);
        }
    }

    public void setRandomBlock() {
        Random rnd = new Random(System.currentTimeMillis());
        int block = rnd.nextInt(7);
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
    
    private enum Result {
        OK, ERR;
    }

    abstract class Move {
        public Result run() {
            eraseCurr();
            move();
            if (canPlaceBlock()) {
                Result ret = placeBlock();
                if (ret == Result.ERR) {
                    throw new IllegalStateException();
                }
                return Result.OK;
            } else {
                fallBack();
                return Result.ERR;
            }
        }

        public void fallBack() {
            moveBack();
            Result ret = placeBlock();
            if (ret == Result.ERR)
                throw new IllegalStateException();
            hook();
        }

        public boolean canPlaceBlock() {
            if (ifBoundaryGoOver())
                return false;
            for (int i = 0; i < currentBlock.width(); i++) {
                for (int j = 0; j < currentBlock.height(); j++) {
                    if (board.get(y + j)[x + i] != BoardElement.EMPTY
                            && currentBlock.getShape(i, j) != BoardElement.EMPTY) {
                        return false;
                    }
                }
            }
            return true;
        }
        
        abstract boolean ifBoundaryGoOver();

        abstract public void move();

        abstract public void moveBack();

        abstract public void hook();
    }

    class Down extends Move {
        public boolean ifBoundaryGoOver() {
            return y + currentBlock.height() > ConfigModel.HEIGHT;
        }

        public void move() {
            y++;
        }

        public void moveBack() {
            y--;
        }

        public void hook() {
            checkBoard();
            setRandomBlock();
            placeBlock();
        }
    }

    class Right extends Move {
        public boolean ifBoundaryGoOver() {
            return x + currentBlock.width() > ConfigModel.WIDTH;
        }

        public void move() {
            x++;
        }

        public void moveBack() {
            x--;
        }

        public void hook() {
        }
    }

    class Left extends Move {
        public boolean ifBoundaryGoOver() {
            return x < 0;
        }

        public void move() {
            x--;
        }

        public void moveBack() {
            x++;
        }

        public void hook() {
        }
    }


    public Result placeBlock() {
        for (int i = 0; i < currentBlock.width(); i++) {
            for (int j = 0; j < currentBlock.height(); j++) {
                if (board.get(y + j)[x + i] != BoardElement.EMPTY
                        && currentBlock.getShape(i, j) != BoardElement.EMPTY)
                    return Result.ERR;
                if (currentBlock.getShape(i, j) != BoardElement.EMPTY) {
                    board.get(y + j)[x + i] = currentBlock.getShape(i, j);
                }
            }
        }
        return Result.OK;
    }

    public void eraseCurr() {
        for (int i = 0; i < currentBlock.width(); i++) {
            for (int j = 0; j < currentBlock.height(); j++) {
                if (currentBlock.getShape(i, j) != BoardElement.EMPTY)
                    board.get(y + j)[x + i] = BoardElement.EMPTY;
            }
        }
    }

    public void moveDownAndCheck() {
        Down down = new Down();
        down.run();
    }

    public void moveRight() {
        Right right = new Right();
        right.run();
    }

    public void moveLeft() {
        Left left = new Left();
        left.run();
    }

    public void moveStraightDown() {
        Down down = new Down();
        while (down.run() == Result.OK) {
        }
    }

    public void checkBoard() {
        for (int i = 0; i < configModel.HEIGHT; i++) {
            boolean isRaw = true;
            for (int j = 0; j < configModel.WIDTH; j++) {
                if (board.get(i)[j] == BoardElement.EMPTY) {
                    isRaw = false;
                    break;
                }
            }
            if (isRaw)
                shiftDown(i - 1);
        }
    }

    public void shiftDown(int startHeight) {
        for (int i = startHeight; i >= 0; i--) {
            for (int j = 0; j < configModel.WIDTH; j++) {
                board.get(i + 1)[j] = board.get(i)[j];
            }
        }
    }
}
