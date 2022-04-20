package tetris.items;

import java.util.Random;

import tetris.utils.Block;
import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

public class LineClearItem extends Block {
    private int itemPosY;

    public LineClearItem() {
        Random rnd = new Random(System.currentTimeMillis());
        int rndNum = rnd.nextInt(7);
        BlockKind blockKind = BlockKind.values()[rndNum];
        shape = BlockKind.getBlockInstance(blockKind).getFullShape();

        int cnt = 0;
        rndNum = rnd.nextInt(4);
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != BoardElement.EMPTY) {
                    if (cnt == rndNum) {
                        shape[i][j] = BoardElement.LINE_CLEAR;
                        itemPosY = i;
                    }
                    cnt++;
                }
            }
        }

        kind = BlockKind.LINE_CLEAR_ITEM;
    }

    @Override
    public void rotate() {
        BoardElement[][] temp = new BoardElement[width()][height()];
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                temp[i][j] = shape[height() - 1 - j][i];
                if (temp[i][j] == BoardElement.LINE_CLEAR) {
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
