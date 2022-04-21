package tetris.blocks;

import tetris.utils.Block;
import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

public class LBlock extends Block {

    public LBlock() {
        boardElement = BoardElement.L_BLOCK;
        shape = new BoardElement[][] {
            {BoardElement.L_BLOCK, BoardElement.L_BLOCK, BoardElement.L_BLOCK},
            {BoardElement.L_BLOCK, BoardElement.EMPTY, BoardElement.EMPTY}
        };
        kind = BlockKind.L_BLOCK;
    }
}
