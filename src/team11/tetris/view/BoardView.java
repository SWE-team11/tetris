package team11.tetris.view;

import team11.tetris.blocks.Block;
import team11.tetris.model.BoardModel;
import team11.tetris.model.ConfigModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

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
	private Timer timer;

	// 모델 주입
	public BoardModel boardModel;
	public ConfigModel configModel;

	private static final int initInterval = 1000;
	
	public BoardView() {
		super("SeoulTech SE 11team Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Board display setting.
		pane = new JTextPane();
		pane.setEditable(false);
		pane.setBackground(Color.BLACK);
		CompoundBorder border = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 10),
				BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
		pane.setBorder(border);
		this.getContentPane().add(pane, BorderLayout.CENTER);
		
		//Document default style.
		styleSet = new SimpleAttributeSet();
		StyleConstants.setFontSize(styleSet, 18);
		StyleConstants.setFontFamily(styleSet, "Courier");
		StyleConstants.setBold(styleSet, true);
//		StyleConstants.setForeground(styleSet, Color.WHITE);
		StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);
		
		//Initialize board for the game.
		setFocusable(true);
		requestFocus();
	}

	public void setKeyListner(KeyListener playerKeyListener) {
		addKeyListener(playerKeyListener);
	}

	//Set timer for block drops.
	public void setTimerActionListener(ActionListener timerActionListener) {
		timer = new Timer(initInterval, timerActionListener);
	}

	public void injectModel(BoardModel boardModel, ConfigModel configModel) {
		this.boardModel = boardModel;
		this.configModel = configModel;
	}

	//Create the first block and draw.
	public void initView() {
		placeBlock();
		drawBoard();
		timer.start();
	}

	public void placeBlock() {
		Block currentBlock = boardModel.currentBlock;
		int x = boardModel.x;
		int y = boardModel.y;

		StyledDocument doc = pane.getStyledDocument();
		SimpleAttributeSet styles = new SimpleAttributeSet();
		StyleConstants.setForeground(styles, currentBlock.getColor());
		for(int j=0; j<currentBlock.height(); j++) {
			int rows = y+j == 0 ? 0 : y+j-1;
			int offset = rows * (configModel.WIDTH+3) + x + 1;
			doc.setCharacterAttributes(offset, currentBlock.width(), styles, true);
			for(int i=0; i<currentBlock.width(); i++) {
				boardModel.board.get(y + j)[x+i] = currentBlock.getShape(i, j);
			}
		}
	}

	public void drawBoard() {
		pane.setText("");
		Style style = pane.addStyle("textStyle", null);
		StyleConstants.setForeground(style, Color.white);
		StyledDocument doc = pane.getStyledDocument();

		StringBuffer sb = new StringBuffer();
		for(int t=0; t<configModel.WIDTH+2; t++)
			try{doc.insertString(doc.getLength(), Character.toString(configModel.BORDER_CHAR),style);}
			catch (BadLocationException e){}
		try{doc.insertString(doc.getLength(), "\n",style);}
		catch (BadLocationException e){}

		for(int i=0; i < boardModel.board.size(); i++) {
			try{doc.insertString(doc.getLength(), Character.toString(configModel.BORDER_CHAR),style);}
			catch (BadLocationException e){}
			for(int j = 0; j < boardModel.board.get(i).length; j++) {
				StyleConstants.setForeground(style, boardModel.currentBlock.getColor());
				try{doc.insertString(doc.getLength(), boardModel.board.get(i)[j] == 1 ? "0" : " ",style);}
				catch (BadLocationException e){}
			}
			StyleConstants.setForeground(style, Color.white);

			try{doc.insertString(doc.getLength(), Character.toString(configModel.BORDER_CHAR)+"\n",style);}
			catch (BadLocationException e){}
		}
		for(int t=0; t<configModel.WIDTH+2; t++)
			try{doc.insertString(doc.getLength(), Character.toString(configModel.BORDER_CHAR),style);}
			catch (BadLocationException e){}
		doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
		pane.setStyledDocument(doc);
	}

}
