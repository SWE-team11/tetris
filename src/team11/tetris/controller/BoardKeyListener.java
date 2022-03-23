package team11.tetris.controller;

import team11.tetris.model.BoardModel;
import team11.tetris.view.BoardView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BoardKeyListener implements KeyListener {
    private BoardModel model;
    private BoardView view;

    public BoardKeyListener(BoardModel model, BoardView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // @TODO model의 movedown 실행, model의 rotate 실행, view의 drawBoard 실행
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_RIGHT:
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_UP:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
