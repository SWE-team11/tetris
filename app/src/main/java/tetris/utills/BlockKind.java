package tetris.utills;

import tetris.blocks.*;

public enum BlockKind {
    I_BLOCK, J_BLOCK, L_BLOCK, Z_BLOCK, S_BLOCK, T_BLOCK, O_BLOCK;

    public static Block getBlockInstance(BlockKind blockKind) {
        return switch (blockKind) {
            case I_BLOCK -> new IBlock();
            case J_BLOCK -> new JBlock();
            case L_BLOCK -> new LBlock();
            case Z_BLOCK -> new ZBlock();
            case S_BLOCK -> new SBlock();
            case T_BLOCK -> new TBlock();
            case O_BLOCK -> new OBlock();
        };
    }
}
