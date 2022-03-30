package team11.tetris.utills;

import java.awt.*;

public enum BoardElement {
    EMPTY, BORDER, I_BLOCK, J_BLOCK, L_BLOCK, O_BLOCK, S_BLOCK, T_BLOCK, Z_BLOCK;
    // @TODO 추후 아이템 Enum값 추가

    public static Color getElementColor(BoardElement element) {
        return switch (element) {
            case EMPTY -> Color.BLACK;
            case BORDER -> Color.WHITE;
            case I_BLOCK -> Color.CYAN;
            case J_BLOCK -> Color.BLUE;
            case L_BLOCK -> Color.ORANGE;
            case O_BLOCK -> Color.YELLOW;
            case S_BLOCK -> Color.GREEN;
            case T_BLOCK -> Color.MAGENTA;
            case Z_BLOCK -> Color.RED;
        };
    }

    public static String getElementText(BoardElement element) {
        return switch (element) {
            case EMPTY -> " ";
            case BORDER -> "X";
            case I_BLOCK, J_BLOCK, L_BLOCK, O_BLOCK, S_BLOCK, T_BLOCK, Z_BLOCK -> "O";
        };
    }
}
