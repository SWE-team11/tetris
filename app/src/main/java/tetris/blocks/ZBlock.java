package tetris.blocks;

import tetris.utils.BoardElement;

public class ZBlock extends Block {
    public ZBlock() {
        shape = new BoardElement[][] {
            {BoardElement.Z_BLOCK, BoardElement.Z_BLOCK, BoardElement.EMPTY},
            {BoardElement.EMPTY, BoardElement.Z_BLOCK, BoardElement.Z_BLOCK}
        };
    }
}
