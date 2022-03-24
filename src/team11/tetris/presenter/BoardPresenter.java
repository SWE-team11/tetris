package team11.tetris.presenter;

import team11.tetris.model.BoardModel;
import team11.tetris.model.ConfigModel;
import team11.tetris.view.BoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BoardPresenter {
    private ConfigModel configModel;
    private BoardModel boardModel;
    private BoardView boardView;

    public BoardPresenter(BoardModel boardModel, ConfigModel configModel) {
        this.boardModel = boardModel;
        this.configModel = configModel;
        this.boardView = new BoardView();

        this.boardModel.initBoard(configModel.WIDTH, configModel.HEIGHT);
        this.boardModel.setRandomBlock();
        this.boardView.injectModel(boardModel, configModel);
        this.boardView.setKeyListner(new PlayerKeyListener());
        this.boardView.setTimerActionListener(new TimerActionListener());
        this.boardView.initView();
    }

    public class PlayerKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    if (boardModel.moveDownAndSuccess()) {
                        boardView.placeBlock();
                    } else {
                        boardView.placeBlock();
                        boardModel.setRandomBlock();
                    }
                    boardView.drawBoard();
                    break;
                case KeyEvent.VK_RIGHT:
                    boardModel.moveRight();
                    boardView.placeBlock();
                    boardView.drawBoard();
                    break;
                case KeyEvent.VK_LEFT:
                    boardModel.moveLeft();
                    boardView.placeBlock();
                    boardView.drawBoard();
                    break;
                case KeyEvent.VK_UP:
                    boardModel.eraseCurr();
                    boardModel.currentBlock.rotate();
                    boardView.drawBoard();
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public class TimerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (boardModel.moveDownAndSuccess()) {
                boardView.placeBlock();
            } else {
                boardView.placeBlock();
                boardModel.setRandomBlock();
            }
            boardView.drawBoard();
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
}
