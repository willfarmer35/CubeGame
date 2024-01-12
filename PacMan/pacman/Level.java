import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Level {
	public int width=32;
	public int height=32;
	
	public List<Apple> apples;
	public List<Enemy> enemies;
	public List<Tile> walls;
	public Tile[][] tiles;
	int[][]mapArr = {
			  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			  {1,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,1},
			  {1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,0,1},
			  {1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,0,1},
			  {1,2,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,3,1},
			  {1,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,0,1},
			  {1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,0,1},
			  {1,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,1},
			  {1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			  {1,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,1},
			  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			  {1,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			  {1,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,1},
			  {1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			  {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1},
			  {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1},
			  {1,0,0,0,0,0,0,0,1,0,0,0,3,0,0,0,1,0,0,1},
			  {1,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,1,0,0,1},
			  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
			 
			
			  };	
	
	public Level() {
	walls = new ArrayList<>();
	apples = new ArrayList<>();
	enemies = new ArrayList<>();
	tiles = new Tile[width][height];
	
	for(int i=0;i< mapArr.length;i++) {
		for(int j=0;j <mapArr[i].length;j++) {
			if(mapArr[i][j]==1) {
			tiles[i][j]= new Tile(j*32,i*32);
			
			}
			if(mapArr[i][j]==0) {
				apples.add(new Apple(j*32,i*32));
				
				}
			if(mapArr[i][j]==3) {
				enemies.add(new Enemy(j*32,i*32));
				
				}
			
			
			
		}
	}
		
		
	

	
	}
	
	public void tick() {
		for(int i = 0; i < enemies.size();i++) {
			enemies.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		
		for(int i=0;i< mapArr.length;i++) {
			for(int j=0;j <mapArr[i].length;j++) {
				if(tiles[i][j] !=null)
				tiles[i][j].render(g);
				
			
				
				
				
			}
		}
		for(int i = 0; i < apples.size();i++) {
			apples.get(i).render(g);
		}
	
		for(int i = 0; i < enemies.size();i++) {
			enemies.get(i).render(g);
		}
	}
}
