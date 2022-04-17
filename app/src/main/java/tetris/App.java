package tetris;

import tetris.model.ConfigModel;
import tetris.presenter.GamePresenter;
import tetris.model.GameModel;

public class App {
    protected App() {
        throw new UnsupportedOperationException();
    }
    public static void main(final String[] args) {
        ConfigModel configModel = ConfigModel.getInstance();
        GameModel gameModel = new GameModel(configModel);
        GamePresenter gamePresenter = new GamePresenter(gameModel);
        gamePresenter.setVisible(true);
    }
}
