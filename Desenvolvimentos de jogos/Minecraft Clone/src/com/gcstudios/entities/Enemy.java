package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.FloorTile;
import com.gcstudios.world.Tile;
import com.gcstudios.world.WallTile;
import com.gcstudios.world.World;

public class Enemy extends Entity{
	
	public boolean right = true,left = false;
	
	public int vida = 30;
	public int dir = 1;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		if(World.isFree((int)x,(int)(y+1))) {
			y+=1;
		}
		if(dir == 1) {
			if(World.isFree((int)(x+speed), (int)y)) {
				x+=speed;
			}else {
				int xnext = (int)((x+speed)/16) + 1;
				int ynext = (int)(y/16);
				if(World.tiles[xnext+ynext*World.WIDTH] instanceof WallTile && World.tiles[xnext+ynext*World.WIDTH].solid == false) {
					World.tiles[xnext+ynext*World.WIDTH] = new FloorTile((xnext)*16,ynext*16,Tile.TILE_AR);
				}
				dir = -1;
			}
		}else if(dir == -1) {
			if(World.isFree((int)(x-speed), (int)y)) {
				x-=speed;
			}else {
				int xnext = (int)((x-speed)/16);
				int ynext = (int)(y/16);
				if(World.tiles[xnext+ynext*World.WIDTH] instanceof WallTile && World.tiles[xnext+ynext*World.WIDTH].solid == false) {
					World.tiles[xnext+ynext*World.WIDTH] = new FloorTile((xnext)*16,ynext*16,Tile.TILE_AR);
				}
				dir = 1;
			}
		}
		if(vida == 0) {
			Game.entities.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		
		super.render(g);
	}

}
