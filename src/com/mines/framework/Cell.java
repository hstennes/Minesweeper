package com.mines.framework;

public class Cell {

	private final int numNeighbors = 8;
	private int x, y;
	private int surrounding;
	private boolean known;
	private boolean flagged;
	private boolean mine;
	private Cell[] neighbors;
	
	private Board board;
	
	public Cell(Board board, int x, int y, boolean mine) {
		this.board = board;
		this.x = x;
		this.y = y;
		this.mine = mine;
		known = false;
		flagged = false;
		neighbors = new Cell[numNeighbors];
	}
	
	public void loadNeighbors() {
		neighbors[0] = board.get(x, y - 1);
		neighbors[1] = board.get(x + 1, y - 1);
		neighbors[2] = board.get(x + 1, y);
		neighbors[3] = board.get(x + 1, y + 1);
		neighbors[4] = board.get(x, y + 1);
		neighbors[5] = board.get(x - 1, y + 1);
		neighbors[6] = board.get(x - 1, y);
		neighbors[7] = board.get(x - 1, y - 1);
	}
	
	public void countSurrounding() {
		surrounding = 0;
		for(int i = 0; i < neighbors.length; i++) {
			if(neighbors[i] != null && neighbors[i].isMine()) {
				surrounding++;
			}
		}
	}
	
	public void reveal() {
		known = true;
		flagged = false;
		if(mine) {
			System.out.println("Mine clicked: (" + x + ", " + y + ")");
			board.onMineClicked(x, y);
		}
		if(surrounding == 0) {
			for(int i = 0; i < neighbors.length; i++) {
				if(neighbors[i] != null && !neighbors[i].isKnown()) neighbors[i].reveal();
			}
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getSurrounding() {
		return surrounding;
	}
	
	public int getUnknownNeighbors() {
		int count = 0;
		for(int i = 0; i <  neighbors.length; i++) {
			if(neighbors[i] != null  && !neighbors[i].isKnown()) count++;
		}
		return count;
	}
	
	public Cell[] getNeighbors() {
		int count = 0;
		for(int i = 0; i < neighbors.length; i++) {
			if(neighbors[i] != null) count++;
		}
		Cell[] noNullNeighbors = new Cell[count];
		int index = 0;
		for(int i = 0; i < neighbors.length; i++) {
			if(neighbors[i] != null) {
				noNullNeighbors[index] = neighbors[i];
				index++;
			}
		}
		return noNullNeighbors;
	}

	public boolean isKnown() {
		return known;
	}
	
	public void setKnown(boolean known) {
		this.known = known;
	}

	public boolean isFlagged() {
		if(known) flagged = false;
	    return flagged;
	}

	public void setFlagged(boolean flagged) {
		if(!mine && flagged) System.out.println("Mine (" + x + ", " + y + ") incorrectly flagged");
		this.flagged = flagged;
		if(known) this.flagged = false;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}
}
