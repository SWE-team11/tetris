package tetris.blocks;

import tetris.utills.BoardElement;

import java.awt.Color;

public abstract class Block {

    protected BoardElement[][] shape;
    protected Color color;

    public Block() {
        shape = new BoardElement[][]{
                {BoardElement.O_BLOCK, BoardElement.O_BLOCK},
                {BoardElement.O_BLOCK, BoardElement.O_BLOCK}
        };
        color = Color.YELLOW;
    }

    public final BoardElement getShape(final int x, final int y) {
        return shape[y][x];
    }

    public final Color getColor() {
        return color;
    }

    public final void rotate() {
        BoardElement[][] temp = new BoardElement[width()][height()];
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                temp[i][j] = shape[height() - 1 - j][i];
            }
        }
        shape = temp;
        // @TODO Rotate the block 90 deg. clockwise.
    }

    public final int height() {
        return shape.length;
    }

    public final int width() {
        if (shape.length > 0) {
            return shape[0].length;
        }
        return 0;
    }
}
