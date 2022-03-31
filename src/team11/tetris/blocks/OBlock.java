package team11.tetris.blocks;

import team11.tetris.utills.BoardElement;

public class OBlock extends Block {

	public OBlock() {
		shape = new BoardElement[][] {
			{BoardElement.O_BLOCK, BoardElement.O_BLOCK},
			{BoardElement.O_BLOCK, BoardElement.O_BLOCK}
		};
		color = BoardElement.getElementColor(BoardElement.O_BLOCK);
	}
}
