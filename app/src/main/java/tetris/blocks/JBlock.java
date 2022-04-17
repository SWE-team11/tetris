package tetris.blocks;

import tetris.utills.BoardElement;

public class JBlock extends Block {
    public JBlock() {
        shape = new BoardElement[][] {
            {BoardElement.J_BLOCK, BoardElement.J_BLOCK, BoardElement.J_BLOCK},
            {BoardElement.EMPTY, BoardElement.EMPTY, BoardElement.J_BLOCK}
        };
        color = BoardElement.getElementColor(BoardElement.J_BLOCK);
    }
}
