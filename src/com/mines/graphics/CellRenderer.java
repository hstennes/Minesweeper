package com.mines.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.mines.framework.Cell;
import com.mines.framework.MouseInput;

public class CellRenderer {

	private Font font = new Font("Monospaced", Font.BOLD, 20);
	private Color bgColor = new Color(175, 175, 175);
	private Cell cell;
	private Texture texture;
	
	public CellRenderer(Cell cell, Texture texture) {
		this.cell = cell;
		this.texture = texture;
	}
	
	public void render(Graphics g, Point p, MouseInput mouseInput) {
		g.setFont(font);
		if(cell == mouseInput.getPressedCell() && !cell.isKnown()) {
			g.drawImage(texture.sprites[2], (int) p.getX(), (int) p.getY(), null);
		}
		else if(cell.isKnown()) {
			if(cell.isMine()) {
				g.drawImage(texture.sprites[3], (int) p.getX(), (int) p.getY(), null);
			}
			else {
				int surrounding = cell.getSurrounding();
				g.setColor(colorByNumber(surrounding));
				String text = Integer.toString(surrounding);
				Point textPoint = GraphicsUtil.centerText(g, text, new Rectangle((int) p.getX(), (int) p.getY(), 32, 32), font);
				g.drawString(text, (int) textPoint.getX(), (int) textPoint.getY()); 
			}
		}
		else {
		    if(cell.isFlagged()) g.drawImage(texture.sprites[1], (int) p.getX(), (int) p.getY(), null);
			else g.drawImage(texture.sprites[0], (int) p.getX(), (int) p.getY(), null);
		}
	}
	
	private Color colorByNumber(int surrounding) {
		switch(surrounding) {
		case 0: 
			return bgColor;
		case 1:
			return new Color(21, 0, 255);
		case 2:
			return new Color(14, 107, 2);
		case 3:
			return new Color(252, 0, 5);
		case 4:
			return new Color(9, 0, 104);
		case 5:
			return new Color(103, 0, 1);
		case 6:
			return new Color(36, 179, 227);
		default:
			return new Color(0, 0, 0);
		}
	}
	
	public void setCell(Cell cell) {
		this.cell = cell;
	}
}
