package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	public static BufferedImage[] sprite_left, sprite_up, sprite_down, sprite_right;
	private int frames = 0,maxFrames = 5,index = 0,maxIndex = 1;
	private boolean moved = false;
	public int right_dir = 1,left_dir = -1, up_dir = 2, down_dir = -2;
	public int dir = right_dir;
	
	public double life = 1,maxlife = 1;
	public boolean isDamaged = false;
	private int damageFrames = 0;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		sprite_left = new BufferedImage[2];
		sprite_up = new BufferedImage[2];
		sprite_down = new BufferedImage[2];
		sprite_right = new BufferedImage[2];
		for(int i = 0; i < 2; i++) {
			sprite_left[i] = Game.spritesheet.getSprite(64 + (i*16), 0, 16, 16);
		}
		for(int i = 0; i < 2; i++) {
			sprite_up[i] = Game.spritesheet.getSprite(128 + (i*16) , 0, 16, 16);
		}
		for(int i = 0; i < 2; i++) {
			sprite_down[i] = Game.spritesheet.getSprite(96 + (i*16), 0, 16, 16);
		}
		for(int i = 0; i < 2; i++) {
			sprite_right[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 16, 16);
		}
	}
	
	public void tick(){
		depth = 2;
		moved = false;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			moved = true;
			x+=speed;
			dir = right_dir;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			moved = true;
			x-=speed;
			dir = left_dir;
		}else if(up && World.isFree(this.getX(),(int)(y-speed))){
			moved = true;
			y-=speed;
			dir = up_dir;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			moved = true;
			y+=speed;
			dir = down_dir;
		}
		verificaPegaPoint();
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
		}
		if(isDamaged) {
			this.damageFrames++;
			if(this.damageFrames == 8) {
				this.damageFrames = 0;
				isDamaged = false;
			}
		}
		
		if(Game.Point_atual == Game.Point_contagem || life <= 0) {
			World.restartGame();
		}
	}
	
	public void verificaPegaPoint(){
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity current = Game.entities.get(i);
			if(current instanceof Point) {
				if(Entity.isColidding(this, current)) {
					Game.Point_atual++;
					Game.entities.remove(i);
					return;
				}
			}
		}
	}

	public void render(Graphics g) {
			if(dir == right_dir) {
				g.drawImage(sprite_left[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
			}else if(dir == left_dir) {
				g.drawImage(sprite_right[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
			}
			if(dir == up_dir) {
				g.drawImage(sprite_up[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
			}else if(dir == down_dir) {
				g.drawImage(sprite_down[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
			}	
	}


}
