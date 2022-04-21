package tetris.view;

import tetris.App;
import tetris.presenter.ConfigPresenter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ConfigView extends JFrame {
    private ConfigPresenter configPresenter;

    private ImageIcon getResource(String path) {
        return new ImageIcon(getClass().getClassLoader().getResource(path));
    }

    private Image background = getResource("image/configBackground.png").getImage();
    private JTextPane gameSpeedPane;
    private JTextPane downKeyPane;
    private JTextPane leftKeyPane;
    private JTextPane rightKeyPane;
    private JTextPane descentKeyPane;
    private JTextPane rotationKeyPane;



    public ConfigView(final ConfigPresenter presenter) {

        super("TETRIS");
        configPresenter = presenter;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel configPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(),null);
            }
        };
        configPanel.setLayout(null);

        JButton upBtn = new JButton("UP");
        JButton downBtn = new JButton("Down");
        JButton smallBtn = new JButton("small");
        JButton mediumBtn = new JButton("medium");
        JButton largeBtn = new JButton("large");
        JButton onOffBtn = new JButton("on");
        JButton setBtn01 = new JButton("set");
        JButton setBtn02 = new JButton("set");
        JButton setBtn03 = new JButton("set");
        JButton setBtn04 = new JButton("set");
        JButton setBtn05 = new JButton("set");
        JButton initializeRecordBtn = new JButton("Initialize Record");
        JButton initializeSettingBtn = new JButton("Initialize Setting");

        // Button
        upBtn.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        upBtn.setContentAreaFilled(false);
        upBtn.setBounds(260, 55, 35, 33);

        downBtn.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        downBtn.setContentAreaFilled(false);
        downBtn.setBounds(302, 55, 35, 33);

        smallBtn.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        smallBtn.setContentAreaFilled(false);
        smallBtn.setBounds(172, 126, 50, 33);

        mediumBtn.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        mediumBtn.setContentAreaFilled(false);
        mediumBtn.setBounds(230, 126, 50, 35);

        largeBtn.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        largeBtn.setContentAreaFilled(false);
        largeBtn.setBounds(288, 126, 50, 33);

        onOffBtn.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        onOffBtn.setContentAreaFilled(false);
        onOffBtn.setBounds(226, 168, 60, 33);

        setBtn01.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        setBtn01.setContentAreaFilled(false);
        setBtn01.setBounds(279, 243, 60, 33);

        setBtn02.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        setBtn02.setContentAreaFilled(false);
        setBtn02.setBounds(279, 284, 60, 33);

        setBtn03.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        setBtn03.setContentAreaFilled(false);
        setBtn03.setBounds(279, 325, 60, 33);

        setBtn04.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        setBtn04.setContentAreaFilled(false);
        setBtn04.setBounds(279, 366, 60, 33);

        setBtn05.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        setBtn05.setContentAreaFilled(false);
        setBtn05.setBounds(279, 407, 60, 33);

        initializeRecordBtn.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        initializeRecordBtn.setContentAreaFilled(false);
        initializeRecordBtn.setBounds(45, 488, 130, 33);

        initializeSettingBtn.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        initializeSettingBtn.setContentAreaFilled(false);
        initializeSettingBtn.setBounds(208, 488, 130, 33);

        // pane
        gameSpeedPane = new JTextPane();
        gameSpeedPane.setEditable(false);
        gameSpeedPane.setBackground(Color.black);
        gameSpeedPane.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        gameSpeedPane.setBounds(170,55, 70,35);

        downKeyPane = new JTextPane();
        downKeyPane.setEditable(false);
        downKeyPane.setBackground(Color.black);
        downKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        downKeyPane.setBounds(144,243, 115,33);

        leftKeyPane = new JTextPane();
        leftKeyPane.setEditable(false);
        leftKeyPane.setBackground(Color.black);
        leftKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        leftKeyPane.setBounds(144,284, 115,33);

        rightKeyPane = new JTextPane();
        rightKeyPane.setEditable(false);
        rightKeyPane.setBackground(Color.black);
        rightKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        rightKeyPane.setBounds(144,325, 115,33);

        descentKeyPane = new JTextPane();
        descentKeyPane.setEditable(false);
        descentKeyPane.setBackground(Color.black);
        descentKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        descentKeyPane.setBounds(144,366, 115,33);

        rotationKeyPane = new JTextPane();
        rotationKeyPane.setEditable(false);
        rotationKeyPane.setBackground(Color.black);
        rotationKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        rotationKeyPane.setBounds(144,407, 115,33);


        // add
        this.setContentPane(configPanel);
        configPanel.add(upBtn);
        configPanel.add(downBtn);
        configPanel.add(smallBtn);
        configPanel.add(mediumBtn);
        configPanel.add(largeBtn);
        configPanel.add(onOffBtn);
        configPanel.add(setBtn01);
        configPanel.add(setBtn02);
        configPanel.add(setBtn03);
        configPanel.add(setBtn04);
        configPanel.add(setBtn05);
        configPanel.add(initializeRecordBtn);
        configPanel.add(initializeSettingBtn);

        configPanel.add(gameSpeedPane);
        configPanel.add(downKeyPane);
        configPanel.add(leftKeyPane);
        configPanel.add(rightKeyPane);
        configPanel.add(descentKeyPane);
        configPanel.add(rotationKeyPane);
    }

}
