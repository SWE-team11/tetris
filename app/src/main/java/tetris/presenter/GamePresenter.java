package tetris.presenter;

import tetris.model.GameModel;
import tetris.utils.BoardElement;
import tetris.utils.Presenter;
import tetris.view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GamePresenter implements Presenter {
    private final GameModel gameModel;
    private final GameView gameView;
    private final Timer timer;
    private static final int VIEW_WIDTH = 400;
    private static final int VIEW_HEIGHT = 600;

    private static final int INIT_INTERVAL = 1000;

    public GamePresenter() {
        this.gameModel = new GameModel(this);
        this.gameView = new GameView(this);

        this.gameModel.setRandomBlock();
        this.gameView.drawBoard(this.gameModel.getBoard());

        timer = new Timer(INIT_INTERVAL, new TimerActionListener());
    }

    public class TimerActionListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            gameModel.runDelete();
            moveDown();
            System.out.println(gameModel.getScore());
        }
    }

    public final void setVisible(final boolean visible) {
        if (visible) {
            gameView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            gameView.setVisible(true);
            gameStart();
        } else {
            gameView.setVisible(false);
            gameStop();
        }
    }


    public final void gameStart() {
        gameView.startPlayerKeyListen();
        timer.start();
    }

    public final void gameStop() {
        gameView.stopPlayerKeyListen();
        timer.stop();
    }

    public void drawBoard() {
        gameView.drawBoard(gameModel.getBoard());
    }

    public final void moveRotate() {
        gameModel.moveRotate();
        drawBoard();
    }

    public final void moveDown() {
        gameModel.moveDownAndCheck();
        drawBoard();
    }

    public final void moveLeft() {
        gameModel.moveLeft();
        drawBoard();
    }

    public final void moveRight() {
        gameModel.moveRight();
        drawBoard();
    }

    public final void moveStraightDown() {
        gameModel.moveStraightDown();
        drawBoard();
    }

}
