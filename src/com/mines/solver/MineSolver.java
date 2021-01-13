package com.mines.solver;

import com.mines.framework.Board;
import com.mines.framework.Cell;
import com.mines.main.Game;

public class MineSolver {

	public static final int COMPLETE = 0;
	public static final int PROGRESS = 1;
	public static final int ERROR = 2;
	
	private boolean firstTick = true;
	private boolean active = false;
	private boolean usedElimination = false;
	private int[] cellList;
	
	private Game game;
	private SolverBoard solverBoard;
	private MatrixReader reader;
	private Board board;
	
	public MineSolver(Game game) {
		this.game = game;
		solverBoard = new SolverBoard();
		reader = new MatrixReader();
		board = game.getBoard();  
	} 
	
	public void tick() {
		if(active) {
			System.out.println("Starting solver iteration");
			boolean conclusionsMade = false;
			if(firstTick) {
				firstTick = false;
				board.open(board.getWidth() / 2, board.getHeight() / 2);
			}
			solverBoard.setBoard(board);
			if(solverBoard.hasMatrix()) {
				Matrix boardMatrix = solverBoard.getCurrentMatrix();
				reader.setMatrix(boardMatrix);
				cellList = reader.getConclusions();
				if(containsInfo(cellList)) {
					clickCells(cellList, solverBoard);
					usedElimination = false;
					conclusionsMade = true;
				}
				else {
					boardMatrix.eliminate(true);
					usedElimination = true;
					cellList = reader.getConclusions();
					if(containsInfo(cellList)) {
						clickCells(cellList, solverBoard);
						conclusionsMade = true;
					}
				}
				if(!conclusionsMade) setActive(false);
			}
			else setActive(false);	
		}
	}
	
	private boolean containsInfo(int[] cellList) {
		for(int i = 0; i < cellList.length; i++) {
			if(cellList[i] != -1) return true;
		}
		return false;
	}
	
	private void clickCells(int[] cellList, SolverBoard solverBoard) {
		for(int i = 0; i < cellList.length; i++) {
			Cell cell = solverBoard.getVariableCells().get(i);
			if(cellList[i] == 1) flag(cell.getX(), cell.getY());
			else if(cellList[i] == 0) click(cell.getX(), cell.getY());
		}
	}
	
	private void click(int x, int y) {
		Board board = game.getBoard();
		board.open(x, y);
	}
	
	private void flag(int x, int y) {
		Board board = game.getBoard();
		if(!board.get(x, y).isFlagged()) board.flag(x, y);
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isFirstTick() {
		return firstTick;
	}

	public void setFirstTick(boolean firstTick) {
		this.firstTick = firstTick;
	}

	public boolean usedElimination() {
		return usedElimination;
	}
	
	public SolverBoard getSolverBoard() {
		return solverBoard;
	}
	
	public int[] getCellList() {
		return cellList;
	}
}
