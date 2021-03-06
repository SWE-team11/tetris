package tetris.utils;

import tetris.blocks.*;
import tetris.items.*;

public enum BlockKind {
    J_BLOCK, L_BLOCK, Z_BLOCK, S_BLOCK, T_BLOCK, O_BLOCK, I_BLOCK,
    LINE_CLEAR_ITEM, BOMB_ITEM, CROSS_DELETE_ITEM, SAME_DELETE_ITEM, WEIGHT_ITEM;

    public static final int getTetrominoSize() {
        return 7;
    }

    public static final int getItemSize() {
        return 5;
    }

    public static final Block getBlockInstance(final BlockKind blockKind) {
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
            case CROSS_DELETE_ITEM -> new CrossDeleteItem();
            case SAME_DELETE_ITEM -> new SameDeleteItem();
            case WEIGHT_ITEM -> new WeightItem();
        };
    }
}
