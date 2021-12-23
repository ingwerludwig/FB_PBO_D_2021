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
import javax.swing.JPanel;
import javax.imageio.ImageIO;

public class HomePanel extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;

	//bagian area
	private BufferedImage image;
	private JButton startButton;
	private JPanel contentPane;
	private GamePanel gamePanel;
	Font myFont = new Font("Retro Gaming", Font.BOLD, 16);
	
	public HomePanel(JPanel panel){
        contentPane = panel;
        setOpaque(true);
        try {                
			image = ImageIO.read(new File("src/UI/images/background.jpeg"));
		} catch (IOException ex) {

		}

        startButton = new JButton ("Start!");
        startButton.setFont(myFont);
        startButton.setFocusPainted(false);
        startButton.setLocation(0,100);
        startButton.setBounds(450,550,200,50);
        startButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	gamePanel = new GamePanel(contentPane);
            	contentPane.add(gamePanel, "Panel 2");
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.next(contentPane);
            }
        });
        add(startButton);
    }
	
	@Override
    public Dimension getPreferredSize(){
        return (new Dimension(1080,720));
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
