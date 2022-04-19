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

    private Image background = new ImageIcon(getClass().getClassLoader().getResource("image/background.png")).getImage();

    public MainView(final MainPresenter presenter) {
        this.mainPresenter = presenter;
        
        setTitle("TETRIS");
        setSize(400, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel btnPanel = new JPanel();
        JButton playBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/play.png")));
        JButton normalBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/normal.png")));
        JButton normal_clickedBtn = new JButton(
                new ImageIcon(getClass().getClassLoader().getResource("image/normal_clicked.png")));
        JButton itemBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/item.png")));
        JButton item_clickedBtn = new JButton(
                new ImageIcon(getClass().getClassLoader().getResource("image/item_clicked.png")));
        JButton settingBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/setting.png")));
        JButton exitBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/exit.png")));

        playBtn.setBounds(30, 300, 320, 90);
        playBtn.setBorderPainted(false);
        playBtn.setContentAreaFilled(false);
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                App.navigate(App.View.Game);
            }
        });

        normal_clickedBtn.setBounds(30, 215, 150, 60);
        normal_clickedBtn.setBorderPainted(false);
        normal_clickedBtn.setContentAreaFilled(false);

        itemBtn.setBounds(200, 215, 150, 60);
        itemBtn.setBorderPainted(false);
        itemBtn.setContentAreaFilled(false);

        settingBtn.setBounds(250, 485, 46, 46);
        settingBtn.setBorderPainted(false);
        settingBtn.setContentAreaFilled(false);

        exitBtn.setBounds(305, 485, 46, 46);
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);

        btnPanel.setBounds(0, 200, 400, 600);
        btnPanel.setBackground(new Color(0, 0, 0, 255));

        add(btnPanel);
        btnPanel.add(normal_clickedBtn);
        btnPanel.add(itemBtn);
        btnPanel.add(playBtn);
        btnPanel.add(settingBtn);
        btnPanel.add(exitBtn);
    }

    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }
}
