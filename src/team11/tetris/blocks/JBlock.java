package team11.tetris.blocks;

import team11.tetris.utills.BoardElement;

public class JBlock extends Block {
	
	public JBlock() {
		shape = new BoardElement[][] {
				{BoardElement.J_BLOCK, BoardElement.J_BLOCK, BoardElement.J_BLOCK},
				{BoardElement.EMPTY, BoardElement.EMPTY, BoardElement.J_BLOCK}
		};
		color = BoardElement.getElementColor(BoardElement.J_BLOCK);
	}
}
