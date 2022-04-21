package tetris.view;

import tetris.presenter.ConfigPresenter;

import javax.swing.*;

public class ConfigView extends JFrame {
    private ConfigPresenter configPresenter;

    public ConfigView(final ConfigPresenter presenter) {
        super("TETRIS");
        configPresenter = presenter;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
