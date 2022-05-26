package tetris.view;

import tetris.App;
import tetris.model.ConfigModel;
import tetris.presenter.GamePresenter;
import tetris.utils.Block;
import tetris.utils.BoardElement;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;

public class BattleModeView extends JFrame {

    private ImageIcon getResource(String path) {
        return new ImageIcon(getClass().getClassLoader().getResource(path));
    }

    private Image background = getResource("image/battleModeBackground.png").getImage();
    private Image nextImg = getResource("image/next.png").getImage();
    private Image pausePanelImg = getResource("image/pausePanel.png").getImage();
    private Image scorePanelImg = getResource("image/scoreDialog.png").getImage();
    private Image statusBarImg = getResource("image/statusBar.png").getImage();
    private Image timeImg = getResource("image/time.png").getImage();

    static final int LINE_BORDER_OUTER_WEIGHT = 10;
    static final int LINE_BORDER_INNER_WEIGHT = 5;
    private final int VIEW_WIDTH = 900;
    private final int VIEW_HEIGHT = 600;
    static final int FONT_SIZE = 28;
    static final float LINE_SPACING = -0.45f;
    private static final long serialVersionUID = 2434035659171694595L;

    private JTextPane boardPane;
    private JTextPane nextBlockPane;
    private JTextPane scorePane;
    private JTextPane deletedRawPane;
    private JTextPane recordScorePane;
    private JTextPane boardPane01;
    private JTextPane nextBlockPane01;
    private JTextPane scorePane01;
    private JTextPane deletedRawPane01;
    private JTextPane recordScorePane01;
    private JTextPane timerPane;
    private JTextField namePane;
    private SimpleAttributeSet styleSet;
    private JPanel pauseDialog = new JPanel(){
        public void paintComponent(Graphics g) {
            g.drawImage(pausePanelImg, 0, 0, 200, 100,null);
        }
    };
    private JPanel scoreDialog = new JPanel(){
        public void paintComponent(Graphics g) {
            g.drawImage(scorePanelImg, 0, 0, 280, 360,null);
        }
    };

    private int getWidth(ConfigModel.BoardSize boardSize) {
        return switch (boardSize) {
            case SMALL -> -20;
            case MEDIUM -> 0;
            case LARGE -> 60;
        };
    }

    private GamePresenter gamePresenter;
    private PlayerKeyListener playerKeyListener;
    private PauseKeyListener pauseKeyListener;

