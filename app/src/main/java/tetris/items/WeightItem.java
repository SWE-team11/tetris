package tetris.items;

import tetris.utils.Block;
import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

public class WeightItem extends Block {
    public WeightItem() {
        shape = new BoardElement[][] {
                { BoardElement.EMPTY, BoardElement.WEIGHT_ITEM, BoardElement.WEIGHT_ITEM, BoardElement.EMPTY },
                { BoardElement.WEIGHT_ITEM, BoardElement.WEIGHT_ITEM, BoardElement.WEIGHT_ITEM, BoardElement.WEIGHT_ITEM}
        };
        kind = BlockKind.WEIGHT_ITEM;
    }

    @Override
    public void rotate() { }

    @Override
    public boolean isItemBlock() {
        return true;
    }
    
}
