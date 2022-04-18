package tetris.blocks;

import tetris.utils.BoardElement;

public class SBlock extends Block {
    public SBlock() {
        shape = new BoardElement[][] {
            {BoardElement.EMPTY, BoardElement.S_BLOCK, BoardElement.S_BLOCK},
            {BoardElement.S_BLOCK, BoardElement.S_BLOCK, BoardElement.EMPTY}
        };
    }
}
