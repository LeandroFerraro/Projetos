package com.gcstudios.main;

import com.gcstudios.entities.Enemy;
import com.gcstudios.entities.Entity;

public class EnemySpawn {

	private int targetTime = 60*2;
	private int curTime = 0;
	
	public void tick() {
		curTime++;
		if(curTime == targetTime) {
			curTime = 0;
			targetTime = Entity.rand.nextInt(100);
			int yy = 0;
			int xx = Entity.rand.nextInt(Game.WIDTH-16);
			int spawn = Entity.rand.nextInt(2);
			if(spawn == 0) {
				Enemy enemy1 = new Enemy(xx,yy,16,16,Entity.rand.nextInt(3-1)+1,Game.spritesheet.getSprite(16, 0, 16, 16));
				Game.entities.add(enemy1);
			}
			if(spawn == 1) {
				Enemy enemy2 = new Enemy(xx,yy,16,16,Entity.rand.nextInt(3-1)+1,Game.spritesheet.getSprite(32, 0, 16, 16));
				Game.entities.add(enemy2);
			}
		}
	}
}

