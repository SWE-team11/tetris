package tetris.blocks;

import tetris.utills.BoardElement;

public class TBlock extends Block {
    public TBlock() {
        shape = new BoardElement[][] {
                {BoardElement.EMPTY, BoardElement.T_BLOCK, BoardElement.EMPTY},
                {BoardElement.T_BLOCK, BoardElement.T_BLOCK, BoardElement.T_BLOCK}
        };
        color = BoardElement.getElementColor(BoardElement.T_BLOCK);
    }
}
