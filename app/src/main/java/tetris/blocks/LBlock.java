package tetris.blocks;

import tetris.utills.BoardElement;

public class LBlock extends Block {

    public LBlock() {
        shape = new BoardElement[][] {
            {BoardElement.L_BLOCK, BoardElement.L_BLOCK, BoardElement.L_BLOCK},
            {BoardElement.L_BLOCK, BoardElement.EMPTY, BoardElement.EMPTY}
        };
    }
}
