package team11.tetris.blocks;

import team11.tetris.utills.BoardElement;

import java.awt.Color;

public class IBlock extends Block {
	
	public IBlock() {
		shape = new BoardElement[][] {
			{BoardElement.I_BLOCK, BoardElement.I_BLOCK, BoardElement.I_BLOCK, BoardElement.I_BLOCK}
		};
		color = BoardElement.getElementColor(BoardElement.I_BLOCK);
	}
}
