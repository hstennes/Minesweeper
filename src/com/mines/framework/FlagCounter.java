package com.mines.framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.mines.graphics.BoardRenderer;
import com.mines.graphics.Texture;
import com.mines.main.Game;
import com.mines.main.Minesweeper;

public class FlagCounter {

	private final int yOffset1 = 60;
	private final int yOffset2 = 30;
	private final int xOffset = 34;
	private Font font = new Font("Monospaced", Font.PLAIN, 30);
	private Board board;
	private BoardRenderer renderer;
	private Texture texture;
	
	public FlagCounter(Minesweeper sweeper, Game game) {
		board = game.getBoard();
		renderer = game.getBoardRenderer();
		texture = sweeper.getTexture();
	}
	
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawImage(texture.sprites[1], (int) renderer.getCornerX(), (int) renderer.getCornerY() - yOffset1, null);
		g.drawString(": " + Integer.toString(board.getFlagsRemaining()), (int) renderer.getCornerX() + xOffset, (int) renderer.getCornerY() - yOffset2);
	}
	
}
