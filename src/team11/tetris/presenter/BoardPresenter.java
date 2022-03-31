package team11.tetris.presenter;

import team11.tetris.model.BoardModel;
import team11.tetris.model.ConfigModel;
import team11.tetris.view.BoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class BoardPresenter {
    private BoardModel boardModel;
    private BoardView boardView;
    private Timer timer;
    
    private static final int initInterval = 1000;

    public BoardPresenter(BoardModel boardModel) {
        this.boardModel = boardModel;
        this.boardView = new BoardView(this);

        this.boardModel.setRandomBlock();
        this.boardView.drawBoard(this.boardModel.getBoard());

        timer = new Timer(initInterval, new TimerActionListener());
        timer.start();
    }

    public class TimerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            moveDown();
        }
    }

    public void setVisible(boolean visible) {
        if (visible) {
            boardView.setSize(400, 600);
            boardView.setVisible(true);
        } else {
            boardView.setVisible(false);
        }
    }

    public void moveDown() {
        boardModel.moveDown();
        boardView.drawBoard(boardModel.getBoard());
    }

    public void moveLeft() {
        boardModel.moveLeft();
        boardView.drawBoard(boardModel.getBoard());
    }

    public void moveRight() {
        boardModel.moveRight();
        boardView.drawBoard(boardModel.getBoard());
    }
    
    public void moveStraightDown() {
        boardModel.moveStraightDown();
        boardView.drawBoard(boardModel.getBoard());
    }
}
