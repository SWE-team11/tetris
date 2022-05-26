package tetris.view;

import tetris.App;
import tetris.model.ConfigModel;
import tetris.presenter.BattlePresenter;
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

public class BattleView extends JFrame {

    private ImageIcon getResource(String path) {
        return new ImageIcon(getClass().getClassLoader().getResource(path));
    }

    private Image background = getResource("image/battleModeBackground.png").getImage();
    private Image nextImg = getResource("image/next.png").getImage();
    private Image scoreImg = getResource("image/score.png").getImage();
    private Image linesImg = getResource("image/lines.png").getImage();
    private Image pausePanelImg = getResource("image/pausePanel.png").getImage();
    private Image scorePanelImg = getResource("image/scoreDialog.png").getImage();

    static final int LINE_BORDER_OUTER_WEIGHT = 10;
    static final int LINE_BORDER_INNER_WEIGHT = 5;
    private final int VIEW_WIDTH = 800;
    private final int VIEW_HEIGHT = 600;
    static final int FONT_SIZE = 28;
    static final float LINE_SPACING = -0.45f;
    private static final long serialVersionUID = 2434035659171694595L;

    //P2
    private JTextPane boardPaneP2;
    private JTextPane nextBlockPaneP2;
    private JTextPane scorePaneP2;
    private JTextPane deletedRawPaneP2;

    //P1
    private JTextPane boardPaneP1;
    private JTextPane nextBlockPaneP1;
    private JTextPane scorePaneP1;
    private JTextPane deletedRawPaneP1;

    //기록
    private JTextPane recordScorePane;
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
    private JPanel backgroundPanel;

    private int getWidth(ConfigModel.BoardSize boardSize) {
        return switch (boardSize) {
            case SMALL -> -20;
            case MEDIUM -> 0;
            case LARGE -> 60;
        };
    }

    private BattlePresenter battlePresenter;
    private Player1KeyListener player1KeyListener;
    private Player2KeyListener player2KeyListener;
    private PauseKeyListener pauseKeyListener;

