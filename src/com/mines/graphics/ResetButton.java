package com.mines.graphics;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.mines.main.Game;
import com.mines.main.Minesweeper;

public class ResetButton extends ClickButton {

	private final int size = 64;
	private final int ySpacing = 16;
	
	public ResetButton(Minesweeper sweeper, Game game) {
		super(sweeper, game);
		width = size;
		height = size;
		BoardRenderer renderer = game.getBoardRenderer();
		Rectangle boardBounds = renderer.getBoardBounds();
		x = (int) ((boardBounds.getWidth() - size) / 2 + boardBounds.getX());
		y = (int) (boardBounds.getY() - size - ySpacing);
		texture = sweeper.getTexture();
	}
	
	@Override
	protected void buttonAction() {
		sweeper.newGame();
	}
	
	@Override
	public void render(Graphics g) {
		if(pressed) g.drawImage(texture.sprites[5], x, y, null);
		else g.drawImage(texture.sprites[4], x, y, null);
		
		if(game.getState() == Game.LOSS) g.drawImage(texture.sprites[8], x, y, null);
		else if(sweeper.getMouseInput().getPressedCell() != null) g.drawImage(texture.sprites[7], x, y, null);
		else g.drawImage(texture.sprites[6], x, y, null);
	}

	public void press() {
		pressed = true;
	}
	
	public void release(Point releaseLocation) {
		if(pressed && getBounds().contains(releaseLocation)) buttonAction();
		pressed = false;
	}
}
