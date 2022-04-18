package tetris.model;

import tetris.blocks.*;
import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {
    private ConfigModel configModel;
    private ArrayList<BoardElement[]> board;
    private Block currentBlock;
    private Block nextBlock;

    final int DEFAULT_X_POS = 3;
    final int DEFAULT_Y_POS = 0;

    private int posX;
    private int posY;

    public GameModel(final ConfigModel config) {
        this.configModel = config;
        initBoard(configModel.WIDTH, configModel.HEIGHT);
        posX = DEFAULT_X_POS;
        posY = DEFAULT_Y_POS;
    }

    public final ArrayList<BoardElement[]> getBoard() {
        return board;
    }

    public final void initBoard(final int width, final int height) {
        board = new ArrayList<BoardElement[]>(height);
        for (int i = 0; i < height; i++) {
            BoardElement[] row = new BoardElement[width];
            for (int j = 0; j < width; j++) {
                row[j] = BoardElement.EMPTY;
            }
            board.add(row);
        }
    }

    public final void setRandomBlock() {
        Random rnd = new Random(System.currentTimeMillis());
        int rndNum = rnd.nextInt(BlockKind.values().length);
        BlockKind blockKind = BlockKind.values()[rndNum];
        currentBlock = BlockKind.getBlockInstance(blockKind);
        posX = DEFAULT_X_POS;
        posY = DEFAULT_Y_POS;
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
            if (ret == Result.ERR) {
                throw new IllegalStateException();
            }
            hook();
        }

        public boolean canPlaceBlock() {
            if (ifBoundaryGoOver()) {
                return false;
            }
            for (int i = 0; i < currentBlock.width(); i++) {
                for (int j = 0; j < currentBlock.height(); j++) {
                    if (board.get(posY + j)[posX + i] != BoardElement.EMPTY
                            && currentBlock.getShape(i, j) != BoardElement.EMPTY) {
                        return false;
                    }
                }
            }
            return true;
        }

        abstract boolean ifBoundaryGoOver();

        public abstract void move();

        public abstract void moveBack();

        public abstract void hook();
    }

    class Rotate extends Move {
        public boolean ifBoundaryGoOver() {
            return (posY + currentBlock.height() > ConfigModel.HEIGHT)
                    || (posX + currentBlock.width() > ConfigModel.WIDTH);
        }

        public void move() {
            currentBlock.rotate();
        }

        public void moveBack() {
            currentBlock.rotate();
            currentBlock.rotate();
            currentBlock.rotate();
        }

        public void hook() {
        }
    }

    class Down extends Move {
        public boolean ifBoundaryGoOver() {
            return posY + currentBlock.height() > ConfigModel.HEIGHT;
        }

        public void move() {
            posY++;
        }

        public void moveBack() {
            posY--;
        }

        public void hook() {
            checkBoard();
            setRandomBlock();
            placeBlock();
        }
    }

    class Right extends Move {
        public boolean ifBoundaryGoOver() {
            return posX + currentBlock.width() > ConfigModel.WIDTH;
        }

        public void move() {
            posX++;
        }

        public void moveBack() {
            posX--;
        }

        public void hook() {
        }
    }

    class Left extends Move {
        public boolean ifBoundaryGoOver() {
            return posX < 0;
        }

        public void move() {
            posX--;
        }

        public void moveBack() {
            posX++;
        }

        public void hook() {
        }
    }

    public final Result placeBlock() {
        for (int i = 0; i < currentBlock.width(); i++) {
            for (int j = 0; j < currentBlock.height(); j++) {
                if (board.get(posY + j)[posX + i] != BoardElement.EMPTY
                        && currentBlock.getShape(i, j) != BoardElement.EMPTY) {
                    return Result.ERR;
                }
                if (currentBlock.getShape(i, j) != BoardElement.EMPTY) {
                    board.get(posY + j)[posX + i] = currentBlock.getShape(i, j);
                }
            }
        }
        return Result.OK;
    }

    public final void eraseCurr() {
        for (int i = 0; i < currentBlock.width(); i++) {
            for (int j = 0; j < currentBlock.height(); j++) {
                if (currentBlock.getShape(i, j) != BoardElement.EMPTY) {
                    board.get(posY + j)[posX + i] = BoardElement.EMPTY;
                }
            }
        }
    }

    public final void moveDownAndCheck() {
        Down down = new Down();
        down.run();
    }

    public final void moveRight() {
        Right right = new Right();
        right.run();
    }

    public final void moveLeft() {
        Left left = new Left();
        left.run();
    }

    public final void moveStraightDown() {
        Down down = new Down();
        while (true) {
            if (down.run() == Result.ERR) {
                break;
            }
        }
    }

    public final void moveRotate() {
        Rotate rotate = new Rotate();
        rotate.run();
    }

    public final void checkBoard() {
        for (int i = 0; i < configModel.HEIGHT; i++) {
            boolean isRaw = true;
            for (int j = 0; j < configModel.WIDTH; j++) {
                if (board.get(i)[j] == BoardElement.EMPTY) {
                    isRaw = false;
                    break;
                }
            }
            if (isRaw) {
                shiftDown(i - 1);
            }
        }
    }

    public final void shiftDown(final int startHeight) {
        for (int i = startHeight; i >= 0; i--) {
            for (int j = 0; j < configModel.WIDTH; j++) {
                board.get(i + 1)[j] = board.get(i)[j];
            }
        }
    }
}
