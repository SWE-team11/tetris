package tetris.blocks;

import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

public class JBlock extends Block {
    public JBlock() {
        shape = new BoardElement[][] {
            {BoardElement.J_BLOCK, BoardElement.J_BLOCK, BoardElement.J_BLOCK},
            {BoardElement.EMPTY, BoardElement.EMPTY, BoardElement.J_BLOCK}
        };
        kind = BlockKind.J_BLOCK;
    }
}
