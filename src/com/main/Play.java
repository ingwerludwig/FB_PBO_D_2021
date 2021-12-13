package com.main;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.panel.GamePanel;

public class Play extends JFrame{
	static int width = 1080;
	static int height = 720;
	
	/**
	 * 
	 */
	//abaikan
	private static final long serialVersionUID = 1L;
	
	
	//semua component panel yang dibutuhkan untuk game ini
	static GamePanel gp = new GamePanel(width, height);
	
	//end component
	
	//Jpanel dan cardLayout 
	static JPanel cards = new JPanel();
	static CardLayout c = new CardLayout();
	//end 
	
	
	//constructor
	Play() {
		cards.setLayout(c);
		
		//tambahkan setiap component ke sini
		cards.add(gp, "GamePanel");
		
		//c.show() generate pertama kali yang dirender itu apa
		c.show(cards, "GamePanel");
		add(cards);
		
		setTitle("Stress Ball Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(width, height); 
        setVisible(true); 
	}
	
	public static void main(String[] args) {
		new Play();
	}
}
