package com.panel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.util.Score;

import javax.imageio.ImageIO;

public class EndPanel extends JPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//bagian area
	private BufferedImage image;
	private JButton PlayAgainButton;
    private JPanel contentPane;
    private JLabel highScoreLabel;
    Font myFont = new Font("Retro Gaming", Font.BOLD, 16);
    Font myFont1 = new Font("Retro Gaming", Font.BOLD, 28);
    
    //static class buat ngeliat score
    //dibuat static biar class GamePanel bisa meng akses class ini dengan gampang
	static Score score = new Score();
    
    public EndPanel(JPanel panel){  
    	contentPane = panel;
        setOpaque(true);
        try {                
			image = ImageIO.read(new File("src/UI/images/gameover.jpeg"));
		} catch (IOException ex) {

		}
        setLayout(null);
        PlayAgainButton = new JButton ("Try Again");
        PlayAgainButton.setFont(myFont);
        PlayAgainButton.setFocusPainted(false);
        PlayAgainButton.setLocation(0,100);
        PlayAgainButton.setBounds(430,570,200,50);
        PlayAgainButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {         
            	GamePanel gp = new GamePanel(contentPane);
            	contentPane.add(gp, "Game Panel");
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.next(contentPane);
            }
        });
      
        add(PlayAgainButton);
    }
    
    public void showHighScore() {
    	highScoreLabel = new JLabel("Your High Score is " + score.getHighScore());
		highScoreLabel.setBounds(360, 120, 500, 30);
		highScoreLabel.setForeground(Color.WHITE);
		highScoreLabel.setFont(myFont1);
		add(highScoreLabel);
    }
	
    public Dimension getPreferredSize(){
        return (new Dimension(1080, 720));
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
	
	@Override
	 protected void paintComponent(Graphics g) {
	   super.paintComponent(g);
	       g.drawImage(image, 0, 0, null);
	       showHighScore();
	}
	
	public class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			
		}
	}
	
}