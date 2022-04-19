package tetris;

import tetris.model.ConfigModel;
import tetris.presenter.GamePresenter;
import tetris.model.GameModel;
import tetris.presenter.MainPresenter;
import tetris.utils.Presenter;

public class App {
    private static ConfigModel configModel;
    private static GameModel gameModel;
    private static GamePresenter gamePresenter;
    private static MainPresenter mainPresenter;
    private static Presenter currentPresenter;

    public enum View {
        Main, Game
    }

    protected App() {
        throw new UnsupportedOperationException();
    }

    public static void main(final String[] args) {
        ConfigModel.initConfig();
        gamePresenter = new GamePresenter();
        mainPresenter = new MainPresenter();
        currentPresenter = mainPresenter;
        currentPresenter.setVisible(true);
    }

    public static void navigate(final View view) {
        currentPresenter.setVisible(false);
        switch (view) {
            case Main -> currentPresenter = mainPresenter;
            case Game -> currentPresenter = gamePresenter;
        }
        currentPresenter.setVisible(true);
    }
}
