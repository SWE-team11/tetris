package tetris.view;

import tetris.presenter.BoardPresenter;
import tetris.utills.BoardElement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.text.*;

public class BoardView extends JFrame {

    static final int LINE_BORDER_OUTER_WEIGHT = 10;
    static final int LINE_BORDER_INNER_WEIGHT = 5;
    static final int FONT_SIZE = 25;
    static final float LINE_SPACING = -0.3f;
    private static final long serialVersionUID = 2434035659171694595L;

    private JTextPane pane;
    private SimpleAttributeSet styleSet;

    private BoardPresenter boardPresenter;
    private PlayerKeyListener playerKeyListener;

    public BoardView(final BoardPresenter presenter) {
        super("SeoulTech SE 11team Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // boardPresenter inject
        this.boardPresenter = presenter;

        // Board display setting.
        pane = new JTextPane();
        pane.setEditable(false);
        pane.setBackground(Color.BLACK);
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, LINE_BORDER_OUTER_WEIGHT),
                BorderFactory.createLineBorder(Color.DARK_GRAY, LINE_BORDER_INNER_WEIGHT));
        pane.setBorder(border);
        this.getContentPane().add(pane, BorderLayout.CENTER);

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
        pane.addKeyListener(this.playerKeyListener);
    }

    class PlayerKeyListener implements KeyListener {
        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    boardPresenter.moveDown();
                    break;
                case KeyEvent.VK_RIGHT:
                    boardPresenter.moveRight();
                    break;
                case KeyEvent.VK_LEFT:
                    boardPresenter.moveLeft();
                    break;
                case KeyEvent.VK_UP:
                    boardPresenter.moveRotate();
                    break;
                case KeyEvent.VK_SPACE:
                    boardPresenter.moveStraightDown();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(final KeyEvent e) {

        }
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
