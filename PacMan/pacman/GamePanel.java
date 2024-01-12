

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;



public class GamePanel extends JPanel implements ActionListener {
		
		static final int SCREEN_WIDTH = 640;
		static final int SCREEN_HEIGHT = 640;

		static final int DELAY = 75;
	
		public static Player player;	
		public static Level level;
		
		
		static boolean running = false;
		Random random;
		Timer timer;
	
		GamePanel(){
			random = new Random();
			this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
			this.setBackground(Color.black);
			this.setFocusable(true);
			this.addKeyListener(new MyKeyAdapter());
			player = new Player(GamePanel.WIDTH/2,GamePanel.HEIGHT/2);
			level = new Level();
			startGame();	
		
		}
		
	public void startGame() {
			running = true;
			timer = new Timer(DELAY,this);
			timer.start();
			
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			draw(g);
		}
		private void tick() {
			player.tick();
			level.tick();
		}
		
		
		public void draw(Graphics g) {
			if(running) {
				tick();	
				player.render(g);
				
				level.render(g);
				score(g);
				
			
				
				
			}else if(!running && player.gameOver == false) {
				nextLevel(g);
			}else{
				gameOver(g);
			}
		}
		
		
			


		
		

	
		
	
		
		public static void gameOver(Graphics g) {
			running = false;
			
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free",Font.BOLD,75));
		
			g.drawString("Game Over", 110, 100);
			g.setFont(new Font("Ink Free",Font.BOLD,30));
			g.drawString("Score :"+ player.score, 250, 200);
			
			g.setFont(new Font("Ink Free",Font.BOLD,25));
			g.drawString("Press Q to Reset", 200, 300);
		
		}
		public static void score(Graphics g) {
			
			
			g.setColor(Color.white);
			g.setFont(new Font("Ink Free",Font.BOLD,25));
		
			g.drawString("Score :"+ player.score, 250, 25);
		
		
		}
		
		public void nextLevel(Graphics g) {
			running = false;
		
			g.setColor(Color.cyan);
			g.setFont(new Font("Ink Free",Font.BOLD,75));
		
			g.drawString("Level Passed", 110, 100);
			
		
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(running) {
			
				
		
			
			}
			repaint();
		}
		public void restartGame() {
		        setVisible(false);
		        new GameFrame();
		    }
		
		public class MyKeyAdapter extends KeyAdapter{
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.left = true;
				
					
					break;
				case KeyEvent.VK_RIGHT:
					player.right = true;
			
					
					break;
				case KeyEvent.VK_UP:
					player.up = true;
		
					
					break;
				case KeyEvent.VK_DOWN:
					player.down=true;
				
					
					break;
				case KeyEvent.VK_Q:
					restartGame();
                    System.out.println("restart");
                  
					break;
				
					
				}
			}
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.left = false;
				
					
					break;
				case KeyEvent.VK_RIGHT:
					player.right = false;
			
					
					break;
				case KeyEvent.VK_UP:
					player.up = false;
		
					
					break;
				case KeyEvent.VK_DOWN:
					player.down=false;
				
					
					break;
			
				
					
				}
			}
		}
		
	
		
}