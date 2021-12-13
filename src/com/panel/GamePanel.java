package com.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

import com.component.Ball;
import com.component.BallArea;
import com.util.CountdownTimer;
import com.util.Score;


public class GamePanel extends JPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int timeThread = 500;
	private Score score;

	//refresh rate nya
	private static final int REFRESH_RATE = 120;
	
	//bagian ball
	private List<Ball> balls = new ArrayList<Ball>();
	private BallArea box;
	
	//bagian area
	private int areaWidth;
	private int areaHeight;	
	
	static CountdownTimer stopwatch;
	
	
	private List<Color> color = new ArrayList<Color>();
	private Random random = new Random();
	
	public GamePanel(int width, int height) {
		this.addKeyListener(this);
		this.setFocusable(true);
		
		this.areaWidth = width;
		this.areaHeight = height;
		
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		
		//countdown timer
		stopwatch = new CountdownTimer(10, 1000);
		
		box = new BallArea(0, 0, width, height, Color.WHITE, Color.BLACK);
		
		MouseHandler handler = new MouseHandler();
		addMouseListener(handler);
		
		score = new Score();
		
		color.add(new Color(247, 32, 32));
		color.add(new Color(30,144,255));
		color.add(new Color(255,255,0));
		color.add(new Color(127, 0, 255));
		color.add(new Color(255, 192, 203));
		color.add(new Color(0,0,0));
		
		//cara mendapatkan area latar belakang, jika framenya diresize
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				Dimension dlm = c.getSize();
				areaWidth = dlm.width;
				areaHeight = dlm.height;
				
				box.set(0, 0, width, height);
			}
		});
		
		startThread();
	}
	
	public int getAreaWidth() {
		return this.areaWidth;
	}
	
	public int getAreaHeight() {
		return this.areaHeight;
	}
	
	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					int radius = 100;
				
					int x = random.nextInt(getAreaWidth() - radius * 2) + radius + 10;
					int y = random.nextInt(getAreaHeight() - radius * 2) + radius + 10;
								
					int randomColor = random.nextInt(6);
					
					if(stopwatch.getInterval() == 1) {
						stopwatch.stopTime();
						
						//kalau waktu abis, exit
						System.exit(0);
					}
					
					else {				
						try {
							balls.add(new Ball(x, y, radius, color.get(randomColor)));
							Thread.sleep(timeThread);
						} catch (Exception e) {
							System.out.println("Error");
						}
					}					

					
					try {
						Thread.sleep(1000 / REFRESH_RATE);
					} 
					
					catch (InterruptedException ex) {
						Thread.currentThread().interrupt();  // set interrupt flag
				        System.out.println("Error occured (InterruptedException) in class BallPanel");
					}
					
					repaint();
				}
			}
		};
		gameThread.start();
	}
	

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		box.draw(g);
		
		for(Ball b : balls) {
			b.draw(g);
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {		

	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
//			balls.removeIf(ball -> ball.isInClickableArea(e.getX(), e.getY()));
			
			Iterator<Ball> itr = balls.iterator();
			
			try {
				while(itr.hasNext()) {
					Ball b = itr.next();
					
					if(b.isInClickableArea(e.getX(), e.getY())) {
						balls.remove(b);
						
						score.increaseScore();
					}
				}
			} catch (Exception err) {
//				System.out.println("Ball removed");
			}

			
		}
	}
	
	

	

}

