package tetris.blocks;

import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

public class IBlock extends Block {
    public IBlock() {
        shape = new BoardElement[][] {
            {BoardElement.I_BLOCK, BoardElement.I_BLOCK, BoardElement.I_BLOCK, BoardElement.I_BLOCK}
        };
        kind = BlockKind.I_BLOCK;
    }
}
