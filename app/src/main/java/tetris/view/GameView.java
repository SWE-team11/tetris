package tetris.view;

import tetris.App;
import tetris.model.ConfigModel;
import tetris.presenter.GamePresenter;
import tetris.utils.BoardElement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
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

    private JTextPane pane;
    private SimpleAttributeSet styleSet;
    private JPanel pauseDialog = new JPanel();

    private GamePresenter gamePresenter;
    private PlayerKeyListener playerKeyListener;
    private PauseKeyListener pauseKeyListener;

    public GameView(final GamePresenter presenter) {
        super("SeoulTech SE 11team Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // boardPresenter inject
        this.gamePresenter = presenter;

        // Board display setting.
        JPanel backgroundPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(200 , 0 , 0 , 25));

        // pane display setting
        pane = new JTextPane();
        pane.setEditable(false);
        pane.setOpaque(false);
        pane.setBounds(40,55, 220,440);

        // pause display setting
        pauseDialog.setBounds(100, 200, 200, 100);
        pauseDialog.setLayout(null);
        pauseDialog.setVisible(false);
        pauseDialog.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 50));
        JButton continueBtn = new JButton("Continue");
        JButton exitBtn = new JButton("Exit");
        continueBtn.setPreferredSize(new Dimension(60,30));
        exitBtn.setPreferredSize(new Dimension(60,30));

        // Button key listener
        continueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                gamePresenter.gameStart();
            }
        });
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                App.navigate(App.View.Main);
            }
        });

        // panel add
        this.getContentPane().add(backgroundPanel);
        pauseDialog.add(continueBtn);
        pauseDialog.add(exitBtn);
        backgroundPanel.add(pauseDialog);
        backgroundPanel.add(pane, BorderLayout.CENTER);

        // Document default style.
        styleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(styleSet, FONT_SIZE);
        StyleConstants.setLineSpacing(styleSet, LINE_SPACING);
        StyleConstants.setFontFamily(styleSet, "Courier New");

        StyleConstants.setBold(styleSet, true);
        StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);

        // Initialize board for the game.
        pane.setFocusable(true);
        pane.requestFocus();
        pane.requestFocusInWindow();

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
        pane.addKeyListener(this.playerKeyListener);
    }

    public void stopPlayerKeyListen() {
        pane.removeKeyListener(this.playerKeyListener);
    }

    public void startPauseKeyListen() {
        pane.addKeyListener(this.pauseKeyListener);
    }

    public void stopPauseKeyListen() {
        pane.removeKeyListener(this.pauseKeyListener);
    }

    public void setVisiblePauseDialog(boolean ifVisible) {
        pauseDialog.setVisible(ifVisible);
    }

    public final void drawBoard(final ArrayList<BoardElement[]> board) {
        // Set Config
        pane.setText("");
        Style style = pane.addStyle("textStyle", null);
        StyledDocument doc = pane.getStyledDocument();

        // Build Styled Document
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
        pane.setStyledDocument(doc);
    }

}
