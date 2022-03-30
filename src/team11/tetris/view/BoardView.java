package team11.tetris.view;

import team11.tetris.presenter.BoardPresenter;
import team11.tetris.utills.BoardElement;

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

	private static final long serialVersionUID = 2434035659171694595L;

	private JTextPane pane;
	private SimpleAttributeSet styleSet;

	private BoardPresenter boardPresenter;
	private PlayerKeyListener playerKeyListener;

	public BoardView(BoardPresenter boardPresenter) {
		super("SeoulTech SE 11team Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// boardPresenter inject
		this.boardPresenter = boardPresenter;

		// Board display setting.
		pane = new JTextPane();
		pane.setEditable(false);
		pane.setBackground(Color.BLACK);
		CompoundBorder border = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 10),
				BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
		pane.setBorder(border);
		this.getContentPane().add(pane, BorderLayout.CENTER);

		// Document default style.
		styleSet = new SimpleAttributeSet();
		StyleConstants.setFontSize(styleSet, 18);
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
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
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
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}

	public void drawBoard(ArrayList<BoardElement[]> board) {
		// Set Config
		pane.setText("");
		Style style = pane.addStyle("textStyle", null);
		StyledDocument doc = pane.getStyledDocument();

		// Build Styled Document
		try {
			for(int i=0; i<board.size() + 2; i++) {
				for(int j=0; j<board.get(0).length + 2; j++) {
					boolean isBorder = i == 0 || i == board.size() + 1 || j == 0 || j == board.get(0).length + 1;
					if(isBorder) {
						StyleConstants.setForeground(style, BoardElement.getElementColor(BoardElement.BORDER));
						doc.insertString(doc.getLength(), BoardElement.getElementText(BoardElement.BORDER), style);
					} else {
						StyleConstants.setForeground(style, BoardElement.getElementColor(board.get(i-1)[j-1]));
						doc.insertString(doc.getLength(), BoardElement.getElementText(board.get(i-1)[j-1]), style);
					}
				}
				doc.insertString(doc.getLength(), "\n", style);
			}
		} catch (BadLocationException e) {}

		doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
		pane.setStyledDocument(doc);
	}

}
