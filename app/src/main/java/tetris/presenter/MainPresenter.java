package tetris.presenter;

import tetris.utils.Presenter;
import tetris.view.MainView;

public class MainPresenter implements Presenter {
    private MainView mainView;
    private final int VIEW_WIDTH = 400;
    private final int VIEW_HEIGHT = 600;

    public MainPresenter() {
        initPresent();
    }

    @Override
    public void initPresent() {
        this.mainView = new MainView(this);
    }

    @Override
    public final void setVisible(final boolean visible) {
        if (visible) {
            mainView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            mainView.setLocationRelativeTo(null);
            mainView.setVisible(true);
        } else {
            mainView.setVisible(false);
        }
    }
}
