package tetris.items;

import tetris.utils.Block;
import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

import java.util.Random;

public class SameDeleteItem extends Block {
    private int itemPosY;

    public SameDeleteItem() {
        Random rnd = new Random(System.currentTimeMillis());
        int rndNum = rnd.nextInt(7);
        BlockKind blockKind = BlockKind.values()[rndNum];
        Block originalBlock = BlockKind.getBlockInstance(blockKind);
        shape = originalBlock.getFullShape();
        boardElement = originalBlock.getBoardElement();

        int cnt = 0;
        rndNum = rnd.nextInt(4);
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != BoardElement.EMPTY) {
                    if (cnt == rndNum) {
                        shape[i][j] = BoardElement.SAME_DELETE_ITEM;
                        itemPosY = i;
                    }
                    cnt++;
                }
            }
        }

        kind = BlockKind.SAME_DELETE_ITEM;
    }

    @Override
    public void rotate() {
        BoardElement[][] temp = new BoardElement[width()][height()];
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                temp[i][j] = shape[height() - 1 - j][i];
                if (temp[i][j] == BoardElement.SAME_DELETE_ITEM) {
                    itemPosY = i;
                }
            }
        }
        shape = temp;
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