package tetris.view;

import tetris.App;
import tetris.presenter.MainPresenter;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private MainPresenter mainPresenter;

    private ImageIcon getResource(String path) {
        return new ImageIcon(getClass().getClassLoader().getResource(path));
    }

    private Image background = getResource("image/background.png").getImage();

    public MainView(final MainPresenter presenter) {
        this.mainPresenter = presenter;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("TETRIS");

        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.RIGHT);
        JPanel btnPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, null);
            }
        };
        btnPanel.setLayout(null);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(200 , 0 , 0 , 25));

        JButton playBtn = new JButton(getResource("image/play.png"));
        JButton normalBtn = new JButton(getResource("image/normal.png"));
        JButton normalClickedBtn = new JButton(getResource("image/normal_clicked.png"));
        JButton itemBtn = new JButton(getResource("image/item.png"));
        JButton itemClickedBtn = new JButton(getResource("image/item_clicked.png"));
        JButton settingBtn = new JButton(getResource("image/setting.png"));
        JButton exitBtn = new JButton(getResource("image/exit.png"));

        playBtn.setBorderPainted(false);
        playBtn.setContentAreaFilled(false);
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                App.navigate(App.View.Game);
            }
        });
        playBtn.setBounds(30, 300, 320, 90);


        normalClickedBtn.setBorderPainted(false);
        normalClickedBtn.setContentAreaFilled(false);
        normalClickedBtn.setBounds(30, 215, 150, 60);

        itemBtn.setBorderPainted(false);
        itemBtn.setContentAreaFilled(false);
        itemBtn.setBounds(200, 215, 150, 60);

        settingBtn.setBorderPainted(false);
        settingBtn.setContentAreaFilled(false);
        settingBtn.setBounds(250, 485, 46, 46);

        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setBounds(305, 485, 46, 46);

        this.setContentPane(btnPanel);
        btnPanel.add(normalClickedBtn);
        btnPanel.add(itemBtn);
        btnPanel.add(playBtn);
        btnPanel.add(settingBtn);
        btnPanel.add(exitBtn);
    }
}
