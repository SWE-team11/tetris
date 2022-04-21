package tetris.view;

import tetris.App;
import tetris.model.ConfigModel;
import tetris.presenter.GamePresenter;
import tetris.utils.Block;
import tetris.utils.BoardElement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.*;

public class GameView extends JFrame {

    private ImageIcon getResource(String path) {
        return new ImageIcon(getClass().getClassLoader().getResource(path));
    }

    private Image background = getResource("image/GameViewBackground.png").getImage();

    static final int LINE_BORDER_OUTER_WEIGHT = 10;
    static final int LINE_BORDER_INNER_WEIGHT = 5;
    static final int FONT_SIZE = 28;
    static final float LINE_SPACING = -0.45f;
    private static final long serialVersionUID = 2434035659171694595L;

    private JTextPane boardPane;
    private JTextPane nextBlockPane;
    private JTextPane scorePane;
    private JTextPane levelPane;
    private JTextPane deletedRawPane;
    private SimpleAttributeSet styleSet;
    private JPanel pauseDialog = new JPanel();

    private GamePresenter gamePresenter;
    private PlayerKeyListener playerKeyListener;
    private PauseKeyListener pauseKeyListener;

    public GameView(final GamePresenter presenter) {
        super("TETRIS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.gamePresenter = presenter;

        JPanel backgroundPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(200 , 0 , 0 , 25));

        boardPane = new JTextPane();
        boardPane.setEditable(false);
        boardPane.setOpaque(false);
        boardPane.setBounds(40,55, 220,440);

        nextBlockPane = new JTextPane();
        nextBlockPane.setEditable(false);
        nextBlockPane.setOpaque(false);
        nextBlockPane.setBounds(290, 90, 70, 80);

        scorePane = new JTextPane();
        scorePane.setEditable(false);
        scorePane.setOpaque(false);
        scorePane.setBounds(290, 317, 70, 70);

        levelPane = new JTextPane();
        levelPane.setEditable(false);
        levelPane.setOpaque(false);
        levelPane.setBounds(290, 385, 70, 70);

        deletedRawPane = new JTextPane();
        deletedRawPane.setEditable(false);
        deletedRawPane.setOpaque(false);
        deletedRawPane.setBounds(290, 462, 70, 70);

        pauseDialog.setBounds(100, 200, 200, 100);
        pauseDialog.setLayout(null);
        pauseDialog.setVisible(false);
        pauseDialog.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 50));

        JButton continueBtn = new JButton("Continue");
        JButton exitBtn = new JButton("Exit");
        continueBtn.setPreferredSize(new Dimension(60,30));
        exitBtn.setPreferredSize(new Dimension(60,30));

        continueBtn.addActionListener(e -> gamePresenter.gameStart());
        exitBtn.addActionListener(e -> App.navigate(App.View.MAIN));

        this.getContentPane().add(backgroundPanel);
        pauseDialog.add(continueBtn);
        pauseDialog.add(exitBtn);
        backgroundPanel.add(pauseDialog);
        backgroundPanel.add(boardPane);
        backgroundPanel.add(nextBlockPane);
        backgroundPanel.add(scorePane);
        backgroundPanel.add(levelPane);
        backgroundPanel.add(deletedRawPane);

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
        levelPane.setText("");
        Style style = levelPane.addStyle("textStyle", null);
        StyledDocument doc = levelPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StyleConstants.setForeground(style, Color.WHITE);
        StyleConstants.setFontSize(style, 24);
        try {
            doc.insertString(doc.getLength(), ConfigModel.gameDifficulty.name(), style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        levelPane.setStyledDocument(doc);
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
}
