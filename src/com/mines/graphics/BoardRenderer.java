package com.mines.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.mines.framework.Board;
import com.mines.main.Game;
import com.mines.main.Minesweeper;

public class BoardRenderer {

	public final int cellSize = 32;
	private final String winText = "YOU WIN!";
	private final int winTextLength = 140;
	private final int winTextYOffset = 30;
	private final Font winFont = new Font("Monospaced", Font.PLAIN, 30);
	private final Font coordFont = new Font("Monospaced", Font.PLAIN, 10);
	private final boolean showCoords = true;
	private final int coordsXOffset = 10;
	private final int coordsYOffset = 20;
	
	private Game game;
	private Minesweeper sweeper;
	private Board board;
	private CellRenderer cellRenderer;
	private int cornerX, cornerY;
	
	public BoardRenderer(Minesweeper sweeper, Game game) {
		board = game.getBoard();
		cornerX = (Minesweeper.WIDTH - board.getWidth() * cellSize) / 2;
		cornerY = (Minesweeper.HEIGHT - board.getHeight() * cellSize) / 2;
		cellRenderer = new CellRenderer(null, sweeper.getTexture());
		this.sweeper = sweeper;
		this.game = game;
	}
	
	public void render(Graphics g) {
		for(int y = 0; y < board.getHeight(); y++) {
			for(int x = 0; x < board.getWidth(); x++) {
				cellRenderer.setCell(board.get(x, y));
				cellRenderer.render(g, new Point(cornerX + x * cellSize, cornerY + y * cellSize), sweeper.getMouseInput());
			}
		}
		if(game.getState() == Game.WIN) {
			g.setFont(winFont);
			g.setColor(Color.BLACK);
			g.drawString(winText, cornerX + board.getWidth() * cellSize - winTextLength, cornerY - winTextYOffset);
		}
		if(showCoords) {
			g.setFont(coordFont);
			g.setColor(Color.gray);
			for(int x = 0; x < board.getWidth(); x++) {
				g.drawString(Integer.toString(x), x * cellSize + cornerX + coordsXOffset, cornerY - 4);
			}
			for(int y = 0; y < board.getHeight(); y++) {
				g.drawString(Integer.toString(y), cornerX - 20, y * cellSize + cornerY + coordsYOffset);
			}
		}
	}
	
	public Rectangle getBoardBounds() {
		return new Rectangle(cornerX, cornerY, board.getWidth() * cellSize, board.getHeight() * cellSize);
	}
	
	public Point getClickCoordinates(Point click) {
		click.translate(-cornerX, -cornerY);
		return new Point((int) click.getX() / cellSize, (int) click.getY() / cellSize);
	}
	
	public int getCornerX() {
		return cornerX;
	}
	
	public int getCornerY() {
		return cornerY;
	}
}
