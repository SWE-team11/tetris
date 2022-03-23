package team11.tetris;

import team11.tetris.controller.BoardKeyListener;
import team11.tetris.model.BoardModel;
import team11.tetris.view.BoardView;

public class Tetris {

	public static void main(String[] args) {
		BoardView boardView = new BoardView();
		BoardModel boardModel = new BoardModel();
		// @TODO model.registerObserver(view);
		BoardKeyListener boardKeyListener = new BoardKeyListener(boardModel, boardView);
		boardView.setSize(400, 600);
		boardView.setVisible(true);
	}
}