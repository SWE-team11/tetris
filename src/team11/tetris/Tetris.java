package team11.tetris;

import team11.tetris.model.ConfigModel;
import team11.tetris.presenter.BoardPresenter;
import team11.tetris.model.BoardModel;
import team11.tetris.view.BoardView;

public class Tetris {

	public static void main(String[] args) {
		ConfigModel configModel = new ConfigModel();
		BoardModel boardModel = new BoardModel(configModel);
		BoardPresenter boardPresenter = new BoardPresenter(boardModel);
		boardPresenter.setVisible(true);
	}
}