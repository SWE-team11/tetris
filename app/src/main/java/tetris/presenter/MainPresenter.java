package tetris.presenter;

import tetris.utils.Presenter;
import tetris.view.MainView;

public class MainPresenter implements Presenter {
    private final MainView mainView;
    static final int VIEW_WIDTH = 400;
    static final int VIEW_HEIGHT = 600;

    public MainPresenter() {
        this.mainView = new MainView(this);
    }

    @Override
    public void initPresent() {

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
