import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {
	public boolean up,down,left,right;
	private int speed = 16;
	public boolean gameOver;
	public int score;
	
	public Player(int x, int y) {
		setBounds(32,128,32,32);
	}
	public void tick() {
		if(right && canMove(x+speed,y))x+=speed;
	
		if(left && canMove(x-speed,y))x-=speed;
		
		if(up && canMove(x,y-speed))y-=speed;
		
		if(down && canMove(x,y+speed))y+=speed;
		Level level = GamePanel.level;
		
			//remove the apples if player touches them
			for(int i =0;i < level.apples.size();i++) {
				if(this.intersects(level.apples.get(i))) {
					level.apples.remove(i);
					score+=10;
					break;
					
				}
			}
			
			
			//Game Over if player touches enemy
		
	}
	//collision detection code
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
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
	

		for(int i =0;i< GamePanel.level.enemies.size();i++) {
			Enemy em = GamePanel.level.enemies.get(i);
			if(em.intersects(this)) {
				GamePanel.gameOver(g);
				gameOver = true;
			}
		}
		
		
		//if you get all the apples, level passed screen
	
		if(GamePanel.level.apples.size()==0) {
			

			GamePanel gamePanel = new GamePanel();
			
			gamePanel.nextLevel(g);
		}
		
	
	}
}
	

