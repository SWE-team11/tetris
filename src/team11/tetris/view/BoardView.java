package team11.tetris.view;

import team11.tetris.blocks.Block;
import team11.tetris.model.BoardModel;
import team11.tetris.model.ConfigModel;
import team11.tetris.presenter.BoardPresenter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.Timer;
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

	public void drawBoard(ArrayList<Integer[]> board, Color color) {
		pane.setText("");
		Style style = pane.addStyle("textStyle", null);
		StyleConstants.setForeground(style, Color.white);
		StyledDocument doc = pane.getStyledDocument();

		try {
			for (int t = 0; t < board.get(0).length + 2; t++)
				doc.insertString(doc.getLength(), Character.toString('X'), style);
			doc.insertString(doc.getLength(), "\n", style);

			for (int i = 0; i < board.size(); i++) {
				doc.insertString(doc.getLength(), Character.toString('X'), style);
				for (int j = 0; j < board.get(i).length; j++) {
					StyleConstants.setForeground(style, color);
					doc.insertString(doc.getLength(), board.get(i)[j] > 0 ? "0" : " ", style);
				}
				StyleConstants.setForeground(style, Color.white);
				doc.insertString(doc.getLength(), Character.toString('X') + "\n", style);
			}

			for (int t = 0; t < board.get(0).length + 2; t++)
				doc.insertString(doc.getLength(), Character.toString('X'), style);
		} catch (BadLocationException e) {
		}

		doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
		pane.setStyledDocument(doc);
	}

}
