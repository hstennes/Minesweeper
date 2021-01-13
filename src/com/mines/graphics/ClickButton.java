package com.mines.graphics;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.mines.main.Game;
import com.mines.main.Minesweeper;

public abstract class ClickButton {

	protected int width, height;
	protected int x, y;
	protected boolean pressed;
	
	protected Texture texture;
	protected Minesweeper sweeper;
	protected Game game;
	
	public ClickButton(Minesweeper sweeper, Game game) {
		texture = sweeper.getTexture();
		this.sweeper = sweeper;
		this.game = game;
	}
	
	protected abstract void buttonAction();
	
	public abstract void render(Graphics g);
	
	public void press() {
		pressed = true;
	}
	
	public void release(Point releaseLocation) {
		if(getBounds().contains(releaseLocation) && pressed) buttonAction();
		pressed = false;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
