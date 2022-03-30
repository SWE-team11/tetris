package team11.tetris.blocks;

import team11.tetris.utills.BoardElement;

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
	
	public BoardElement getShape(int x, int y) {
		return shape[y][x];
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
