package team11.tetris.blocks;

import team11.tetris.utills.BoardElement;

import java.awt.Color;

public abstract class Block {
		
	protected BoardElement[][] shape;
	protected Color color;
	protected int[] bottomProjection;
	protected int[] rightProjection;
	protected int[] leftProjection;

	public Block() {
		shape = new BoardElement[][]{
				{BoardElement.O_BLOCK, BoardElement.O_BLOCK},
				{BoardElement.O_BLOCK, BoardElement.O_BLOCK}
		};
		color = Color.YELLOW;
	}
	
	public BoardElement getShape(int x, int y) {
		return shape[y][x];
	}

	public int[] getBottomProjection() {
		int[] bottom = new int[shape[0].length];
		for (int i = 0; i < width(); i++) {
			for (int j = height() - 1; j >= 0; j--) {
				if (shape[j][i] != BoardElement.EMPTY) {
					bottom[i] = j;
					break;
				}
			}
		}
		return bottom;
	}

	public int[] getRightProjection() {
		int[] right = new int[shape.length];
		for (int i = 0; i < height(); i++) {
			for (int j = width() - 1; j >= 0; j--) {
				if (shape[i][j] != BoardElement.EMPTY) {
					right[i] = j;
					break;
				}
			}
		}
		return right;
	}

	public int[] getLeftProjection() {
		int[] left = new int[shape.length];
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				if (shape[i][j] != BoardElement.EMPTY) {
					left[i] = j;
					break;
				}
			}
		}
		return left;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void rotate() {
		// @TODO Rotate the block 90 deg. clockwise.
	}
	
	public int height() {
		return shape.length;
	}
	
	public int width() {
		if(shape.length > 0)
			return shape[0].length;
		return 0;
	}
}
