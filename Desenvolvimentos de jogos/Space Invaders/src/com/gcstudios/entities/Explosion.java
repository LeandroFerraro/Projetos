package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Explosion extends Entity{
	
	private int frames = 0;
	private int targetFrames = 10;
	private int mexAnimation = 4;
	private int curAnimation = 0;
	
	public BufferedImage[] explosionSprite = new BufferedImage[4];

	public Explosion(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		explosionSprite[0] = Game.spritesheet.getSprite(48,0,16,16);
		explosionSprite[1] = Game.spritesheet.getSprite(64,0,16,16);
		explosionSprite[2] = Game.spritesheet.getSprite(80,0,16,16);
		explosionSprite[3] = Game.spritesheet.getSprite(96,0,16,16);
	}

	public void tick() {
		frames++;
		if(frames == targetFrames) {
			frames = 0;
			curAnimation++;
			if(curAnimation == mexAnimation) {
				Game.entities.remove(this);
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(explosionSprite[curAnimation], this.getX(),this.getY(),null);
	}
}
