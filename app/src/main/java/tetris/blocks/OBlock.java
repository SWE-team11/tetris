package tetris.blocks;

import tetris.utills.BoardElement;

public class OBlock extends Block {
    public OBlock() {
        shape = new BoardElement[][] {
            {BoardElement.O_BLOCK, BoardElement.O_BLOCK},
            {BoardElement.O_BLOCK, BoardElement.O_BLOCK}
        };
    }
}
