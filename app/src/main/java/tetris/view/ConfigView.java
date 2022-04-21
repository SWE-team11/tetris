package tetris.view;

import tetris.App;
import tetris.model.ConfigModel;
import tetris.model.RecordModel;
import tetris.presenter.ConfigPresenter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    private JTextPane dropKeyPane;
    private JTextPane rotationKeyPane;
    private KeyEvent lastKeyEvent;
    private KeyBindListener keyBindListener;


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

        JButton downBtn = new JButton("-");
        JButton upBtn = new JButton("+");
        JButton smallBtn = new JButton("SM");
        JButton mediumBtn = new JButton("MD");
        JButton largeBtn = new JButton("LG");
        JButton onOffChk = new JButton("Blind Mode");
        JButton setBtn01 = new JButton("Set");
        JButton setBtn02 = new JButton("Set");
        JButton setBtn03 = new JButton("Set");
        JButton setBtn04 = new JButton("Set");
        JButton setBtn05 = new JButton("Set");
        JButton initializeRecordBtn = new JButton("Initialize Record");
        JButton initializeSettingBtn = new JButton("Initialize Setting");
        JButton exit = new JButton(getResource("image/smallExit.png"));

        // Button
        upBtn.setForeground(Color.WHITE);
        downBtn.setForeground(Color.WHITE);
        smallBtn.setForeground(Color.WHITE);
        mediumBtn.setForeground(Color.WHITE);
        largeBtn.setForeground(Color.WHITE);
        onOffChk.setForeground(Color.WHITE);
        setBtn01.setForeground(Color.WHITE);
        setBtn02.setForeground(Color.WHITE);
        setBtn03.setForeground(Color.WHITE);
        setBtn04.setForeground(Color.WHITE);
        setBtn05.setForeground(Color.WHITE);
        initializeRecordBtn.setForeground(Color.WHITE);
        initializeSettingBtn.setForeground(Color.WHITE);

        downBtn.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        downBtn.setContentAreaFilled(false);
        downBtn.setBounds(260, 55, 35, 33);
        downBtn.addActionListener(e -> {
            ConfigModel.changeGameSpeed(Math.max(ConfigModel.gameSpeed - 0.5, 1));
            gameSpeedPane.setText(Double.toString(ConfigModel.gameSpeed));
            setFocusable(true);
            requestFocus();
        });

        upBtn.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        upBtn.setContentAreaFilled(false);
        upBtn.setBounds(302, 55, 35, 33);
        upBtn.addActionListener(e -> {
            System.out.println("up");
            ConfigModel.changeGameSpeed(Math.min(ConfigModel.gameSpeed + 0.5, 3));
            gameSpeedPane.setText(Double.toString(ConfigModel.gameSpeed));
            setFocusable(true);
            requestFocus();
        });

        smallBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.boardSize == ConfigModel.BoardSize.SMALL ? Color.white : Color.gray,2)));
        smallBtn.setForeground(ConfigModel.boardSize == ConfigModel.BoardSize.SMALL ? Color.white : Color.gray);
        smallBtn.setContentAreaFilled(false);
        smallBtn.setBounds(172, 126, 50, 33);
        smallBtn.addActionListener(e -> {
            ConfigModel.changeBoardSize(ConfigModel.BoardSize.SMALL);
            smallBtn.setBorder(new TitledBorder(new LineBorder(Color.white)));
            smallBtn.setForeground(Color.white);
            mediumBtn.setBorder(new TitledBorder(new LineBorder(Color.gray)));
            mediumBtn.setForeground(Color.gray);
            largeBtn.setBorder(new TitledBorder(new LineBorder(Color.gray)));
            largeBtn.setForeground(Color.gray);
            setFocusable(true);
            requestFocus();
        });

        mediumBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.boardSize == ConfigModel.BoardSize.MEDIUM ? Color.white : Color.gray,2)));
        mediumBtn.setForeground(ConfigModel.boardSize == ConfigModel.BoardSize.MEDIUM ? Color.white : Color.gray);
        mediumBtn.setContentAreaFilled(false);
        mediumBtn.setBackground(new Color(0,0,0,ConfigModel.boardSize == ConfigModel.BoardSize.MEDIUM ? 0 : 122));
        mediumBtn.setBounds(230, 126, 50, 33);
        mediumBtn.addActionListener(e -> {
            ConfigModel.changeBoardSize(ConfigModel.BoardSize.MEDIUM);
            smallBtn.setBorder(new TitledBorder(new LineBorder(Color.gray)));
            smallBtn.setForeground(Color.gray);
            mediumBtn.setBorder(new TitledBorder(new LineBorder(Color.white)));
            mediumBtn.setForeground(Color.white);
            largeBtn.setBorder(new TitledBorder(new LineBorder(Color.gray)));
            largeBtn.setForeground(Color.gray);
            setFocusable(true);
            requestFocus();
        });

        largeBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.boardSize == ConfigModel.BoardSize.LARGE ? Color.white : Color.gray,2)));
        largeBtn.setForeground(ConfigModel.boardSize == ConfigModel.BoardSize.LARGE ? Color.white : Color.gray);
        largeBtn.setContentAreaFilled(false);
        largeBtn.setBounds(288, 126, 50, 33);
        largeBtn.addActionListener(e -> {
            ConfigModel.changeBoardSize(ConfigModel.BoardSize.LARGE);
            smallBtn.setBorder(new TitledBorder(new LineBorder(Color.gray)));
            smallBtn.setForeground(Color.gray);
            mediumBtn.setBorder(new TitledBorder(new LineBorder(Color.gray)));
            mediumBtn.setForeground(Color.gray);
            largeBtn.setBorder(new TitledBorder(new LineBorder(Color.white)));
            largeBtn.setForeground(Color.white);
            setFocusable(true);
            requestFocus();
        });

        onOffChk.setBorder(new TitledBorder(new LineBorder(ConfigModel.colorBlindMode ? Color.white : Color.gray,2)));
        onOffChk.setForeground(ConfigModel.colorBlindMode ? Color.white : Color.gray);
        onOffChk.setContentAreaFilled(false);
        onOffChk.setBounds(226, 168, 112, 33);
        onOffChk.addActionListener(e -> {
            ConfigModel.changeColorBlindMode(!ConfigModel.colorBlindMode);
            onOffChk.setBorder(new TitledBorder(new LineBorder(ConfigModel.colorBlindMode ? Color.white : Color.gray,2)));
            onOffChk.setForeground(ConfigModel.colorBlindMode ? Color.white : Color.gray);
            setFocusable(true);
            requestFocus();
        });

        setBtn01.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        setBtn01.setContentAreaFilled(false);
        setBtn01.setBounds(279, 243, 60, 33);
        setBtn01.addActionListener(e -> {
            if(lastKeyEvent == null) return;
            ConfigModel.changeKeyBinding(ConfigModel.PlayerKey.DOWN, lastKeyEvent);
            downKeyPane.setText(KeyEvent.getKeyText(lastKeyEvent.getKeyCode()));
            setFocusable(true);
            requestFocus();
        });

        setBtn02.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        setBtn02.setContentAreaFilled(false);
        setBtn02.setBounds(279, 284, 60, 33);
        setBtn02.addActionListener(e -> {
            if(lastKeyEvent == null) return;
            ConfigModel.changeKeyBinding(ConfigModel.PlayerKey.LEFT, lastKeyEvent);
            leftKeyPane.setText(KeyEvent.getKeyText(lastKeyEvent.getKeyCode()));
            setFocusable(true);
            requestFocus();
        });

        setBtn03.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        setBtn03.setContentAreaFilled(false);
        setBtn03.setBounds(279, 325, 60, 33);
        setBtn03.addActionListener(e -> {
            if(lastKeyEvent == null) return;
            ConfigModel.changeKeyBinding(ConfigModel.PlayerKey.RIGHT, lastKeyEvent);
            rightKeyPane.setText(KeyEvent.getKeyText(lastKeyEvent.getKeyCode()));
            setFocusable(true);
            requestFocus();
        });

        setBtn04.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        setBtn04.setContentAreaFilled(false);
        setBtn04.setBounds(279, 366, 60, 33);
        setBtn04.addActionListener(e -> {
            if(lastKeyEvent == null) return;
            ConfigModel.changeKeyBinding(ConfigModel.PlayerKey.DROP, lastKeyEvent);
            dropKeyPane.setText(KeyEvent.getKeyText(lastKeyEvent.getKeyCode()));
            setFocusable(true);
            requestFocus();
        });

        setBtn05.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        setBtn05.setContentAreaFilled(false);
        setBtn05.setBounds(279, 407, 60, 33);
        setBtn05.addActionListener(e -> {
            if(lastKeyEvent == null) return;
            ConfigModel.changeKeyBinding(ConfigModel.PlayerKey.ROTATE, lastKeyEvent);
            rotationKeyPane.setText(KeyEvent.getKeyText(lastKeyEvent.getKeyCode()));
            setFocusable(true);
            requestFocus();
        });

        initializeRecordBtn.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        initializeRecordBtn.setContentAreaFilled(false);
        initializeRecordBtn.setBounds(45, 488, 130, 33);
        initializeRecordBtn.addActionListener(e -> {
            RecordModel.initRecord();
            setFocusable(true);
            requestFocus();
        });

        initializeSettingBtn.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        initializeSettingBtn.setContentAreaFilled(false);
        initializeSettingBtn.setBounds(208, 488, 130, 33);
        initializeSettingBtn.addActionListener(e -> {
            ConfigModel.initConfig();
            gameSpeedPane.setText(Double.toString(ConfigModel.gameSpeed));
            smallBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.boardSize == ConfigModel.BoardSize.SMALL ? Color.white : Color.gray,2)));
            smallBtn.setForeground(ConfigModel.boardSize == ConfigModel.BoardSize.SMALL ? Color.white : Color.gray);
            mediumBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.boardSize == ConfigModel.BoardSize.MEDIUM ? Color.white : Color.gray,2)));
            mediumBtn.setForeground(ConfigModel.boardSize == ConfigModel.BoardSize.MEDIUM ? Color.white : Color.gray);
            largeBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.boardSize == ConfigModel.BoardSize.LARGE ? Color.white : Color.gray,2)));
            largeBtn.setForeground(ConfigModel.boardSize == ConfigModel.BoardSize.LARGE ? Color.white : Color.gray);
            onOffChk.setBorder(new TitledBorder(new LineBorder(ConfigModel.colorBlindMode ? Color.white : Color.gray,2)));
            onOffChk.setForeground(ConfigModel.colorBlindMode ? Color.white : Color.gray);
            downKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DOWN.ordinal()]));
            leftKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.LEFT.ordinal()]));
            rightKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.RIGHT.ordinal()]));
            dropKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DROP.ordinal()]));
            rotationKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.ROTATE.ordinal()]));
            setFocusable(true);
            requestFocus();
        });

        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setBounds(343, 10, 30, 30);
        exit.addActionListener(e -> {
            App.navigate(App.View.MAIN);
            setFocusable(true);
            requestFocus();
        });

        // pane
        gameSpeedPane = new JTextPane();
        gameSpeedPane.setEditable(false);
        gameSpeedPane.setBackground(Color.black);
        gameSpeedPane.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        gameSpeedPane.setBounds(170,55, 70,35);
        gameSpeedPane.setText(Double.toString(ConfigModel.gameSpeed));

        downKeyPane = new JTextPane();
        downKeyPane.setEditable(false);
        downKeyPane.setBackground(Color.black);
        downKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        downKeyPane.setBounds(144,243, 115,33);
        downKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DOWN.ordinal()]));

        leftKeyPane = new JTextPane();
        leftKeyPane.setEditable(false);
        leftKeyPane.setBackground(Color.black);
        leftKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        leftKeyPane.setBounds(144,284, 115,33);
        leftKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.LEFT.ordinal()]));

        rightKeyPane = new JTextPane();
        rightKeyPane.setEditable(false);
        rightKeyPane.setBackground(Color.black);
        rightKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        rightKeyPane.setBounds(144,325, 115,33);
        rightKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.RIGHT.ordinal()]));

        dropKeyPane = new JTextPane();
        dropKeyPane.setEditable(false);
        dropKeyPane.setBackground(Color.black);
        dropKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        dropKeyPane.setBounds(144,366, 115,33);
        dropKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DROP.ordinal()]));

        rotationKeyPane = new JTextPane();
        rotationKeyPane.setEditable(false);
        rotationKeyPane.setBackground(Color.black);
        rotationKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white,2)));
        rotationKeyPane.setBounds(144,407, 115,33);
        rotationKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.ROTATE.ordinal()]));

        SimpleAttributeSet textPaneStyle = new SimpleAttributeSet();
        StyleConstants.setAlignment(textPaneStyle, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(textPaneStyle, 20);
        StyledDocument doc = gameSpeedPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = downKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = leftKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = rightKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = dropKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = rotationKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        gameSpeedPane.setForeground(Color.WHITE);
        downKeyPane.setForeground(Color.WHITE);
        leftKeyPane.setForeground(Color.WHITE);
        rightKeyPane.setForeground(Color.WHITE);
        dropKeyPane.setForeground(Color.WHITE);
        rotationKeyPane.setForeground(Color.WHITE);

        this.setContentPane(configPanel);
        configPanel.add(upBtn);
        configPanel.add(downBtn);
        configPanel.add(smallBtn);
        configPanel.add(mediumBtn);
        configPanel.add(largeBtn);
        configPanel.add(onOffChk);
        configPanel.add(setBtn01);
        configPanel.add(setBtn02);
        configPanel.add(setBtn03);
        configPanel.add(setBtn04);
        configPanel.add(setBtn05);
        configPanel.add(initializeRecordBtn);
        configPanel.add(initializeSettingBtn);
        configPanel.add(exit);

        configPanel.add(gameSpeedPane);
        configPanel.add(downKeyPane);
        configPanel.add(leftKeyPane);
        configPanel.add(rightKeyPane);
        configPanel.add(dropKeyPane);
        configPanel.add(rotationKeyPane);

        keyBindListener = new KeyBindListener();
        addKeyListener(keyBindListener);
        setFocusable(true);
        requestFocus();
    }

    class KeyBindListener implements KeyListener {
        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {
            lastKeyEvent = e;
            System.out.println(e.toString());
        }

        @Override
        public void keyReleased(final KeyEvent e) {

        }
    }
}
