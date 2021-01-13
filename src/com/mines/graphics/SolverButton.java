package com.mines.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.mines.main.Game;
import com.mines.main.Minesweeper;
import com.mines.solver.MineSolver;

public class SolverButton extends ClickButton {
	
	private final String text = "AI";
	private final Font font = new Font("Monospaced", Font.PLAIN, 30);
	private final int yOffset = 32;
	
	private MineSolver solver;
	
	public SolverButton(Minesweeper sweeper, Game game) {
		super(sweeper, game);
		Rectangle boardBounds = game.getBoardRenderer().getBoardBounds();
		solver = game.getSolver();
		width = 128;
		height = 64;
		x = (int) (boardBounds.getX() + (boardBounds.getWidth() - width) / 2);
		y = (int) (boardBounds.getY() + boardBounds.getHeight() + yOffset);
	}

	@Override
	protected void buttonAction() {
		if(game.getState() == Game.RUNNING) {
			solver.setActive(true);
			solver.tick();
			solver.setActive(false);
			game.getPrinter().updateInfo();
		}
	}

	@Override
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.WHITE);
		if(pressed) g.drawImage(texture.sprites[10], x, y, null);
		else g.drawImage(texture.sprites[9], x, y, null);
		Point textPoint = GraphicsUtil.centerText(g, text, getBounds(), font);
		g.drawString(text, (int) textPoint.getX(), (int) textPoint.getY());
	}
}
