package com.mines.solver;

import java.util.ArrayList;

import com.mines.framework.Board;
import com.mines.framework.Cell;

public class SolverBoard {
	
	private Matrix currentMatrix;
	private ArrayList<Cell> variableCells;
	private boolean hasMatrix = true;
	
	public SolverBoard() { }
	
	public SolverBoard(Board board) {
		setBoard(board);
	}
	
	public void setBoard(Board board) {
		ArrayList<Cell> numberCells = new ArrayList<Cell>();
		for(int y = 0; y < board.getHeight(); y++) { 
			for(int x = 0; x < board.getWidth(); x++) {
				Cell cell = board.get(x, y); 
				if(isNumberCell(cell)) {
					numberCells.add(cell);
				}
			}
		}
		ArrayList<Cell> variableCells = new ArrayList<Cell>();
		for(int y = 0; y < board.getHeight(); y++) {
			for(int x = 0; x < board.getWidth(); x++) {
				Cell cell = board.get(x, y);
				if(isVariableCell(cell)) {
					variableCells.add(cell);
				}
			}
		}	
		if(numberCells.size() > 0 && variableCells.size() > 0) {
			hasMatrix = true;
			double[][] varMatrix = new double[numberCells.size()][variableCells.size()];
			double[] sumMatrix = new double[numberCells.size()];
			for(int row = 0; row < numberCells.size(); row++) {
				Cell numberCell = numberCells.get(row);
				for(int col = 0; col < variableCells.size(); col++) {
					Cell variableCell = variableCells.get(col);
					if(isNeighbor(variableCell, numberCell)) {
						varMatrix[row][col] = 1;
					}
					else varMatrix[row][col] = 0;
				}
				sumMatrix[row] = numberCell.getSurrounding() - countSurroundingFlags(numberCell);
			}
			currentMatrix = new Matrix(varMatrix, sumMatrix);
			this.variableCells = variableCells;
		}
		else hasMatrix = false;
	}
	
	private boolean isNeighbor(Cell a, Cell b) {
		Cell[] aNeighbors = a.getNeighbors();
		for(int i = 0; i < aNeighbors.length; i++) {
			if(aNeighbors[i] == b) return true;
		}
		return false;
	}
	
	private int countSurroundingFlags(Cell cell) {
		Cell[] neighbors = cell.getNeighbors();
		int count = 0;
		for(int i = 0; i < neighbors.length; i++) {
			if(neighbors[i].isFlagged()) count++;
		}
		return count;
	}
	
	private boolean isNumberCell(Cell cell) {
		if(!cell.isKnown()) return false;
		Cell[] neighbors = cell.getNeighbors();
		for(int i = 0; i < neighbors.length; i++) {
			if(!neighbors[i].isKnown() && !neighbors[i].isFlagged()) return true;
		}
		return false;
	}
	
	private boolean isVariableCell(Cell cell) {
		if(cell.isKnown() || cell.isFlagged()) return false;
		Cell[] neighbors = cell.getNeighbors();
		for(int i = 0; i < neighbors.length; i++) {
			if(isNumberCell(neighbors[i])) return true;
		}
		return false;
	}
	
	public Matrix getCurrentMatrix() {
		return currentMatrix;
	}
	
	public ArrayList<Cell> getVariableCells() {
		return variableCells;
	}
	
	public static void printArray(double[][] matrix) {
	    for (int row = 0; row < matrix.length; row++) {
	        for (int column = 0; column < matrix[row].length; column++) {
	            System.out.print(matrix[row][column] + " ");
	        }
	        System.out.println();
	    }
	}
	
	public boolean hasMatrix() {
		return hasMatrix;
	}
}
