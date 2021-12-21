package com.panel;
import java.awt.Graphics;
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

        PlayAgainButton = new JButton ("Play Again!");
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
    
//	public EndPanel(int width, int height) {
//	//setBackground(Color.black);
//		
//	}
	
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
	}
	
	public class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			
		}
	}
	
}

