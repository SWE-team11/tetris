package team11.tetris.blocks;

import team11.tetris.utills.BoardElement;

import java.awt.Color;

public class TBlock extends Block {
	
	public TBlock() {
		shape = new BoardElement[][] {
			{BoardElement.EMPTY, BoardElement.T_BLOCK, BoardElement.EMPTY},
			{BoardElement.T_BLOCK, BoardElement.T_BLOCK, BoardElement.T_BLOCK}
		};
		color = BoardElement.getElementColor(BoardElement.T_BLOCK);
	}
}
