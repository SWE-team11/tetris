package tetris;

import tetris.model.ConfigModel;
import tetris.model.RecordModel;
import tetris.presenter.ConfigPresenter;
import tetris.presenter.GamePresenter;
import tetris.presenter.RecordPresenter;
import tetris.presenter.MainPresenter;
import tetris.utils.Presenter;


public class App {
    private static GamePresenter gamePresenter;
    private static MainPresenter mainPresenter;
    private static ConfigPresenter configPresenter;
    private static RecordPresenter recordPresenter;
    private static Presenter currentPresenter;

    public enum View {
        MAIN, GAME, CONFIG, RECORD, MULTI
    }

    protected App() {
        throw new UnsupportedOperationException();
    }

    public static void main(final String[] args) {
        RecordModel.loadRecord();
        ConfigModel.loadConfig();
        gamePresenter = new GamePresenter();
        mainPresenter = new MainPresenter();
        configPresenter = new ConfigPresenter();
        recordPresenter = new RecordPresenter();
        currentPresenter = mainPresenter;
        currentPresenter.setVisible(true);
    }

    public static void navigate(final View view) {
        currentPresenter.setVisible(false);
        switch (view) {
            case MAIN -> currentPresenter = mainPresenter;
            case GAME -> currentPresenter = gamePresenter;
            case CONFIG -> currentPresenter = configPresenter;
            case RECORD -> currentPresenter = recordPresenter;
        }
        currentPresenter.initPresent();
        currentPresenter.setVisible(true);
    }
}
