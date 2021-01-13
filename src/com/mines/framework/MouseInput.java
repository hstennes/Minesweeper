package com.mines.framework;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import com.mines.graphics.BoardRenderer;
import com.mines.graphics.ResetButton;
import com.mines.graphics.SolverButton;
import com.mines.main.Game;

public class MouseInput extends MouseAdapter {

	private Game game;
	private Board board;
	private BoardRenderer renderer;
	private ResetButton resetButton;
	private SolverButton solverButton;
	private Cell pressedCell;
	
	public MouseInput(Game game) {
		setGame(game);
	}
	
	public void mousePressed(MouseEvent e) { 
		Point mouse = new Point(e.getX(), e.getY());
		if(renderer.getBoardBounds().contains(mouse) && game.getState() == Game.RUNNING) {
			Point cell = renderer.getClickCoordinates(mouse);
			if(SwingUtilities.isRightMouseButton(e)) board.flag((int) cell.getX(), (int) cell.getY());
			else if(!board.get((int) cell.getX(), (int) cell.getY()).isFlagged()) pressedCell = board.get((int) cell.getX(), (int) cell.getY());
		}
		else if(resetButton.getBounds().contains(mouse) && !SwingUtilities.isRightMouseButton(e)) resetButton.press();
		else if(solverButton.getBounds().contains(mouse) && !SwingUtilities.isRightMouseButton(e)) solverButton.press();
	}
	
	public void mouseReleased(MouseEvent e) {
		Point mouse = new Point(e.getX(), e.getY());
		if(renderer.getBoardBounds().contains(mouse) && game.getState() == Game.RUNNING) {
			Point cell = renderer.getClickCoordinates(mouse);
			if(!SwingUtilities.isRightMouseButton(e) && pressedCell != null && pressedCell.getX() == cell.getX() && pressedCell.getY() == cell.getY()) {
				board.open((int) cell.getX(), (int) cell.getY());
				game.getSolver().setFirstTick(false);
			}
		}
		pressedCell = null;
		resetButton.release(mouse);
		solverButton.release(mouse);
	}

	public void setGame(Game game) {
		this.game = game;
		board = game.getBoard();
		renderer = game.getBoardRenderer();
		resetButton = game.getResetButton();
		solverButton = game.getSolverButton();
	}
	
	public Cell getPressedCell() {
		return pressedCell;
	}
}
