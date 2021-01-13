package com.mines.main;

import java.awt.Graphics;

import com.mines.framework.Board;
import com.mines.framework.FlagCounter;
import com.mines.framework.GameInfo;
import com.mines.graphics.BoardRenderer;
import com.mines.graphics.InfoPrinter;
import com.mines.graphics.ResetButton;
import com.mines.graphics.SolverButton;
import com.mines.solver.MineSolver;

public class Game {

	public static final int RUNNING = 0;
	public static final int WIN = 1;
	public static final int LOSS = 2;
	
	private int state;
	
	private ResetButton resetButton;
	private SolverButton solverButton;
	private Board board;
	private BoardRenderer renderer;
	private FlagCounter counter;
	private MineSolver solver;
	private InfoPrinter printer;
	
	public Game(Minesweeper sweeper, GameInfo info) {
		board = new Board(this, info);
		renderer = new BoardRenderer(sweeper, this);
		counter = new FlagCounter(sweeper, this);
		resetButton = new ResetButton(sweeper, this);
		solver = new MineSolver(this);
		solverButton = new SolverButton(sweeper, this); 
		printer = new InfoPrinter(this);
	}

	public void render(Graphics g) {
		renderer.render(g);
		counter.render(g);
		resetButton.render(g);
		solverButton.render(g);
		printer.render(g);
	}
	
	public void onMineClicked(int x, int y) {
		board.openAllMines();
		solver.setActive(false);
		printer.onMineClicked(x, y);
		state = LOSS;
	}
	
	public void onTrueAllUncovered() {
		state = WIN;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public BoardRenderer getBoardRenderer() { 
		return renderer;
	}
	
	public ResetButton getResetButton() {
		return resetButton;
	}
	
	public SolverButton getSolverButton() {
		return solverButton;
	}
	
	public MineSolver getSolver() {
		return solver;
	}
	
	public InfoPrinter getPrinter() {
		return printer;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
