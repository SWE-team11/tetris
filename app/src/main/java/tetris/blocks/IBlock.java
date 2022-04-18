package tetris.blocks;

import tetris.utills.BoardElement;

public class IBlock extends Block {
    public IBlock() {
        shape = new BoardElement[][] {
            {BoardElement.I_BLOCK, BoardElement.I_BLOCK, BoardElement.I_BLOCK, BoardElement.I_BLOCK}
        };
    }
}
