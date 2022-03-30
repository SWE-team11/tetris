package team11.tetris.blocks;

import team11.tetris.utills.BoardElement;

import java.awt.Color;

public class ZBlock extends Block {
	
	public ZBlock() {
		shape = new BoardElement[][] {
			{BoardElement.Z_BLOCK, BoardElement.Z_BLOCK, BoardElement.EMPTY},
			{BoardElement.EMPTY, BoardElement.Z_BLOCK, BoardElement.Z_BLOCK}
		};
		color = BoardElement.getElementColor(BoardElement.Z_BLOCK);
	}
}
