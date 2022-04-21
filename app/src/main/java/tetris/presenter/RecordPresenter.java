package tetris.presenter;

import tetris.utils.Presenter;
import tetris.view.RecordView;

import java.util.ArrayList;

import tetris.model.RecordModel;
import tetris.utils.Record;

public class RecordPresenter implements Presenter {
    private RecordView recordView;
    private final int VIEW_WIDTH = 400;
    private final int VIEW_HEIGHT = 600;

    public RecordPresenter() {
        initPresent();
    }

    @Override
    public void initPresent() {
        recordView = new RecordView(this);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            recordView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            recordView.setLocationRelativeTo(null);
            recordView.setVisible(true);
        } else {
            recordView.setVisible(false);
        }
    }

    public ArrayList<Record> getRecords() {
        return RecordModel.rankedRecords;
    }
}
