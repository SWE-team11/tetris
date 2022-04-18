package tetris.blocks;

import tetris.utills.BoardElement;

import java.awt.Color;

public abstract class Block {

    protected BoardElement[][] shape;

    public Block() {
        shape = new BoardElement[][]{
                {BoardElement.O_BLOCK, BoardElement.O_BLOCK},
                {BoardElement.O_BLOCK, BoardElement.O_BLOCK}
        };
    }

    public final BoardElement getShape(final int x, final int y) {
        return shape[y][x];
    }

    public final void rotate() {
        BoardElement[][] temp = new BoardElement[width()][height()];
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                temp[i][j] = shape[height() - 1 - j][i];
            }
        }
        shape = temp;
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
