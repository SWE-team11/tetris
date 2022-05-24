package tetris.view;

import tetris.presenter.MultiPresenter;

import javax.swing.*;

public class MultiView extends JFrame {

    private MultiPresenter multiPresenter;

    public MultiView(final MultiPresenter multiPresenter) {
        this.multiPresenter = multiPresenter;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
