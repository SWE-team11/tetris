package tetris.view;

import tetris.App;
import tetris.model.ConfigModel;
import tetris.presenter.MainPresenter;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MainView extends JFrame {

    private MainPresenter mainPresenter;
    private boolean dialogOpen = false;

    private ImageIcon getResource(String path) {
        return new ImageIcon(getClass().getClassLoader().getResource(path));
    }

    private Image background = getResource("image/background.png").getImage();
    private Image normalGameDialogBG = getResource("image/normalGameDialog.png").getImage();
    private Image battleGameDialogBG = getResource("image/battleGameDialog.png").getImage();

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

        JPanel normalGameDialog = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(normalGameDialogBG, 0, 0, 400, 600, null);
            }
        };
        JPanel battleGameDialog = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(battleGameDialogBG, 0, 0, 400, 600, null);
            }
        };

        JButton soloBtn = new JButton();
        soloBtn.setBorderPainted(false);
        soloBtn.setContentAreaFilled(false);
        soloBtn.addActionListener(e -> {
            if(dialogOpen) return;
            normalGameDialog.setVisible(true);
            dialogOpen = true;
        });
        soloBtn.setBounds(50, 245, 290, 80);

        JButton duoBtn = new JButton();
        duoBtn.setBorderPainted(false);
        duoBtn.setContentAreaFilled(false);
        duoBtn.addActionListener(e -> {
            if(dialogOpen) return;
            battleGameDialog.setVisible(true);
            dialogOpen = true;
        });
        duoBtn.setBounds(50, 340, 290, 80);

        JButton playBtn = new JButton(getResource("image/play.png"));
        JButton normalBtn = new JButton(getResource("image/normal.png"));
        JButton normalClickedBtn = new JButton(getResource("image/normal_clicked.png"));
        JButton itemBtn = new JButton(getResource("image/item.png"));
        JButton itemClickedBtn = new JButton(getResource("image/item_clicked.png"));
        JButton easyLevelBtn = new JButton(getResource("image/easyLevel.png"));
        JButton easyLevelClickedBtn = new JButton(getResource("image/easyLevelClicked.png"));
        JButton normalLevelBtn = new JButton(getResource("image/normalLevel.png"));
        JButton normalLevelClickedBtn = new JButton(getResource("image/normalLevelClicked.png"));
        JButton hardLevelBtn = new JButton(getResource("image/hardLevel.png"));
        JButton hardLevelClickedBtn = new JButton(getResource("image/hardLevelClicked.png"));

        JButton settingBtn = new JButton(getResource("image/setting.png"));
        JButton exitBtn = new JButton(getResource("image/exit.png"));
        JButton toRecordBtn = new JButton(getResource("image/toRecord.png"));

        playBtn.setBorderPainted(false);
        playBtn.setContentAreaFilled(false);
        playBtn.addActionListener(e -> App.navigate(App.View.BATTLE));
        playBtn.setBounds(30, 285, 320, 90);

        normalBtn.setBorderPainted(false);
        normalBtn.setContentAreaFilled(false);
        normalBtn.setBounds(30, 205, 150, 60);
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
        normalClickedBtn.setBounds(30, 205, 150, 60);
        normalClickedBtn.setVisible(ConfigModel.gameMode == ConfigModel.GameMode.BASIC ? true : false);

        itemBtn.setBorderPainted(false);
        itemBtn.setContentAreaFilled(false);
        itemBtn.setBounds(200, 205, 150, 60);
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
        itemClickedBtn.setBounds(200, 205, 150, 60);
        itemClickedBtn.setVisible(ConfigModel.gameMode == ConfigModel.GameMode.BASIC ? false : true);

        easyLevelBtn.setBorderPainted(false);
        easyLevelBtn.setContentAreaFilled(false);
        easyLevelBtn.setBounds(30, 395, 98, 48);
        easyLevelBtn.setVisible(ConfigModel.gameDifficulty != ConfigModel.GameDifficulty.EASY);

        easyLevelBtn.addActionListener(e -> {
            ConfigModel.changeGameDifficulty(ConfigModel.GameDifficulty.EASY);
            easyLevelBtn.setVisible(false);
            easyLevelClickedBtn.setVisible(true);
            normalLevelBtn.setVisible(true);
            normalLevelClickedBtn.setVisible(false);
            hardLevelBtn.setVisible(true);
            hardLevelClickedBtn.setVisible(false);
            repaint();
        });

        easyLevelClickedBtn.setBorderPainted(false);
        easyLevelClickedBtn.setContentAreaFilled(false);
        easyLevelClickedBtn.setBounds(30, 395, 98, 48);
        easyLevelClickedBtn.setVisible(ConfigModel.gameDifficulty == ConfigModel.GameDifficulty.EASY);


        normalLevelBtn.setBorderPainted(false);
        normalLevelBtn.setContentAreaFilled(false);
        normalLevelBtn.setBounds(140, 395, 98, 48);
        normalLevelBtn.setVisible(ConfigModel.gameDifficulty != ConfigModel.GameDifficulty.NORMAL);

        normalLevelBtn.addActionListener(e -> {
            ConfigModel.changeGameDifficulty(ConfigModel.GameDifficulty.NORMAL);
            easyLevelBtn.setVisible(true);
            easyLevelClickedBtn.setVisible(false);
            normalLevelBtn.setVisible(false);
            normalLevelClickedBtn.setVisible(true);
            hardLevelBtn.setVisible(true);
            hardLevelClickedBtn.setVisible(false);
            repaint();
        });

        normalLevelClickedBtn.setBorderPainted(false);
        normalLevelClickedBtn.setContentAreaFilled(false);
        normalLevelClickedBtn.setBounds(140, 395, 98, 48);
        normalLevelClickedBtn.setVisible(ConfigModel.gameDifficulty == ConfigModel.GameDifficulty.NORMAL);

        hardLevelBtn.setBorderPainted(false);
        hardLevelBtn.setContentAreaFilled(false);
        hardLevelBtn.setBounds(252, 395, 98, 48);
        hardLevelBtn.setVisible(ConfigModel.gameDifficulty != ConfigModel.GameDifficulty.HARD);

        hardLevelBtn.addActionListener(e -> {
            ConfigModel.changeGameDifficulty(ConfigModel.GameDifficulty.HARD);
            easyLevelBtn.setVisible(true);
            easyLevelClickedBtn.setVisible(false);
            normalLevelBtn.setVisible(true);
            normalLevelClickedBtn.setVisible(false);
            hardLevelBtn.setVisible(false);
            hardLevelClickedBtn.setVisible(true);
            repaint();
        });

        hardLevelClickedBtn.setBorderPainted(false);
        hardLevelClickedBtn.setContentAreaFilled(false);
        hardLevelClickedBtn.setBounds(252, 395, 98, 48);
        hardLevelClickedBtn.setVisible(ConfigModel.gameDifficulty == ConfigModel.GameDifficulty.HARD ? true : false);

        settingBtn.setBorderPainted(false);
        settingBtn.setContentAreaFilled(false);
        settingBtn.setBounds(250, 485, 46, 46);
        settingBtn.addActionListener(e -> {
            App.navigate(App.View.CONFIG);
        });

        JButton xBtn = new JButton();
        xBtn.setBorderPainted(false);
        xBtn.setContentAreaFilled(false);
        xBtn.addActionListener(e -> {
            if(dialogOpen) return;
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        xBtn.setBounds(302, 480, 45, 45);

        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setBounds(305, 485, 46, 46);
        exitBtn.addActionListener(e -> {
            normalGameDialog.setVisible(false);
            battleGameDialog.setVisible(false);
            dialogOpen = false;
        });

        toRecordBtn.setBorderPainted(false);
        toRecordBtn.setContentAreaFilled(false);
        toRecordBtn.setBounds(30, 485, 85, 45);
        toRecordBtn.addActionListener(e -> {
            App.navigate(App.View.RECORD);
        });

        normalGameDialog.setOpaque(false);
        normalGameDialog.setVisible(false);
        normalGameDialog.setLayout(null);
        normalGameDialog.setBounds(0, 0, 400, 600);

        //버튼 추가하기
        normalGameDialog.add(settingBtn);
        normalGameDialog.add(exitBtn);
        normalGameDialog.add(toRecordBtn);

        battleGameDialog.setOpaque(false);
        battleGameDialog.setVisible(false);
        battleGameDialog.setLayout(null);
        battleGameDialog.setBounds(0, 0, 400, 600);

        battleGameDialog.add(normalBtn);
        battleGameDialog.add(normalClickedBtn);
        battleGameDialog.add(itemBtn);
        battleGameDialog.add(itemClickedBtn);
        battleGameDialog.add(playBtn);
        battleGameDialog.add(easyLevelBtn);
        battleGameDialog.add(easyLevelClickedBtn);
        battleGameDialog.add(normalLevelBtn);
        battleGameDialog.add(normalLevelClickedBtn);
        battleGameDialog.add(hardLevelBtn);
        battleGameDialog.add(hardLevelClickedBtn);
        battleGameDialog.add(exitBtn);

        this.setContentPane(btnPanel);
        btnPanel.add(normalGameDialog);
        btnPanel.add(battleGameDialog);
        btnPanel.add(soloBtn);
        btnPanel.add(duoBtn);
        btnPanel.add(xBtn);
    }
}
