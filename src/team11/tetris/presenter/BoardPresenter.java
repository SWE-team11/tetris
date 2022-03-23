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

    public BoardPresenter(BoardModel boardModel, ConfigModel configModel, BoardView boardView) {
        this.boardModel = boardModel;
        this.configModel = configModel;
        this.boardView = boardView;

        this.boardModel.initBoard(configModel.WIDTH, configModel.HEIGHT);
        this.boardModel.setRandomBlock();
        this.boardView.injectModel(boardModel, configModel);
        this.boardView.setKeyListner(new PlayerKeyListener());
        this.boardView.setTimerActionListener(new TimerActionListener());
        this.boardView.initView();
    }

    public void moveDown() {
        boardModel.eraseCurr();
        if(boardModel.y < configModel.HEIGHT - boardModel.currentBlock.height()) boardModel.y++;
        else {
            boardView.placeBlock();
            boardModel.setRandomBlock();
            boardModel.x = 3;
            boardModel.y = 0;
        }
        boardView.placeBlock();
    }

    public void moveRight() {
        boardModel.eraseCurr();
        if(boardModel.x < configModel.WIDTH - boardModel.currentBlock.width()) {
            boardModel.x++;
        }
        boardView.placeBlock();
    }
    public void moveLeft() {
        boardModel.eraseCurr();
        if(boardModel.x > 0) {
            boardModel.x--;
        }
        boardView.placeBlock();
    }

    public class PlayerKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    moveDown();
                    boardView.drawBoard();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveRight();
                    boardView.drawBoard();
                    break;
                case KeyEvent.VK_LEFT:
                    moveLeft();
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
            moveDown();
            boardView.drawBoard();
        }
    }
}
