package com.mines.framework;

public class GameInfo {

	private int width, height;
	private int numMines;
	
	public GameInfo(int width, int height, int numMines) {
		this.width = width;
		this.height = height;
		this.numMines = numMines;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getNumMines() {
		return numMines;
	}

	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}
}