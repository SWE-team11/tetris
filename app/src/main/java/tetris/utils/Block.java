package tetris.utils;

public abstract class Block {
    protected BlockKind kind;
    protected BoardElement boardElement;
    protected BoardElement[][] shape;

    public Block() {}

    public final BoardElement getShape(final int x, final int y) {
        return shape[y][x];
    }

    public final BoardElement[][] getFullShape() {
        return shape;
    }

    public void rotate() {
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

    public int getItemPosY() {
        throw new UnsupportedOperationException();
    }

    public int getItemPosX() {
        throw new UnsupportedOperationException();
    }

    public BoardElement getBoardElement() {
        return this.boardElement;
    }

    public boolean isItemBlock() {
        return false;
    }

    public BlockKind getKind() {
        return kind;
    }
}
