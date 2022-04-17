package tetris;

import tetris.model.ConfigModel;
import tetris.presenter.BoardPresenter;
import tetris.model.BoardModel;

public class App {
    protected App() {
        throw new UnsupportedOperationException();
    }
    public static void main(final String[] args) {
        ConfigModel configModel = ConfigModel.getInstance();
        BoardModel boardModel = new BoardModel(configModel);
        BoardPresenter boardPresenter = new BoardPresenter(boardModel);
        boardPresenter.setVisible(true);
    }
}
