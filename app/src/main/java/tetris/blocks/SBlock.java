package tetris.blocks;

import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

public class SBlock extends Block {
    public SBlock() {
        shape = new BoardElement[][] {
            {BoardElement.EMPTY, BoardElement.S_BLOCK, BoardElement.S_BLOCK},
            {BoardElement.S_BLOCK, BoardElement.S_BLOCK, BoardElement.EMPTY}
        };
        kind = BlockKind.S_BLOCK;
    }
}
