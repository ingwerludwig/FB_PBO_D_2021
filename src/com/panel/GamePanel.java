package com.panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.component.Ball;
import com.util.RoundedButton;
import com.util.Score;


public class GamePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	//constant value (value yang tidak akan berubah di dalam method ini)
	//radius = radius bola
	private final int RADIUS = 100;
	private final int width = 1080;
	private final int height = 720;
	private final String[] colorName = {"red", "blue", "yellow", "violet", "pink", "black"};
	
	//EndPanel
	private EndPanel endPanel;

	//variable untuk waktu
	private int elapsedTime = 500; // selang waktu dari suatu detik ke detik lainnya adalah 1 detik
	private int time = 60; // 60 detik
	private int abilityTime = 10;

	//balls = array berisi bola
	private List<Ball> balls;

	//score
	private boolean gameOver;
	private Score score;

	Timer timer, timeLeftTimer, countAbilityTimer;

	JLabel scoreLabel;
	JLabel timeLeft;
	JButton ability;

	private List<Color> color = new ArrayList<Color>();
	public Random random;

	private JButton startButton;
	private JPanel contentPane;
	//constructor
	public GamePanel(JPanel panel) {
		 contentPane = panel;
	        setOpaque(true);
	        startButton = new JButton ("Start!");
	        startButton.setLocation(0,100);
//	        startButton.setBounds(40,30,200,40);
	        startButton.addActionListener( new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
	                cardLayout.next(contentPane);
	            }
	        });
	        add(startButton);


		//setting untuk jpanel
		setLayout(null);
		setFocusable(true);
		
		random = new Random();

		//setting untuk tulisan score di layar program
		scoreLabel = new JLabel("Score : 0");
		scoreLabel.setBounds(10, 10, 200, 30);
		scoreLabel.setForeground(Color.BLACK);
		scoreLabel.setFont(new Font("Roboto", Font.BOLD, 20));
		add(scoreLabel);

		//setting untuk tulisan time di layar
		timeLeft = new JLabel("Time : " + time + " second");
		timeLeft.setBounds(870,10, 200, 50);
		timeLeft.setForeground(Color.BLACK);
		timeLeft.setFont(new Font("Roboto", Font.BOLD, 20));
		add(timeLeft);

		//setting button ability x2 di layar
		ability = new JButton("x2");
		ability.setBounds(15, 40, 50, 50);
		ability.setBackground(Color.decode("#B4B4B4"));
		ability.setForeground(Color.BLACK);
		ability.setBorder(new RoundedButton(15));
		ability.setVisible(true);
		add(ability);

		//setting untuk komponen bola
		//array bola
		balls = new ArrayList<Ball>();

		//warna untuk bola
		color.add(new Color(247, 32, 32)); //red
		color.add(new Color(30,144,255)); //blue
		color.add(new Color(255,204,0)); //yellow 
		color.add(new Color(127, 0, 255)); //violet
		color.add(new Color(255, 192, 203)); //pink
		color.add(new Color(0,0,0)); //black

		//mouse handler untuk meng click bolanya
		MouseHandler handler = new MouseHandler();
		addMouseListener(handler);

		//setting untuk gameover
		gameOver = false;

		//setting untuk score
		score = new Score();
		
		startGame();
	}
	
	public void startGame() {
		//setting untuk di in game bagian generate bola
		timer = new Timer(elapsedTime, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = random.nextInt(width - RADIUS * 2) + RADIUS + 20;
				int y = random.nextInt(height - RADIUS * 2) + RADIUS + 20;
				int randomColor = random.nextInt(6);
				
				balls.add(new Ball(x, y, RADIUS, color.get(randomColor), colorName[randomColor]));
				
				if(gameOver) {
					timeLeftTimer.stop();
				}
			}
		});

		//setting untuk di in game bagian time left 
		timeLeftTimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				time--;
				setTime(time);
				
				if(gameOver) {
					timeLeftTimer.stop();
				}
			}

		});

		//setting untuk ability di button nya
		ability.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				remove(ability);
				scoreLabel.setForeground(Color.RED);
				countAbilityTimer = new Timer(elapsedTime, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						score.setMultiplier(2);
						abilityTime--;

						if(abilityTime == 0) {
							countAbilityTimer.stop();
							scoreLabel.setForeground(Color.BLACK);
							score.setMultiplier(1);
						}
					}

				});

				countAbilityTimer.start();
			}


		});

		timeLeftTimer.start();
		timer.start();
	}
	
	@Override
    public Dimension getPreferredSize(){
        return (new Dimension(1080,720));
    }


	public void setScore(Score score) {
		scoreLabel.setText("Score : " + score.getScore());
	}

	public void setTime(int time) {
		timeLeft.setText("Time : " + time + " second");
	}
	
	public void resetConfiguration() {
		this.time = 60;
		this.abilityTime = 10;
		this.elapsedTime = 500;
		
        balls.removeAll(balls);
        EndPanel.score.setHighScoreBeforeReset(score.getScore());
        score.resetScore();
	}

	public void subtractElapsedTime(int time) {
		if(elapsedTime >= 200) {
			elapsedTime -= time;
			timer.setDelay(elapsedTime);
		}

	}

	public class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			Iterator<Ball> itr = balls.iterator();

			try {
				while(itr.hasNext()) {
					Ball b = itr.next();

					if(b.isInClickableArea(e.getX(), e.getY())) {
						String ballColorName = b.getColorName();
						if("black".equals(ballColorName)) {
							subtractElapsedTime(100);
							balls.remove(b);
						}

						else {
							balls.remove(b);
						}

						score.increaseScore();
						setScore(score);
					}
				}
			} catch (Exception err) {
//				System.out.println(elapsedTime);
			}


		}
	}

	public void checkIfGameIsOver() {
		//jika waktu habis / bola di layar lebih dari x, do something
		if(time == 0 || balls.size() > 30) {
			gameOver = true;
			
			endPanel = new EndPanel(contentPane);
			contentPane.add(endPanel, "Panel 3");
			
			CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.next(contentPane);
            
            resetConfiguration();
			//redirect ke panel game over di sini
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		checkIfGameIsOver();

		for(Ball b : balls) {
			b.draw(g);
		}

		if(!gameOver) {
			setFocusable(true);
			grabFocus();
		}

		repaint();
	}
}