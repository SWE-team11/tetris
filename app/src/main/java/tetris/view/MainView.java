package tetris.view;

import tetris.App;
import tetris.presenter.MainPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private MainPresenter mainPresenter;

    private JButton testButton;
    private JPanel pane;

    public MainView(final MainPresenter presenter) {
        super("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainPresenter = presenter;

        pane = new JPanel();
        testButton = new JButton();
        testButton.setBounds(0, 200, 100, 100);
        testButton.setText("게임 화면 이동");
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                App.navigate(App.View.Game);
            }
        });
        pane.add(testButton);
        this.getContentPane().add(pane, BorderLayout.CENTER);
    }
}
