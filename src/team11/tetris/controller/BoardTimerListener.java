package team11.tetris.controller;

import team11.tetris.model.BoardModel;
import team11.tetris.view.BoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardTimerListener implements ActionListener {
    private BoardModel model;
    private BoardView view;

    public BoardTimerListener(BoardModel model, BoardView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //@TODO model.moveDown();
        //@TODO view.drawBoard();
    }
}
