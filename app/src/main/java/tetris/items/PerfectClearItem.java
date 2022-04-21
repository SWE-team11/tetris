package tetris.items;

import java.util.Random;

import tetris.utils.Block;
import tetris.utils.BlockKind;
import tetris.utils.BoardElement;

public class PerfectClearItem extends Block {
    private int itemPosY;
    private int itemPosX;

    public PerfectClearItem() {
        Random rnd = new Random(System.currentTimeMillis());
        int rndNum = rnd.nextInt(7);
        BlockKind blockKind = BlockKind.values()[rndNum];
        Block originalBlock = BlockKind.getBlockInstance(blockKind);
        boardElement = originalBlock.getBoardElement();
        shape = originalBlock.getFullShape();

        int cnt = 0;
        rndNum = rnd.nextInt(4);
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != BoardElement.EMPTY) {
                    if (cnt == rndNum) {
                        shape[i][j] = BoardElement.PERFECT_CLEAR_ITEM;
                        itemPosY = i;
                    }
                    cnt++;
                }
            }
        }

        kind = BlockKind.PERFECT_CLEAR_ITEM;
    }
    
    @Override
    public void rotate() {
        BoardElement[][] temp = new BoardElement[width()][height()];
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                temp[i][j] = shape[height() - 1 - j][i];
                if (temp[i][j] == BoardElement.PERFECT_CLEAR_ITEM) {
                    itemPosY = i;
                    itemPosX = j;
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
    public int getItemPosX() {
        return itemPosX;
    }

    @Override
    public boolean isItemBlock() {
        return true;
    }
}
