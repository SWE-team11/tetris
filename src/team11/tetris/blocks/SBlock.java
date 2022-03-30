package team11.tetris.blocks;

import team11.tetris.utills.BoardElement;

import java.awt.Color;

public class SBlock extends Block {

	public SBlock() {
		shape = new BoardElement[][] {
			{BoardElement.EMPTY, BoardElement.S_BLOCK, BoardElement.S_BLOCK},
			{BoardElement.S_BLOCK, BoardElement.S_BLOCK, BoardElement.EMPTY}
		};
		color = BoardElement.getElementColor(BoardElement.S_BLOCK);
	}
}
