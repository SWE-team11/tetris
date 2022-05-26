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


        // JButton playBtn = new JButton(getResource("image/play.png"));
        // solo
        JButton playBtn1 = new JButton();
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

        JButton settingBtn = new JButton();
        JButton exitBtn1 = new JButton();
        JButton toRecordBtn = new JButton();


        // duo
        JButton playBtn2 = new JButton();
        JButton itemBtn2 = new JButton(getResource("image/battleItemOption.png"));
        JButton itemClickedBtn2 = new JButton(getResource("image/battleItemOptionClicked.png"));
        JButton timeBtn2 = new JButton(getResource("image/battleTimeOption.png"));
        JButton timeClickedBtn2 = new JButton(getResource("image/battleTimeOptionClicked.png"));
        JButton normalBtn2 = new JButton(getResource("image/battleNormalOption.png"));
        JButton normalClickedBtn2 = new JButton(getResource("image/battleNormalOptionClicked.png"));
        JButton exitBtn2 = new JButton();



        JButton xBtn = new JButton();
        xBtn.setBorderPainted(false);
        xBtn.setContentAreaFilled(false);
        xBtn.addActionListener(e -> {
            if (dialogOpen)
                return;
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        xBtn.setBounds(302, 480, 45, 45);


        // solo
        playBtn1.setBorderPainted(false);
        playBtn1.setContentAreaFilled(false);
        playBtn1.addActionListener(e -> App.navigate(App.View.GAME));
        playBtn1.setBounds(38, 190, 310, 90);

        normalBtn.setBorderPainted(false);
        normalBtn.setContentAreaFilled(false);
        normalBtn.setBounds(205, 118, 150, 60);
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
        normalClickedBtn.setBounds(205, 118, 150, 60);
        normalClickedBtn.setVisible(ConfigModel.gameMode == ConfigModel.GameMode.BASIC ? true : false);

        itemBtn.setBorderPainted(false);
        itemBtn.setContentAreaFilled(false);
        itemBtn.setBounds(38, 118, 150, 60);
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
        itemClickedBtn.setBounds(38, 118, 150, 60);
        itemClickedBtn.setVisible(ConfigModel.gameMode == ConfigModel.GameMode.BASIC ? false : true);

        easyLevelBtn.setBorderPainted(false);
        easyLevelBtn.setContentAreaFilled(false);
        easyLevelBtn.setBounds(38, 310, 98, 48);
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
        easyLevelClickedBtn.setBounds(38, 310, 98, 48);
        easyLevelClickedBtn.setVisible(ConfigModel.gameDifficulty == ConfigModel.GameDifficulty.EASY);


        normalLevelBtn.setBorderPainted(false);
        normalLevelBtn.setContentAreaFilled(false);
        normalLevelBtn.setBounds(148, 310, 98, 48);
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
        normalLevelClickedBtn.setBounds(148, 310, 98, 48);
        normalLevelClickedBtn.setVisible(ConfigModel.gameDifficulty == ConfigModel.GameDifficulty.NORMAL);

        hardLevelBtn.setBorderPainted(false);
        hardLevelBtn.setContentAreaFilled(false);
        hardLevelBtn.setBounds(258, 310, 98, 48);
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
        hardLevelClickedBtn.setBounds(258, 310, 98, 48);
        hardLevelClickedBtn.setVisible(ConfigModel.gameDifficulty == ConfigModel.GameDifficulty.HARD ? true : false);

        settingBtn.setBorderPainted(false);
        settingBtn.setContentAreaFilled(false);
        settingBtn.setBounds(263, 430, 45, 45);
        settingBtn.addActionListener(e -> {
            App.navigate(App.View.CONFIG);
        });

        exitBtn1.setBorderPainted(false);
        exitBtn1.setContentAreaFilled(false);
        exitBtn1.setBounds(315, 430, 46, 46);
        exitBtn1.addActionListener(e -> {
            normalGameDialog.setVisible(false);
            battleGameDialog.setVisible(false);
            dialogOpen = false;
        });

        toRecordBtn.setBorderPainted(false);
        toRecordBtn.setContentAreaFilled(false);
        toRecordBtn.setBounds(40, 430, 85, 45);
        toRecordBtn.addActionListener(e -> {
            App.navigate(App.View.RECORD);
        });


        // duo
        playBtn2.setBorderPainted(false);
        playBtn2.addActionListener(e -> App.navigate(App.View.BATTLE));
        playBtn2.setBounds(45, 265, 310, 90);
        playBtn2.setOpaque(false);


        itemBtn2.setBorderPainted(false);
        itemBtn2.setContentAreaFilled(false);
        itemBtn2.setBounds(38, 170, 98, 48);
        itemBtn2.setVisible(ConfigModel.gameBattleMode != ConfigModel.GameBattleMode.ITEM);

        itemBtn2.addActionListener(e -> {
            ConfigModel.changeBattleMode(ConfigModel.GameBattleMode.ITEM);
            itemBtn2.setVisible(false);
            itemClickedBtn2.setVisible(true);
            normalBtn2.setVisible(true);
            normalClickedBtn2.setVisible(false);
            timeBtn2.setVisible(true);
            timeClickedBtn2.setVisible(false);
            repaint();
        });

        itemClickedBtn2.setBorderPainted(false);
        itemClickedBtn2.setContentAreaFilled(false);
        itemClickedBtn2.setBounds(38, 170, 98, 48);
        itemClickedBtn2.setVisible(ConfigModel.gameBattleMode == ConfigModel.GameBattleMode.ITEM);


        normalBtn2.setBorderPainted(false);
        normalBtn2.setContentAreaFilled(false);
        normalBtn2.setBounds(145, 170, 98, 48);
        normalBtn2.setVisible(ConfigModel.gameBattleMode != ConfigModel.GameBattleMode.NORMAL);

        normalBtn2.addActionListener(e -> {
            ConfigModel.changeBattleMode(ConfigModel.GameBattleMode.NORMAL);
            itemBtn2.setVisible(true);
            itemClickedBtn2.setVisible(false);
            normalBtn2.setVisible(false);
            normalClickedBtn2.setVisible(true);
            timeBtn2.setVisible(true);
            timeClickedBtn2.setVisible(false);
            repaint();
        });

        normalClickedBtn2.setBorderPainted(false);
        normalClickedBtn2.setContentAreaFilled(false);
        normalClickedBtn2.setBounds(145, 170, 98, 48);
        normalClickedBtn2.setVisible(ConfigModel.gameBattleMode == ConfigModel.GameBattleMode.NORMAL);


        timeBtn2.setBorderPainted(false);
        timeBtn2.setContentAreaFilled(false);
        timeBtn2.setBounds(252, 170, 98, 48);
        timeBtn2.setVisible(ConfigModel.gameBattleMode != ConfigModel.GameBattleMode.TIME);

        timeBtn2.addActionListener(e -> {
            ConfigModel.changeBattleMode(ConfigModel.GameBattleMode.TIME);
            itemBtn2.setVisible(true);
            itemClickedBtn2.setVisible(false);
            normalBtn2.setVisible(true);
            normalClickedBtn2.setVisible(false);
            timeBtn2.setVisible(false);
            timeClickedBtn2.setVisible(true);
            repaint();
        });

        timeClickedBtn2.setBorderPainted(false);
        timeClickedBtn2.setContentAreaFilled(false);
        timeClickedBtn2.setBounds(252, 170, 98, 48);
        timeClickedBtn2.setVisible(ConfigModel.gameBattleMode == ConfigModel.GameBattleMode.TIME);

        exitBtn2.setBorderPainted(false);
        exitBtn2.setContentAreaFilled(false);
        exitBtn2.setBounds(302, 355, 45, 45);
        exitBtn2.addActionListener(e -> {
            normalGameDialog.setVisible(false);
            battleGameDialog.setVisible(false);
            dialogOpen = false;
        });



        normalGameDialog.setOpaque(false);
        normalGameDialog.setVisible(false);
        normalGameDialog.setLayout(null);
        normalGameDialog.setBounds(0, 0, 400, 600);

        //버튼 추가하기
        normalGameDialog.add(normalBtn);
        normalGameDialog.add(normalClickedBtn);
        normalGameDialog.add(itemBtn);
        normalGameDialog.add(itemClickedBtn);
        normalGameDialog.add(playBtn1);
        normalGameDialog.add(easyLevelBtn);
        normalGameDialog.add(easyLevelClickedBtn);
        normalGameDialog.add(normalLevelBtn);
        normalGameDialog.add(normalLevelClickedBtn);
        normalGameDialog.add(hardLevelBtn);
        normalGameDialog.add(hardLevelClickedBtn);
        normalGameDialog.add(settingBtn);
        normalGameDialog.add(exitBtn1);
        normalGameDialog.add(toRecordBtn);


        battleGameDialog.setOpaque(false);
        battleGameDialog.setVisible(false);
        battleGameDialog.setLayout(null);
        battleGameDialog.setBounds(0, 0, 400, 600);

        battleGameDialog.add(playBtn2);
        battleGameDialog.add(itemBtn2);
        battleGameDialog.add(itemClickedBtn2);
        battleGameDialog.add(normalBtn2);
        battleGameDialog.add(normalClickedBtn2);
        battleGameDialog.add(timeBtn2);
        battleGameDialog.add(timeClickedBtn2);
        battleGameDialog.add(exitBtn2);

        this.setContentPane(btnPanel);
        btnPanel.add(normalGameDialog);
        btnPanel.add(battleGameDialog);
        btnPanel.add(soloBtn);
        btnPanel.add(duoBtn);
        btnPanel.add(xBtn);
    }
}



        