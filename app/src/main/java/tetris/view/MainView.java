package tetris.view;

import tetris.App;
import tetris.model.ConfigModel;
import tetris.presenter.MainPresenter;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

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
                g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(),null);
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
        JButton toRecordBtn = new JButton(getResource("image/toRecord.png"));

        playBtn.setBorderPainted(false);
        playBtn.setContentAreaFilled(false);
        playBtn.addActionListener(e -> App.navigate(App.View.GAME));
        playBtn.setBounds(30, 300, 320, 90);

        normalBtn.setBorderPainted(false);
        normalBtn.setContentAreaFilled(false);
        normalBtn.setBounds(30, 215, 150, 60);
        normalBtn.setVisible(ConfigModel.gameMode == ConfigModel.GameMode.BASIC ? false : true);
        normalBtn.addActionListener(e -> {
            ConfigModel.changeGameMode(ConfigModel.GameMode.BASIC);
            normalBtn.setVisible(false);
            normalClickedBtn.setVisible(true);
            itemBtn.setVisible(true);
            itemClickedBtn.setVisible(false);
            repaint();
        });

        normalClickedBtn.setBorderPainted(false);
        normalClickedBtn.setContentAreaFilled(false);
        normalClickedBtn.setBounds(30, 215, 150, 60);
        normalClickedBtn.setVisible(ConfigModel.gameMode == ConfigModel.GameMode.BASIC ? true : false);

        itemBtn.setBorderPainted(false);
        itemBtn.setContentAreaFilled(false);
        itemBtn.setBounds(200, 215, 150, 60);
        itemBtn.setVisible(ConfigModel.gameMode == ConfigModel.GameMode.BASIC ? true : false);

        itemBtn.addActionListener(e -> {
            ConfigModel.changeGameMode(ConfigModel.GameMode.ITEM);
            normalBtn.setVisible(true);
            normalClickedBtn.setVisible(false);
            itemBtn.setVisible(false);
            itemClickedBtn.setVisible(true);
            repaint();
        });

        itemClickedBtn.setBorderPainted(false);
        itemClickedBtn.setContentAreaFilled(false);
        itemClickedBtn.setBounds(200, 215, 150, 60);
        itemClickedBtn.setVisible(ConfigModel.gameMode == ConfigModel.GameMode.BASIC ? false : true);


        settingBtn.setBorderPainted(false);
        settingBtn.setContentAreaFilled(false);
        settingBtn.setBounds(250, 485, 46, 46);
        settingBtn.addActionListener(e -> {
            App.navigate(App.View.CONFIG);
        });

        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setBounds(305, 485, 46, 46);
        exitBtn.addActionListener(e -> {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        toRecordBtn.setBorderPainted(false);
        toRecordBtn.setContentAreaFilled(false);
        toRecordBtn.setBounds(30, 485, 85, 45);
        toRecordBtn.addActionListener(e -> {
            App.navigate(App.View.RECORD);
        });

        this.setContentPane(btnPanel);
        btnPanel.add(normalBtn);
        btnPanel.add(normalClickedBtn);
        btnPanel.add(itemBtn);
        btnPanel.add(itemClickedBtn);
        btnPanel.add(playBtn);
        btnPanel.add(settingBtn);
        btnPanel.add(exitBtn);
        btnPanel.add(toRecordBtn);
    }
}
