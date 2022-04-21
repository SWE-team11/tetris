package tetris.utils;

import tetris.blocks.*;
import tetris.items.LineClearItem;
import tetris.items.SameDeleteItem;

public enum BlockKind {
    J_BLOCK, L_BLOCK, Z_BLOCK, S_BLOCK, T_BLOCK, O_BLOCK, I_BLOCK, LINE_CLEAR_ITEM, SAME_DELETE_ITEM;

    public static final int getTetrominoSize() {
        return 7;
    }

    public static final int getItemSize() {
        return 2;
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
            case SAME_DELETE_ITEM -> new SameDeleteItem();
        };
    }
}
