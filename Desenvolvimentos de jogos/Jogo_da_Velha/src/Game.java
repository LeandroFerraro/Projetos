import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 340;
	public static final int HEIGHT = 220;
	public static final int SCALE = 3;
	private Thread thread;
	private boolean isRunning = true;
	
	public static Spritesheet spritesheet;
	
	private BufferedImage Player;
	
	public static World world;
	
	public static JFrame frame;
	
	private BufferedImage image;
	
	public static List<Entity> entities;
	
	private int Frames = 0;
	private int mexFrames = 7;
	private int corAnimation = 0,mexAnimation = 3;
	private int h = 0; 
	public static int[] pixels;
	
	public Game() {
		
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		pixels =((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheet.png");
		world = new World("/mundo.png");
		
	}
	public void initFrame() {
		frame = new JFrame("Jogo da Velha");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		Game game = new Game();
		game.start();
	}
	
	public void Player() {
		Player = new BufferedImage(null, null, false, null);
		Player = Game.spritesheet.getSprite(32, 0, 16, 16);
	}
		
	 public void Tick(){
	    	h++;
	       Frames++;
	       if(Frames > mexFrames) {
	    	   Frames = 0;
	    	   corAnimation++;
	    	   if(corAnimation >= mexAnimation) {
	    		   corAnimation = 0;
	    		   
	    	   }
	       }
	    }
	 
	 public void Render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
			Graphics g = image.getGraphics();
			g.setColor(new Color(0,0,0));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			world.render(g);
			g.drawImage(image, this.getX(),this.getY(), null);
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.render(g);
			}
			g.dispose();
			g = bs.getDrawGraphics();
			g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
			bs.show();
	}
	
	public void run() {
		
	}

}
