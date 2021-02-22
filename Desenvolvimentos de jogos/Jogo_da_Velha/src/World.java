import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;



public class World {
		
	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	
		public World(String path) {
			try {
				BufferedImage map = ImageIO.read(getClass().getResource(path));
				int[] pixels = new int[map.getWidth() * map.getHeight()];
				WIDTH = map.getWidth();
				HEIGHT = map.getHeight();
				tiles = new Tile[map.getWidth() * map.getHeight()];
				map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());
				for(int xx = 0; xx < map.getWidth(); xx++){
					for(int yy = 0; yy < map.getHeight(); yy++){
						int pixelAtual = pixels[xx + (yy * map.getWidth())];
						if(pixelAtual == 0xFFA700FF) {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
						}
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		public void render(Graphics g){
			int xstart =  16;
			int ystart =  16;
			
			int xfinal = xstart + (Game.WIDTH >> 4);
			int yfinal = ystart + (Game.HEIGHT >> 4);
			
			for(int xx = xstart; xx <= xfinal; xx++) {
				for(int yy = ystart; yy <= yfinal; yy++) {
					if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
						continue;
					Tile tile = tiles[xx + (yy*WIDTH)];
					tile.render(g);
				}
			}
		}
}
