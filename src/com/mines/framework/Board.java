package com.mines.framework;

import java.util.Random;

import com.mines.main.Game;

public class Board {

	private Cell[][] board;
	private Random rand;
	private boolean minesPlaced = false;
	private int numMines;
	private Game game;
	
	public Board(Game game, GameInfo info) {
		this.game = game;
		board = new Cell[info.getWidth()][info.getHeight()];
		rand = new Random();
		numMines = info.getNumMines();
		generateBoard();
	}
	
	private void generateBoard() {
		for(int y = 0; y < getHeight(); y++) {
			for(int x = 0; x < getWidth(); x++) {
				if(board[x][y] == null) board[x][y] = new Cell(this, x, y, false);
			}
		}
		initCells();
	}
	
	private void initCells() {
		for(int y = 0; y < getHeight(); y++) {
			for(int x = 0; x < getWidth(); x++) {
				board[x][y].loadNeighbors();
				board[x][y].countSurrounding();
			}
		}
	}
	
	private void placeMines(int avoidX, int avoidY) {
		for(int i = 0; i < numMines; i++) {
			boolean placeMine = true;
			while(placeMine) {
				int x = rand.nextInt(getWidth());
				int y = rand.nextInt(getHeight());
				if(!board[x][y].isMine() && (Math.abs(x - avoidX) > 1 || Math.abs(y - avoidY) > 1)) {
					board[x][y].setMine(true);
					placeMine = false;
				}
			}
		}
		initCells();
		minesPlaced = true;
	}
	
	public void open(int x, int y) {
		if(!minesPlaced) {
			placeMines(x, y);
			initCells();
		}
		board[x][y].reveal();
		if(checkAllUncovered()) game.onTrueAllUncovered();
	}
	
	public void flag(int x, int y) { 
		if(board[x][y].isFlagged() && checkInBounds(x, y)) board[x][y].setFlagged(false);
		else if(checkInBounds(x, y) && getFlagsRemaining() > 0) board[x][y].setFlagged(true);
	}
	
	public void openAllMines() {
		for(int y = 0; y < getHeight(); y++) {
			for(int x = 0; x < getWidth(); x++) {
				if(board[x][y].isMine()) board[x][y].setKnown(true);
			}
		}
	}
	
	public void onMineClicked(int x, int y) {
		game.onMineClicked(x, y);
	}
	
	private boolean checkInBounds(int x, int y) {
		return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
	}
	
	private boolean checkAllUncovered() {
		boolean allUncovered = true;
		for(int y = 0; y < getHeight(); y++) {
			for(int x = 0; x < getWidth(); x++) {
				if(!board[x][y].isMine() && !board[x][y].isKnown()) {
					allUncovered = false;
					break;
				}
			}
		}
		return allUncovered;
	}
	
	public int getFlagsRemaining() {
		int flags = 0;
		for(int y = 0; y < getHeight(); y++) {
			for(int x = 0; x < getWidth(); x++) {
				if(board[x][y].isFlagged()) flags++;
			}
		}
		return numMines - flags;
	}
	
	public Cell get(int x, int y) {
		try {
			return board[x][y];
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public int getWidth() {
		return board.length;
	}
	
	public int getHeight() {
		return board[0].length;
	}
}
