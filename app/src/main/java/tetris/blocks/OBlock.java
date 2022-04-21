package tetris.blocks;

import tetris.utils.Block;
import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

public class OBlock extends Block {
    public OBlock() {
        boardElement = BoardElement.O_BLOCK;
        shape = new BoardElement[][] {
            {BoardElement.O_BLOCK, BoardElement.O_BLOCK},
            {BoardElement.O_BLOCK, BoardElement.O_BLOCK}
        };
        kind = BlockKind.O_BLOCK;
    }
}
