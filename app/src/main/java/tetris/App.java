package tetris;

import tetris.model.ConfigModel;
import tetris.model.RecordModel;
import tetris.presenter.*;
import tetris.utils.Presenter;


public class App {
    private static GamePresenter gamePresenter;
    private static MainPresenter mainPresenter;
    private static ConfigPresenter configPresenter;
    private static RecordPresenter recordPresenter;
    private static Presenter currentPresenter;
    private static BattlePresenter battlePresenter;

    public enum View {
        MAIN, GAME, CONFIG, RECORD, BATTLE
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
        battlePresenter = new BattlePresenter();
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
            case BATTLE -> currentPresenter = battlePresenter;
        }
        currentPresenter.initPresent();
        currentPresenter.setVisible(true);
    }
}