    public BattleView(final BattlePresenter presenter) {
        super("TETRIS");
        setSize(VIEW_WIDTH + getWidth(ConfigModel.boardSize), VIEW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.battlePresenter = presenter;

        backgroundPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(200 , 0 , 0 , 25));

        JPanel nextPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(nextImg, 0, 0, 70,120,null);
            }
        };
        nextPanel.setLayout(null);
        nextPanel.setBounds(280 + getWidth(ConfigModel.boardSize),54,70,120);

        JPanel nextPanel01 = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(nextImg, 0, 0, 70,120,null);
            }
        };
        nextPanel01.setLayout(null);
        nextPanel01.setBounds(450 + getWidth(ConfigModel.boardSize),54,70,120);

        JPanel scorePanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(scoreImg, 0, 0, 70,76,null);
            }
        };
        scorePanel.setLayout(null);
        scorePanel.setBounds(280 + getWidth(ConfigModel.boardSize),260,70,120);

        JPanel scorePanel01 = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(scoreImg, 0, 0, 70,76,null);
            }
        };
        scorePanel01.setLayout(null);
        scorePanel01.setBounds(450 + getWidth(ConfigModel.boardSize),260,70,120);

        JPanel linesPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(linesImg, 0, 0, 70,76,null);
            }
        };
        linesPanel.setLayout(null);
        linesPanel.setBounds(280 + getWidth(ConfigModel.boardSize),420,70,120);

        JPanel linesPanel01 = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(linesImg, 0, 0, 70,76,null);
            }
        };
        linesPanel01.setLayout(null);
        linesPanel01.setBounds(450 + getWidth(ConfigModel.boardSize),420,70,120);

        boardPaneP1 = new JTextPane();
        boardPaneP1.setEditable(false);
        boardPaneP1.setOpaque(false);
        boardPaneP1.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
        boardPaneP1.setBounds(40,55, 220 + getWidth(ConfigModel.boardSize),440);

        boardPaneP2 = new JTextPane();
        boardPaneP2.setEditable(false);
        boardPaneP2.setOpaque(false);
        boardPaneP2.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
        boardPaneP2.setBounds(540,55, 220 + getWidth(ConfigModel.boardSize),440);

        nextBlockPaneP1 = new JTextPane();
        nextBlockPaneP1.setEditable(false);
        nextBlockPaneP1.setOpaque(false);
        nextBlockPaneP1.setBounds(280 + getWidth(ConfigModel.boardSize) , 80, 70, 85);

        nextBlockPaneP2 = new JTextPane();
        nextBlockPaneP2.setEditable(false);
        nextBlockPaneP2.setOpaque(false);
        nextBlockPaneP2.setBounds(450 + getWidth(ConfigModel.boardSize) , 80, 70, 85);

        scorePaneP1 = new JTextPane();
        scorePaneP1.setEditable(false);
        scorePaneP1.setOpaque(false);
        scorePaneP1.setBounds(285 + getWidth(ConfigModel.boardSize), 290, 70, 70);

        scorePaneP2 = new JTextPane();
        scorePaneP2.setEditable(false);
        scorePaneP2.setOpaque(false);
        scorePaneP2.setBounds(455 + getWidth(ConfigModel.boardSize), 290, 70, 70);

        deletedRawPaneP1 = new JTextPane();
        deletedRawPaneP1.setEditable(false);
        deletedRawPaneP1.setOpaque(false);
        deletedRawPaneP1.setBounds(285 + getWidth(ConfigModel.boardSize), 450, 70, 70);

        deletedRawPaneP2 = new JTextPane();
        deletedRawPaneP2.setEditable(false);
        deletedRawPaneP2.setOpaque(false);
        deletedRawPaneP2.setBounds(455 + getWidth(ConfigModel.boardSize), 450, 70, 70);

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

        continueBtn.addActionListener(e -> battlePresenter.gameStart());
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
            App.navigate(App.View.MAIN);
        });

        backgroundPanel.add(scoreDialog);
        this.getContentPane().add(backgroundPanel);
        backgroundPanel.add(pauseDialog);
        pauseDialog.add(continueBtn);
        pauseDialog.add(exitBtn);
        backgroundPanel.add(boardPaneP1);
        backgroundPanel.add(boardPaneP2);
        backgroundPanel.add(scorePaneP1);
        backgroundPanel.add(scorePaneP2);
        backgroundPanel.add(deletedRawPaneP1);
        backgroundPanel.add(deletedRawPaneP2);
        backgroundPanel.add(nextBlockPaneP1);
        backgroundPanel.add(nextBlockPaneP2);
        backgroundPanel.add(nextPanel);
        backgroundPanel.add(nextPanel01);
        backgroundPanel.add(scorePanel);
        backgroundPanel.add(scorePanel01);
        backgroundPanel.add(linesPanel);
        backgroundPanel.add(linesPanel01);
        scoreDialog.add(recordScorePane);
        scoreDialog.add(namePane);
        scoreDialog.add(enterBtn);

        styleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(styleSet, FONT_SIZE);
        StyleConstants.setLineSpacing(styleSet, LINE_SPACING);
        StyleConstants.setFontFamily(styleSet, "Courier New");
        StyleConstants.setBold(styleSet, true);
        StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);

        backgroundPanel.setFocusable(true);
        backgroundPanel.requestFocus();
        backgroundPanel.requestFocusInWindow();

        this.player1KeyListener = new Player1KeyListener();
        this.player2KeyListener = new Player2KeyListener();
        this.pauseKeyListener = new PauseKeyListener();
    }

    class Player1KeyListener implements KeyListener {
        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_S -> battlePresenter.moveDown(true);
                case KeyEvent.VK_D -> battlePresenter.moveRight(true);
                case KeyEvent.VK_A -> battlePresenter.moveLeft(true);
                case KeyEvent.VK_W -> battlePresenter.moveRotate(true);
                case KeyEvent.VK_SHIFT -> {
                    if(e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT) {
                        battlePresenter.moveStraightDown(true);
                    }
                }
                case KeyEvent.VK_ESCAPE -> battlePresenter.gameStop();
            }
        }

        @Override
        public void keyReleased(final KeyEvent e) {

        }
    }

    class Player2KeyListener implements KeyListener {
        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN -> battlePresenter.moveDown(false);
                case KeyEvent.VK_RIGHT -> battlePresenter.moveRight(false);
                case KeyEvent.VK_LEFT -> battlePresenter.moveLeft(false);
                case KeyEvent.VK_UP -> battlePresenter.moveRotate(false);
                case KeyEvent.VK_SHIFT -> {
                    if(e.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT) {
                        battlePresenter.moveStraightDown(false);
                    }
                }
                case KeyEvent.VK_ESCAPE -> battlePresenter.gameStop();
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
                case ESC -> battlePresenter.gameStart();
            }
        }

        @Override
        public void keyReleased(final KeyEvent e) {

        }
    }

    public void startPlayerKeyListen(boolean isPlayer1) {
        if(isPlayer1) backgroundPanel.addKeyListener(this.player1KeyListener);
        else backgroundPanel.addKeyListener(this.player2KeyListener);
    }

    public void stopPlayerKeyListen(boolean isPlayer1) {
        if(isPlayer1) backgroundPanel.removeKeyListener(this.player1KeyListener);
        else backgroundPanel.removeKeyListener(this.player2KeyListener);
    }

    public void startPauseKeyListen() {
        backgroundPanel.addKeyListener(this.pauseKeyListener);
        backgroundPanel.addKeyListener(this.pauseKeyListener);
    }

    public void stopPauseKeyListen() {
        backgroundPanel.removeKeyListener(this.pauseKeyListener);
        backgroundPanel.removeKeyListener(this.pauseKeyListener);
    }

    public void setVisiblePauseDialog(boolean ifVisible) {
        pauseDialog.setVisible(ifVisible);
    }

    public void setVisibleScoreDialog(boolean ifVisible) {
        scoreDialog.setVisible(ifVisible);
    }

    public final void drawBoard(final ArrayList<BoardElement[]> board, boolean isPlayer1) {
        if(!isPlayer1) {
            boardPaneP2.setText("");
            Style style = boardPaneP2.addStyle("textStyle", null);
            StyledDocument doc = boardPaneP2.getStyledDocument();

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
            boardPaneP2.setStyledDocument(doc);
        } else {
            boardPaneP1.setText("");
            Style style = boardPaneP1.addStyle("textStyle", null);
            StyledDocument doc = boardPaneP1.getStyledDocument();

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
            boardPaneP1.setStyledDocument(doc);
        }
    }

    public final void drawNextBlock(Block nextBlock, boolean isPlayer1) {
        if(!isPlayer1) {
            nextBlockPaneP2.setText("");
            Style style = nextBlockPaneP2.addStyle("textStyle", null);
            StyledDocument doc = nextBlockPaneP2.getStyledDocument();

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
            nextBlockPaneP2.setStyledDocument(doc);
        } else {
            nextBlockPaneP1.setText("");
            Style style = nextBlockPaneP1.addStyle("textStyle", null);
            StyledDocument doc = nextBlockPaneP1.getStyledDocument();

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
            nextBlockPaneP1.setStyledDocument(doc);
        }
    }

    public final void drawScore(double score, boolean isPlayer1) {
        if(!isPlayer1) {
            scorePaneP2.setText("");
            Style style = scorePaneP2.addStyle("textStyle", null);
            StyledDocument doc = scorePaneP2.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            StyleConstants.setForeground(style, Color.WHITE);
            StyleConstants.setFontSize(style, 24);
            try {
                doc.insertString(doc.getLength(), Integer.toString((int) score), style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            scorePaneP2.setStyledDocument(doc);
        } else {
            scorePaneP1.setText("");
            Style style = scorePaneP1.addStyle("textStyle", null);
            StyledDocument doc = scorePaneP1.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            StyleConstants.setForeground(style, Color.WHITE);
            StyleConstants.setFontSize(style, 24);
            try {
                doc.insertString(doc.getLength(), Integer.toString((int) score), style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            scorePaneP1.setStyledDocument(doc);
        }

    }

    public final void drawDeletedRaw(int deletedRaw, boolean isPlayer1) {
        if(!isPlayer1) {
            deletedRawPaneP2.setText("");
            Style style = deletedRawPaneP2.addStyle("textStyle", null);
            StyledDocument doc = deletedRawPaneP2.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            StyleConstants.setForeground(style, Color.WHITE);
            StyleConstants.setFontSize(style, 24);
            try {
                doc.insertString(doc.getLength(), Integer.toString(deletedRaw), style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            deletedRawPaneP2.setStyledDocument(doc);
        } else {
            deletedRawPaneP1.setText("");
            Style style = deletedRawPaneP1.addStyle("textStyle", null);
            StyledDocument doc = deletedRawPaneP1.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            StyleConstants.setForeground(style, Color.WHITE);
            StyleConstants.setFontSize(style, 24);
            try {
                doc.insertString(doc.getLength(), Integer.toString(deletedRaw), style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            deletedRawPaneP1.setStyledDocument(doc);
        }

    }

    public final void drawScrollDialog(int scoreLeft, int scoreRight) {
        recordScorePane.setText("");
        Style style = recordScorePane.addStyle("textStyle", null);
        StyledDocument doc = recordScorePane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StyleConstants.setForeground(style, Color.WHITE);
        StyleConstants.setFontSize(style, 40);
        try {
            doc.insertString(doc.getLength(), Integer.toString((int) scoreLeft), style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        recordScorePane.setStyledDocument(doc);
    }
}
