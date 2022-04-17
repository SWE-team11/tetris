package tetris.presenter;

import tetris.model.BoardModel;
import tetris.view.BoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class BoardPresenter {
    private BoardModel boardModel;
    private BoardView boardView;
    private Timer timer;
    static final int VIEW_WIDTH = 400;
    static final int VIEW_HEIGHT = 600;

    private static final int INIT_INTERVAL = 1000;

    public BoardPresenter(final BoardModel board) {
        this.boardModel = board;
        this.boardView = new BoardView(this);

        this.boardModel.setRandomBlock();
        this.boardView.drawBoard(this.boardModel.getBoard());

        timer = new Timer(INIT_INTERVAL, new TimerActionListener());
        timer.start();
    }

    public class TimerActionListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            moveDown();
        }
    }

    public final void setVisible(final boolean visible) {
        if (visible) {
            boardView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            boardView.setVisible(true);
        } else {
            boardView.setVisible(false);
        }
    }

    public final void moveRotate() {
        boardModel.moveRotate();
        boardView.drawBoard(boardModel.getBoard());
    }

    public final void moveDown() {
        boardModel.moveDownAndCheck();
        boardView.drawBoard(boardModel.getBoard());
    }

    public final void moveLeft() {
        boardModel.moveLeft();
        boardView.drawBoard(boardModel.getBoard());
    }

    public final void moveRight() {
        boardModel.moveRight();
        boardView.drawBoard(boardModel.getBoard());
    }

    public final void moveStraightDown() {
        boardModel.moveStraightDown();
        boardView.drawBoard(boardModel.getBoard());
    }
}
