package tetris.items;

import tetris.utils.Block;
import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

public class BombItem extends Block{
    private int itemPosY;

    public BombItem() {
        shape = new BoardElement[][] {
            {BoardElement.BOMB}
        };
        kind = BlockKind.BOMB_ITEM;
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
