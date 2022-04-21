package tetris.presenter;

import tetris.model.ConfigModel;
import tetris.model.GameModel;
import tetris.utils.Presenter;
import tetris.view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GamePresenter implements Presenter {
    private GameModel gameModel;
    private GameView gameView;
    private final Timer mainTimer;
    private final Timer deleteTimer;
    private final int VIEW_WIDTH = 400;
    private final int VIEW_HEIGHT = 600;

    private static final int INIT_INTERVAL = 1000 / ConfigModel.gameSpeed;

    public GamePresenter() {
        mainTimer = new Timer(INIT_INTERVAL, new MainTimerActionListener());
        deleteTimer = new Timer(INIT_INTERVAL/3, new DeleteTimerActionListener());

        initPresent();
    }

    public class MainTimerActionListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            moveDown();
        }
    }

    public class DeleteTimerActionListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            gameModel.runDelete();
            drawView();
        }
    }

    @Override
    public void initPresent() {
        this.gameModel = new GameModel(this);
        this.gameView = new GameView(this);
        this.gameView.drawBoard(this.gameModel.getBoard());
    }

    @Override
    public final void setVisible(final boolean visible) {
        if (visible) {
            gameView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            gameView.setLocationRelativeTo(null);
            gameView.setVisible(true);
            gameStart();
        } else {
            gameView.setVisible(false);
            gameStop();
        }
    }

    public final void setTimeInterval(int interval) {
        mainTimer.setDelay(interval);
        deleteTimer.setDelay(Math.max(interval/3, 200));
    }

    public final void gameStart() {
        gameView.stopPauseKeyListen();
        gameView.startPlayerKeyListen();
        gameView.setVisiblePauseDialog(false);
        gameView.drawBoard(gameModel.getBoard());
        mainTimer.start();
        deleteTimer.start();
    }

    public final void gameStop() {
        gameView.stopPlayerKeyListen();
        gameView.startPauseKeyListen();
        gameView.setVisiblePauseDialog(true);
        mainTimer.stop();
    }

    public void drawView() {
        gameView.drawBoard(gameModel.getBoard());
        gameView.drawNextBlock(gameModel.getNextBlock());
        gameView.drawScore(gameModel.getScore());
        gameView.drawLevel();
        gameView.drawDeletedRaw(gameModel.getDeletedRaw());
    }

    public final void gameOver() {
        gameView.stopPlayerKeyListen();
        gameView.stopPauseKeyListen();
        gameView.setVisiblePauseDialog(false);
        mainTimer.stop();
    }

    public final void moveRotate() {
        gameModel.moveRotate();
        drawView();
    }

    public final void moveDown() {
        gameModel.moveDownAndCheck();
        drawView();
    }

    public final void moveLeft() {
        gameModel.moveLeft();
        drawView();
    }

    public final void moveRight() {
        gameModel.moveRight();
        drawView();
    }

    public final void moveStraightDown() {
        gameModel.moveStraightDown();
        drawView();
    }

}
