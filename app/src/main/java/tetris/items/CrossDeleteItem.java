package tetris.items;

import tetris.utils.Block;
import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

public class CrossDeleteItem extends Block {
    private int itemPosY;

    public CrossDeleteItem() {
        shape = new BoardElement[][] {
                {BoardElement.CROSS_DELETE_ITEM}
        };
        kind = BlockKind.CROSS_DELETE_ITEM;
        itemPosY = 0;
    }

    @Override
    public int getItemPosY() {
        return itemPosY;
    }

    @Override
    public boolean isItemBlock() {
        return true;
    }
}
