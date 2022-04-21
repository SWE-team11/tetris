package tetris.model;

import tetris.utils.Block;
import tetris.utils.BlockKind;
import tetris.utils.BoardElement;
import tetris.presenter.GamePresenter;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {
    private GamePresenter gamePresenter;
    private ArrayList<BoardElement[]> board;
    private Block currentBlock;
    private Block nextBlock;
    private double score = 0;
    private int deletedRowCount = 0;
    private int itemCount = 0;

    private final int DEFAULT_POS_X = 3;
    private final int DEFAULT_POS_Y = 0;
    private final int ITEM_GENERATE_INTERVAL = 2;
    private double gameSpeed = ConfigModel.gameSpeed;

    private int posX;
    private int posY;

    public GameModel(final GamePresenter presenter) {
        this.gamePresenter = presenter;
        initBoard(ConfigModel.boardWidth, ConfigModel.boardHeight);
        this.setRandomBlock();
        posX = DEFAULT_POS_X;
        posY = DEFAULT_POS_Y;
    }

    public final ArrayList<BoardElement[]> getBoard() {
        return board;
    }

    public double getScore() {
        return this.score;
    }

    public final void initBoard(final int width, final int height) {
        board = new ArrayList<>(height);
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
        BlockKind blockKind;
        int rndNum = 0;

        if(nextBlock == null) {
            rndNum = rnd.nextInt(BlockKind.getTetrominoSize());
            blockKind = BlockKind.values()[rndNum];
            currentBlock = BlockKind.getBlockInstance(blockKind);
        } else {
            currentBlock = nextBlock;
        }

        if (itemCount >= ITEM_GENERATE_INTERVAL) {
            itemCount = Math.max(0, itemCount - ITEM_GENERATE_INTERVAL);
            rndNum = rnd.nextInt(BlockKind.getItemSize()) + BlockKind.getTetrominoSize();
        } 
        else {
            switch (ConfigModel.gameDifficulty) {
                case EASY -> {
                    rndNum = rnd.nextInt(72) / 10; // I_BLOCK 60 ~ 71, weight 12
                    if(rndNum > 6) rndNum = 6;
                }
                case NORMAL -> rndNum = rnd.nextInt(70) / 10; // I_BLOCK 60 ~ 69, weight 10
                case HARD -> rndNum = rnd.nextInt(68) / 10; // I_BLOCK 60 ~ 67, weight 8
            }
        }

        blockKind = BlockKind.values()[rndNum];
        nextBlock = BlockKind.getBlockInstance(blockKind);

        posX = DEFAULT_POS_X;
        posY = DEFAULT_POS_Y;
        GameOver gameOver = new GameOver();
        if (gameOver.canPlaceBlock()) {
            placeBlock();
        } else {
            gamePresenter.gameStop();
            gamePresenter.gameOver();
        }

        gameSpeedUp();
    }

    public void gameSpeedUp() {
        if(gameSpeed > ConfigModel.gameSpeed * 5) return;
        gameSpeed *= 1.005;
        gamePresenter.setTimeInterval((int)(1000 / gameSpeed));
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

    class GameOver extends Move {
        public boolean ifBoundaryGoOver() {
            return false;
        }

        public void move() {

        }

        public void moveBack() {

        }

        public void hook() {

        }
    }

    class Rotate extends Move {
        public boolean ifBoundaryGoOver() {
            return (posY + currentBlock.height() > ConfigModel.boardHeight)
                    || (posX + currentBlock.width() > ConfigModel.boardWidth);
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
            return posY + currentBlock.height() > ConfigModel.boardHeight;
        }

        public void move() {
            posY++;
            score += ConfigModel.getScoreRate();
        }

        public void moveBack() {
            posY--;
        }

        public void hook() {
            if(currentBlock.isItemBlock()) {
                triggerItem();
            } else {
                checkRaw();
            }
            setRandomBlock();
        }
    }

    class Right extends Move {
        public boolean ifBoundaryGoOver() {
            return posX + currentBlock.width() > ConfigModel.boardWidth;
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
        int cnt = 0;
        while (true) {
            cnt++;
            if (down.run() == Result.ERR) {
                break;
            }
        }
        score += cnt * ConfigModel.getScoreRate();
    }

    public final void moveRotate() {
        Rotate rotate = new Rotate();
        rotate.run();
    }

    public final void triggerItem() {
        switch (currentBlock.getKind()) {
            case LINE_CLEAR_ITEM -> {
                for (int j = 0; j < ConfigModel.boardWidth; j++) {
                    board.get(posY + currentBlock.getItemPosY())[j] = BoardElement.DELETE;
                }
                checkRaw();

                score += 100 * ConfigModel.getScoreRate();
                deletedRowCount++;
                itemCount++;
                gameSpeedUp();
            }
            case BOMB_ITEM -> {
                int Xstart = Math.max(0, posX - 2);
                int Xend = Math.min(ConfigModel.boardWidth - 1, posX + 2);
                int Ystart = Math.max(0, posY - 2);
                int Yend = Math.min(ConfigModel.boardHeight - 1, posY + 2);
                int cnt = 0;
                for (int i = Ystart; i <= Yend; i++) {
                    for (int j = Xstart; j <= Xend; j++) {
                        if (board.get(i)[j] != BoardElement.EMPTY) {
                            board.get(i)[j] = BoardElement.DELETE;
                            cnt++;
                        }
                    }
                }
                score += 10 * cnt * ConfigModel.getScoreRate();
                gameSpeedUp();
            }
            case PERFECT_CLEAR_ITEM -> {
                boolean isRaw = true;
                for (int j = 0; j < ConfigModel.boardWidth; j++) {
                    if (board.get(posY + currentBlock.getItemPosY())[j] == BoardElement.EMPTY) {
                        isRaw = false;
                        break;
                    }
                }
                if (isRaw) {
                    initBoard(ConfigModel.boardWidth, ConfigModel.boardHeight);
                    score += 1000 * ConfigModel.getScoreRate();
                    gameSpeedUp();
                }
                else {
                    board.get(posY + currentBlock.getItemPosY())[posX + currentBlock.getItemPosX()] = currentBlock.getBoardElement();
                }
            }
            case SAME_DELETE_ITEM -> {
                int cnt = 0;
                for (int i = 0; i < ConfigModel.boardHeight; i++) {
                    for (int j = 0; j < ConfigModel.boardWidth; j++) {
                        if(board.get(i)[j] == currentBlock.getBoardElement()
                                || board.get(i)[j] == BoardElement.SAME_DELETE_ITEM) {
                            board.get(i)[j] = BoardElement.DELETE;
                            cnt++;
                        }
                    }
                }
                score += 10 * cnt * ConfigModel.getScoreRate();
                gameSpeedUp();
            }
        }
    }

    public final void checkRaw() {
        for (int i = 0; i < ConfigModel.boardHeight; i++) {
            boolean isRaw = true;
            for (int j = 0; j < ConfigModel.boardWidth; j++) {
                if (board.get(i)[j] == BoardElement.EMPTY) {
                    isRaw = false;
                    break;
                }
            }
            if (isRaw) {
                for (int j = 0; j < ConfigModel.boardWidth; j++) {
                    board.get(i)[j] = BoardElement.DELETE;
                }
                score += 100 * ConfigModel.getScoreRate();
                deletedRowCount++;
                itemCount++;
                gameSpeedUp();
            }
        }
        gamePresenter.drawBoard();
    }

    public final void runDelete() {
        for (int i = 0; i < ConfigModel.boardHeight; i++) {
            boolean isRaw = true;
            for (int j = 0; j < ConfigModel.boardWidth; j++) {
                if (board.get(i)[j] != BoardElement.DELETE) {
                    isRaw = false;
                    break;
                }
            }
            if (isRaw) {
                shiftDown(i - 1);
            }
            else {
                for (int j = 0; j < ConfigModel.boardWidth; j++) {
                    if (board.get(i)[j] == BoardElement.DELETE) {
                        board.get(i)[j] = BoardElement.EMPTY;
                    }
                }
            }
        }
    }

    public final void shiftDown(final int startHeight) {
        eraseCurr();
        for (int i = startHeight; i >= 0; i--) {
            for (int j = 0; j < ConfigModel.boardWidth; j++) {
                board.get(i + 1)[j] = board.get(i)[j];
            }
        }
        placeBlock();
    }
}
