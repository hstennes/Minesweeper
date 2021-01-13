package com.mines.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import com.mines.framework.GameInfo;
import com.mines.framework.MouseInput;
import com.mines.graphics.Texture;

//Created by Hank Stennes
public class Minesweeper extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = (int) resolution.getWidth(), HEIGHT = (int) resolution.getHeight() - 100;
	private boolean running = false;
	private Thread thread;

	private Game game;
	private MouseInput mouseInput;
	private Texture texture;
	private GameInfo standardInfo;
	
	public Minesweeper() {
		standardInfo = new GameInfo(30, 16, 99); 
		texture = new Texture();
		game = new Game(this, standardInfo);
		mouseInput = new MouseInput(game);
		addMouseListener(mouseInput); 
		new Window(WIDTH, HEIGHT, "MINESWEEPER", this);
		start();
		System.out.println("Game started");
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void newGame() {
		game = new Game(this, standardInfo);
		mouseInput.setGame(game);
	}
	
	@Override
	public void run() {
		while(running) {
			render();
			delay(30);
		}	
		//MatrixTest.testMatrix();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		game.render(g);
		bs.show();
		g.dispose();
	}
	
	private void delay(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public MouseInput getMouseInput() {
		return mouseInput;
	}
	
	public static void main(String[] args) {
		new Minesweeper();
	}
}
