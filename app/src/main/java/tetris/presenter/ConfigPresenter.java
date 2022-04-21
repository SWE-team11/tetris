package tetris.presenter;

import tetris.utils.Presenter;
import tetris.view.ConfigView;

public class ConfigPresenter implements Presenter {
    private ConfigView configView;
    private final int VIEW_WIDTH = 400;
    private final int VIEW_HEIGHT = 600;

    public ConfigPresenter() {
        initPresent();
    }

    @Override
    public void initPresent() {
        configView = new ConfigView(this);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            configView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            configView.setLocationRelativeTo(null);
            configView.setVisible(true);
        } else {
            configView.setVisible(false);
        }
    }
}
