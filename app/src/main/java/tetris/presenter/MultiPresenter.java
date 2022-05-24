package tetris.presenter;

import tetris.utils.Presenter;
import tetris.view.MultiView;

public class MultiPresenter implements Presenter {
    private MultiView multiView;
    private final int VIEW_WIDTH = 400;
    private final int VIEW_HEIGHT = 600;

    public MultiPresenter() {
        initPresent();
    }

    @Override
    public void initPresent() {
        multiView = new MultiView(this);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            multiView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            multiView.setLocationRelativeTo(null);
            multiView.setVisible(true);
        } else {
            multiView.setVisible(false);
        }
    }
}
