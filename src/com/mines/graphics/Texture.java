package com.mines.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {

	private BufferedImage spritesheet;
	public BufferedImage[] sprites;
	
	public Texture() {
		spritesheet = loadImage("/spritesheet.png");
		sprites = new BufferedImage[11];
		getTextures();
	}
	
	private void getTextures() {
		sprites[0] = spritesheet.getSubimage(0, 0, 32, 32);
		sprites[1] = spritesheet.getSubimage(32, 0, 32, 32);
		sprites[2] = spritesheet.getSubimage(0, 32, 32, 32);
		sprites[3] = spritesheet.getSubimage(32, 32, 32, 32);
		sprites[4] = spritesheet.getSubimage(64, 0, 64, 64);
		sprites[5] = spritesheet.getSubimage(128, 0, 64, 64);
		sprites[6] = spritesheet.getSubimage(0, 64, 64, 64);
		sprites[7] = spritesheet.getSubimage(64, 64, 64, 64);
		sprites[8] = spritesheet.getSubimage(128, 64, 64, 64);
		sprites[9] = spritesheet.getSubimage(0, 128, 128, 64);
		sprites[10] = spritesheet.getSubimage(128, 128, 128, 64);
 	}
	
	public BufferedImage loadImage(String path){
		try {
			return ImageIO.read(getClass().getResource(path));
		} catch (IOException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}
}
