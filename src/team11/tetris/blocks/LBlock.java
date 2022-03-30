package team11.tetris.blocks;

import team11.tetris.utills.BoardElement;

import java.awt.Color;

public class LBlock extends Block {
	
	public LBlock() {
		shape = new BoardElement[][] {
			{BoardElement.L_BLOCK, BoardElement.L_BLOCK, BoardElement.L_BLOCK},
			{BoardElement.L_BLOCK, BoardElement.EMPTY, BoardElement.EMPTY}
		};
		color = BoardElement.getElementColor(BoardElement.L_BLOCK);
	}
}
