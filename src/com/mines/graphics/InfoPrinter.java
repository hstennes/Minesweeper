package com.mines.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.mines.framework.Cell;
import com.mines.main.Game;
import com.mines.main.Minesweeper;
import com.mines.solver.MineSolver;

public class InfoPrinter {

	private final Font font = new Font("Monospaced", Font.PLAIN, 15);
	private final int xSpacing = 30;
	private final int lineSpacing = 20;
	private final int overflow = Minesweeper.HEIGHT - 100;
	private int x;
	private int y;
	private ArrayList<String> currentInfo;
	
	private MineSolver solver;
	private BoardRenderer renderer;
	
	public InfoPrinter(Game game) {
		solver = game.getSolver();
		y = (int) game.getBoardRenderer().getCornerY();
		x = xSpacing;
		currentInfo = new ArrayList<String>();
		renderer = game.getBoardRenderer();
	}
	
	public void updateInfo() {
		int[] cellList = solver.getCellList();
		currentInfo.clear();
		if(cellList != null) {
			if(solver.usedElimination()) currentInfo.add("Used elimination algorithm");
			else currentInfo.add("Used simple algorithm");
			for(int i = 0; i < cellList.length; i++) {
				Cell c = solver.getSolverBoard().getVariableCells().get(i);
				if(cellList[i] == 1)  currentInfo.add("Cell (" + c.getX() + ", " + c.getY() + ") has to be a mine.");
				else if(cellList[i] == 0) currentInfo.add("Cell (" + c.getX() + ", " + c.getY() + ") can't be a mine.");
			}
		}
		if(currentInfo.size() == 1) currentInfo.add("Looks like you have to guess!");
	}
	
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.black);
		Rectangle boardBounds = renderer.getBoardBounds();
		int boardWidth = (int) boardBounds.getWidth();
		int boardX = (int) boardBounds.getX();
		for(int i = 0; i < currentInfo.size(); i++) {
			if(y + lineSpacing * i > overflow) {
				g.drawString(currentInfo.get(i), boardX + boardWidth + xSpacing, (y + lineSpacing * i) - (overflow - y));
			}
			else {
				g.drawString(currentInfo.get(i), x, y + lineSpacing * i);
			}
		}
	}
	
	public void onMineClicked(int x, int y) {
		currentInfo.add("Mine clicked: (" + x + ", " + y + ")");
	}
}
