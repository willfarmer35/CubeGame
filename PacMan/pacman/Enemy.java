import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends Rectangle {
	
	private int random = 0, smart =1, find_path=2;
	
	private int state = random;
	
	private int right =0,left =1,up=2,down = 3;
	
	private int dir = -1;
	
	public Random randomGen;
	
	private int time = 0;
	
	private int targetTime = 60*2;
	
	private int speed =4;
	
	private int lastdir = -1;
	
	private int count =1;
	public Enemy(int x, int y) {
		randomGen = new Random();
		setBounds(x,y,32,32);
		dir= randomGen.nextInt(4);
	}
	
	public void tick() {
		
		
		if(state == random) {
			if(dir == right) {
				if(canMove(x+speed,y)) {
					if(randomGen.nextInt(100)<95)
						x+=speed;
				}else {
					dir = randomGen.nextInt(4);
				}
			}else if(dir == left) {
				if(canMove(x-speed,y)) {
					if(randomGen.nextInt(100)<95)
					x-=speed;
				}else {
					dir = randomGen.nextInt(4);
				}
			}
			else if(dir == up) {
				if(canMove(x,y-speed)) {
					if(randomGen.nextInt(100)<95)
					y-=speed;
				}else {
					dir = randomGen.nextInt(4);
				}
			}
			else if(dir == down) {
				if(canMove(x,y+speed)) {
					if(randomGen.nextInt(100)<95)
					y+=speed;
				}else {
					dir = randomGen.nextInt(4);
				}
			}
			
		time++;
			if(time >= targetTime) {
				count++;
				time =0;
			}
	
				
			
		}else if(state == smart) {
			boolean move = false;
			
			if(x < GamePanel.player.x) {
				if(canMove(x+speed,y)) {
					if(randomGen.nextInt(100)<90)	x+=speed;
				
					move = true;
					lastdir = right;
				}
			}
			
			if(x > GamePanel.player.x) {
				if(canMove(x-speed,y)) {
					if(randomGen.nextInt(100)<90)	x-=speed;
				
					move = true;
					lastdir = left;
				}
			}
			
			if(y < GamePanel.player.y) {
				if(canMove(x,y+speed)) {
					if(randomGen.nextInt(100)<90)	y+=speed;
				
					move = true;
					lastdir = down;
				}
			}
			if(y > GamePanel.player.y) {
				if(canMove(x,y-speed)) {
					if(randomGen.nextInt(100)<90)	y-=speed;
				
					move = true;
					lastdir = up;
				}
			}
			if(x == GamePanel.player.x && y == GamePanel.player.y) 
				move = true;
			if(!move) {
				state = find_path;
			}
			
		}else if(state == find_path) {
				
			if(lastdir == right) {
					
					if(y < GamePanel.player.y) {
						if(canMove(x,y+speed)) {
							if(randomGen.nextInt(100)<90)y+=speed;
							
							state = smart;
						}
					}else {
						if(canMove(x,y-speed)) {
							if(randomGen.nextInt(100)<90)	y-=speed;
						
							state = smart;
						}
					}
					if(canMove(x+speed,y)) {
						if(randomGen.nextInt(100)<90)
						x+=speed;
					}
				}
				if(lastdir == left) {
					if(y < GamePanel.player.y) {
						if(canMove(x,y+speed)) {
							if(randomGen.nextInt(100)<90)y+=speed;
							
						}
					}else {
						if(canMove(x,y-speed)) {
							if(randomGen.nextInt(100)<90) y-=speed;
							
						}
					}
					if(canMove(x-speed,y)) {
						if(randomGen.nextInt(100)<90) x-=speed;
						
						
					}
				}
				if(lastdir == up) {
					
					if(x < GamePanel.player.x) {
						if(canMove(x-speed,y)) {
							if(randomGen.nextInt(100)<90)	x+=speed;
						
							state = smart;
						}
					}else {
						if(canMove(x-speed,y)) {
							if(randomGen.nextInt(100)<90) 	x-=speed;
						
							state = smart;
						}
					}
					if(canMove(x,y-speed)) {
						if(randomGen.nextInt(100)<90)
						y-=speed;
					}
				
				}
				
				if(lastdir == down) {
					if(x < GamePanel.player.x) {
						if(canMove(x-speed,y)) {
							if(randomGen.nextInt(100)<90)x+=speed;
						
							state = smart;
						}
					}else {
						if(canMove(x-speed,y)) {
							if(randomGen.nextInt(100)<90) x-=speed;
							
							state = smart;
						}
					}
					if(canMove(x,y+speed)) {
						if(randomGen.nextInt(100)<90)
						y+=speed;
					}
				}
		}
		
		time++;
		if(time >= targetTime) {
			count++;
			time =0;
		}
		if(count %2 ==0) {
			state = smart;
		}else {
			state = random;
		}

		
		}
	
	
	private boolean canMove(int nextx,int nexty) {
		
		Level level = GamePanel.level;
		Rectangle bounds = new Rectangle(nextx,nexty,width,height);
		for (int i =0; i <level.tiles.length;i++) {
			
			for(int j =0;j <level.tiles[0].length;j++) {
				if(level.tiles[i][j]!=null) {
						if(bounds.intersects(level.tiles[i][j])) {
							return false;
						}
				
					}
				
				}
				
		}
			
		return true;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}