    public BattleModeView(final GamePresenter presenter) {
        super("TETRIS");
        setSize(VIEW_WIDTH + getWidth(ConfigModel.boardSize), VIEW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.gamePresenter = presenter;

        JPanel backgroundPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(200 , 0 , 0 , 25));

        JPanel statusBarPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(statusBarImg, 0, 0, 95,302,null);
            }
        };
        statusBarPanel.setLayout(null);
        statusBarPanel.setBounds(290 + getWidth(ConfigModel.boardSize),192,95,302);

        JPanel statusBarPanelP2 = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(statusBarImg, 0, 0, 95,302,null);
            }
        };
        statusBarPanelP2.setLayout(null);
        statusBarPanelP2.setBounds(515 + getWidth(ConfigModel.boardSize),192,95,302);


        JPanel nextPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(nextImg, 0, 0, 70,120,null);
            }
        };
        nextPanel.setLayout(null);
        nextPanel.setBounds(290 + getWidth(ConfigModel.boardSize),54,70,120);

        JPanel nextPanel01 = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(nextImg, 0, 0, 70,120,null);
            }
        };
        nextPanel01.setLayout(null);
        nextPanel01.setBounds(540 + getWidth(ConfigModel.boardSize),54,70,120);

        JPanel timePanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(timeImg, 0, 0, 95,74,null);
            }
        };
        timePanel.setLayout(null);
        timePanel.setBounds(403 + getWidth(ConfigModel.boardSize),54,95,74);

        boardPane = new JTextPane();
        boardPane.setEditable(false);
        boardPane.setBackground(Color.black);
        boardPane.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
        boardPane.setBounds(40,55, 220 + getWidth(ConfigModel.boardSize),440);

        boardPane01 = new JTextPane();
        boardPane01.setEditable(false);
        boardPane01.setBackground(Color.black);
        boardPane01.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
        boardPane01.setBounds(640,55, 220 + getWidth(ConfigModel.boardSize),440);

        nextBlockPane = new JTextPane();
        nextBlockPane.setEditable(false);
        nextBlockPane.setOpaque(false);
        nextBlockPane.setBounds(290 + getWidth(ConfigModel.boardSize) , 80, 70, 85);

        nextBlockPane01 = new JTextPane();
        nextBlockPane01.setEditable(false);
        nextBlockPane01.setOpaque(false);
        nextBlockPane01.setBounds(450 + getWidth(ConfigModel.boardSize) , 80, 70, 85);

        scorePane = new JTextPane();
        scorePane.setEditable(false);
        scorePane.setOpaque(false);
        scorePane.setBounds(310 + getWidth(ConfigModel.boardSize), 222, 70, 70);

        scorePane01 = new JTextPane();
        scorePane01.setEditable(false);
        scorePane01.setOpaque(false);
        scorePane01.setBounds(455 + getWidth(ConfigModel.boardSize), 290, 70, 70);

        deletedRawPane = new JTextPane();
        deletedRawPane.setEditable(false);
        deletedRawPane.setOpaque(false);
        deletedRawPane.setBounds(310 + getWidth(ConfigModel.boardSize), 450, 70, 70);

        deletedRawPane01 = new JTextPane();
        deletedRawPane01.setEditable(false);
        deletedRawPane01.setOpaque(false);
        deletedRawPane01.setBounds(455 + getWidth(ConfigModel.boardSize), 450, 70, 70);

        timerPane = new JTextPane();
        timerPane.setEditable(false);
        timerPane.setOpaque(false);
        timerPane.setBounds(403 + getWidth(ConfigModel.boardSize) , 80, 95, 74);

        pauseDialog.setBounds(100 + getWidth(ConfigModel.boardSize)/2, 200, 200, 100);
        pauseDialog.setLayout(null);
        pauseDialog.setVisible(false);
        pauseDialog.setOpaque(false);

        JButton continueBtn = new JButton(getResource("image/continue.png"));
        continueBtn.setBounds(10,30,80,40);
        continueBtn.setBorderPainted(false);
        continueBtn.setContentAreaFilled(false);

        JButton exitBtn = new JButton(getResource("image/toMain.png"));
        exitBtn.setBounds(110,30,80,40);
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);

        continueBtn.addActionListener(e -> gamePresenter.gameStart());
        exitBtn.addActionListener(e -> App.navigate(App.View.MAIN));

        // Score Dialog
        scoreDialog.setBounds(60 + getWidth(ConfigModel.boardSize)/2, 50, 280, 360);
        scoreDialog.setLayout(null);
        scoreDialog.setVisible(false);
        scoreDialog.setOpaque(false);

        recordScorePane = new JTextPane();
        recordScorePane.setEditable(false);
        recordScorePane.setOpaque(false);
        recordScorePane.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
        recordScorePane.setBounds(25,100, 230,85);

        namePane = new JTextField();
        namePane.setOpaque(false);
        namePane.setForeground(Color.white);
        namePane.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
        namePane.setBounds(115,205, 140  ,50);

        JButton enterBtn = new JButton(getResource("image/enter.png"));
        enterBtn.setBorderPainted(false);
        enterBtn.setContentAreaFilled(false);
        enterBtn.setBounds(70, 275, 140, 50);
        enterBtn.addActionListener(e -> {
            presenter.recordGame(namePane.getText());
            App.navigate(App.View.MAIN);
        });

        backgroundPanel.add(scoreDialog);
        this.getContentPane().add(backgroundPanel);
        backgroundPanel.add(pauseDialog);
        pauseDialog.add(continueBtn);
        pauseDialog.add(exitBtn);
        backgroundPanel.add(boardPane);
        backgroundPanel.add(boardPane01);
        backgroundPanel.add(scorePane);
        backgroundPanel.add(scorePane01);
        backgroundPanel.add(deletedRawPane);
        backgroundPanel.add(deletedRawPane01);
        backgroundPanel.add(nextBlockPane);
        backgroundPanel.add(nextBlockPane01);
        backgroundPanel.add(timerPane);

        backgroundPanel.add(statusBarPanel);
        backgroundPanel.add(statusBarPanelP2);
        backgroundPanel.add(nextPanel);
        backgroundPanel.add(nextPanel01);
        backgroundPanel.add(timePanel);


        scoreDialog.add(recordScorePane);
        scoreDialog.add(namePane);
        scoreDialog.add(enterBtn);

        styleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(styleSet, FONT_SIZE);
        StyleConstants.setLineSpacing(styleSet, LINE_SPACING);
        StyleConstants.setFontFamily(styleSet, "Courier New");
        StyleConstants.setBold(styleSet, true);
        StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);

        boardPane.setFocusable(true);
        boardPane.requestFocus();
        boardPane.requestFocusInWindow();

        this.playerKeyListener = new PlayerKeyListener();
        this.pauseKeyListener = new PauseKeyListener();
    }

    class PlayerKeyListener implements KeyListener {
        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {
            switch (ConfigModel.getPlayerKey(e)) {
                case DOWN -> gamePresenter.moveDown();
                case RIGHT -> gamePresenter.moveRight();
                case LEFT -> gamePresenter.moveLeft();
                case ROTATE -> gamePresenter.moveRotate();
                case DROP -> gamePresenter.moveStraightDown();
                case ESC -> gamePresenter.gameStop();
            }
        }

        @Override
        public void keyReleased(final KeyEvent e) {

        }
    }

    class PauseKeyListener implements KeyListener {
        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {
            switch (ConfigModel.getPlayerKey(e)) {
                case ESC -> gamePresenter.gameStart();
            }
        }

        @Override
        public void keyReleased(final KeyEvent e) {

        }
    }

    public void startPlayerKeyListen() {
        boardPane.addKeyListener(this.playerKeyListener);
    }

    public void stopPlayerKeyListen() {
        boardPane.removeKeyListener(this.playerKeyListener);
    }

    public void startPauseKeyListen() {
        boardPane.addKeyListener(this.pauseKeyListener);
    }

    public void stopPauseKeyListen() {
        boardPane.removeKeyListener(this.pauseKeyListener);
    }

    public void setVisiblePauseDialog(boolean ifVisible) {
        pauseDialog.setVisible(ifVisible);
    }

    public void setVisibleScoreDialog(boolean ifVisible) {
        scoreDialog.setVisible(ifVisible);
    }

    public final void drawBoard(final ArrayList<BoardElement[]> board) {
        boardPane.setText("");
        Style style = boardPane.addStyle("textStyle", null);
        StyledDocument doc = boardPane.getStyledDocument();

        try {
            for (int i = 0; i < board.size() + 2; i++) {
                for (int j = 0; j < board.get(0).length + 2; j++) {
                    boolean isBorder = i == 0 || i == board.size() + 1 || j == 0 || j == board.get(0).length + 1;
                    if (isBorder) {
                        StyleConstants.setForeground(style, BoardElement.getElementColor(BoardElement.BORDER));
                        doc.insertString(doc.getLength(), BoardElement.getElementText(BoardElement.BORDER), style);
                    } else {
                        StyleConstants.setForeground(style, BoardElement.getElementColor(board.get(i - 1)[j - 1]));
                        doc.insertString(doc.getLength(), BoardElement.getElementText(board.get(i - 1)[j - 1]), style);
                    }
                }
                doc.insertString(doc.getLength(), "\n", style);
            }
        } catch (BadLocationException e) {
        }

        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        boardPane.setStyledDocument(doc);
    }

    public final void drawNextBlock(Block nextBlock) {
        nextBlockPane.setText("");
        Style style = nextBlockPane.addStyle("textStyle", null);
        StyledDocument doc = nextBlockPane.getStyledDocument();

        try {
            for (int i = 0; i < nextBlock.width(); i++) {
                for (int j = 0; j < nextBlock.height(); j++) {
                    BoardElement currentElement = nextBlock.getShape(i, j);
                    StyleConstants.setForeground(style, BoardElement.getElementColor(currentElement));
                    doc.insertString(doc.getLength(), BoardElement.getElementText(currentElement), style);
                }
                doc.insertString(doc.getLength(), "\n", style);
            }
        } catch (BadLocationException e) {

        }

        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        nextBlockPane.setStyledDocument(doc);
    }

    public final void drawScore(double score) {
        scorePane.setText("");
        Style style = scorePane.addStyle("textStyle", null);
        StyledDocument doc = scorePane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StyleConstants.setForeground(style, Color.WHITE);
        StyleConstants.setFontSize(style, 24);
        try {
            doc.insertString(doc.getLength(), Integer.toString((int) score), style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        scorePane.setStyledDocument(doc);
    }

    public final void drawLevel() {
    }

    public final void drawDeletedRaw(int deletedRaw) {
        deletedRawPane.setText("");
        Style style = deletedRawPane.addStyle("textStyle", null);
        StyledDocument doc = deletedRawPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StyleConstants.setForeground(style, Color.WHITE);
        StyleConstants.setFontSize(style, 24);
        try {
            doc.insertString(doc.getLength(), Integer.toString(deletedRaw), style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        deletedRawPane.setStyledDocument(doc);
    }

    public final void drawScrollDialog(int score) {
        recordScorePane.setText("");
        Style style = recordScorePane.addStyle("textStyle", null);
        StyledDocument doc = recordScorePane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StyleConstants.setForeground(style, Color.WHITE);
        StyleConstants.setFontSize(style, 40);
        try {
            doc.insertString(doc.getLength(), Integer.toString((int) score), style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        recordScorePane.setStyledDocument(doc);
    }
}
