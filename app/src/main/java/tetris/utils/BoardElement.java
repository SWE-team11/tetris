package tetris.utils;

import tetris.model.ConfigModel;

import java.awt.*;

public enum BoardElement {
    EMPTY, BORDER, I_BLOCK, J_BLOCK, L_BLOCK, O_BLOCK, S_BLOCK, T_BLOCK, Z_BLOCK, DELETE;
    // @TODO 추후 아이템 Enum값 추가

    public static Color getElementColor(final BoardElement element) {
        if (ConfigModel.colorBlindMode) {
            return switch (element) {
                case EMPTY -> Color.BLACK;
                case BORDER -> Color.WHITE;
                case I_BLOCK -> Color.decode("#0074B6"); //Sky Blue
                case J_BLOCK -> Color.decode("#1BB7ED"); //Blue
                case L_BLOCK -> Color.decode("#F19B00"); //Orange
                case O_BLOCK -> Color.decode("#F3E300"); //Yellow
                case S_BLOCK -> Color.decode("#00A270"); //Bluish Green
                case T_BLOCK -> Color.decode("#D974A9"); //Reddish Purple
                case Z_BLOCK -> Color.decode("#E65400"); //Vermilion
                case DELETE -> Color.white;
            };
        } else {
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
                case DELETE -> Color.white;
            };
        }

    }

    public static String getElementText(final BoardElement element) {
        return switch (element) {
            case EMPTY -> " ";
            case BORDER -> "X";
            case I_BLOCK, J_BLOCK, L_BLOCK, O_BLOCK, S_BLOCK, T_BLOCK, Z_BLOCK, DELETE -> "O";
        };
    }
}
