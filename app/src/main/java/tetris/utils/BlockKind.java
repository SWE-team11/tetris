package tetris.utils;

import tetris.blocks.*;
import tetris.items.*;

public enum BlockKind {
    J_BLOCK, L_BLOCK, Z_BLOCK, S_BLOCK, T_BLOCK, O_BLOCK, I_BLOCK, LINE_CLEAR_ITEM, BOMB_ITEM, PERFECT_CLEAR_ITEM;

    public static Block getBlockInstance(final BlockKind blockKind) {
        return switch (blockKind) {
            case I_BLOCK -> new IBlock();
            case J_BLOCK -> new JBlock();
            case L_BLOCK -> new LBlock();
            case Z_BLOCK -> new ZBlock();
            case S_BLOCK -> new SBlock();
            case T_BLOCK -> new TBlock();
            case O_BLOCK -> new OBlock();
            case LINE_CLEAR_ITEM -> new LineClearItem();
            case BOMB_ITEM -> new BombItem();
            case PERFECT_CLEAR_ITEM -> new PerfectClearItem();
        };
    }
}
