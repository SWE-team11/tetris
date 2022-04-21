package tetris.blocks;

import tetris.utils.Block;
import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

public class ZBlock extends Block {
    public ZBlock() {
        boardElement = BoardElement.Z_BLOCK;
        shape = new BoardElement[][] {
            {BoardElement.Z_BLOCK, BoardElement.Z_BLOCK, BoardElement.EMPTY},
            {BoardElement.EMPTY, BoardElement.Z_BLOCK, BoardElement.Z_BLOCK}
        };
        kind = BlockKind.Z_BLOCK;
    }
}
