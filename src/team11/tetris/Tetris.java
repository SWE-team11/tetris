package team11.tetris;

import team11.tetris.model.ConfigModel;
import team11.tetris.presenter.BoardPresenter;
import team11.tetris.model.BoardModel;
import team11.tetris.view.BoardView;

public class Tetris {

	public static void main(String[] args) {
		BoardView boardView = new BoardView();
		BoardModel boardModel = new BoardModel();
		ConfigModel configModel = new ConfigModel();
		BoardPresenter boardKeyListener = new BoardPresenter(boardModel, configModel, boardView);
		boardView.setSize(400, 600);
		boardView.setVisible(true);
	}
}