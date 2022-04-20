package tetris.blocks;

import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

public class TBlock extends Block {
    public TBlock() {
        shape = new BoardElement[][] {
                {BoardElement.EMPTY, BoardElement.T_BLOCK, BoardElement.EMPTY},
                {BoardElement.T_BLOCK, BoardElement.T_BLOCK, BoardElement.T_BLOCK}
        };
        kind = BlockKind.T_BLOCK;
    }
}
