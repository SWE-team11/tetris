package tetris.presenter;

import tetris.model.ConfigModel;
import tetris.model.GameModel;
import tetris.model.RecordModel;
import tetris.utils.Presenter;
import tetris.view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.Timer;

public class GamePresenter implements Presenter {
    private GameModel gameModel;
    private GameView gameView;
    private final Timer mainTimer;
    private final Timer deleteTimer;
    private final Timer weightItemTimer;

    private static final double INIT_INTERVAL = 1000 / ConfigModel.gameSpeed;

    public GamePresenter() {
        mainTimer = new Timer((int)INIT_INTERVAL, new MainTimerActionListener());
        deleteTimer = new Timer((int)INIT_INTERVAL / 3, new DeleteTimerActionListener());
        weightItemTimer = new Timer((int)INIT_INTERVAL / 5, new WeightItemTimerActionListener());

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

    public class WeightItemTimerActionListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            gameModel.moveWeightItemDown();
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
        deleteTimer.stop();
    }

    public final void gameOver() {
        gameView.drawScrollDialog((int)gameModel.getScore());
        gameView.stopPlayerKeyListen();
        gameView.stopPauseKeyListen();
        gameView.setVisiblePauseDialog(false);
        gameView.setVisibleScoreDialog(true);
        mainTimer.stop();
    }

    public final void recordGame(String name) {
        Date date = new Date();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM.dd")
                .withZone(ZoneId.systemDefault());
        String dateToStr = format.format(date.toInstant());

        if(name == "") name = "Noname";
        RecordModel.addRecord((int)gameModel.getScore(), gameModel.getDeletedRaw(), ConfigModel.gameMode, ConfigModel.gameDifficulty, dateToStr, name);
    }

    public final void weightItemStart() {
        gameView.stopPlayerKeyListen();
        mainTimer.stop();
        weightItemTimer.start();
    }

    public final void weightItemStop() {
        gameView.startPlayerKeyListen();
        mainTimer.start();
        weightItemTimer.stop();
    }

    public void drawView() {
        gameView.drawBoard(gameModel.getBoard());
        gameView.drawNextBlock(gameModel.getNextBlock());
        gameView.drawScore(gameModel.getScore());
        gameView.drawLevel();
        gameView.drawDeletedRaw(gameModel.getDeletedRaw());
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
