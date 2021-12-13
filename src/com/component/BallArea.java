package com.component;

import java.awt.Color;
import java.awt.Graphics;

public class BallArea {
	int minX, maxX;
	int minY, maxY;
	private Color colorFilled, colorBorder;
	
	public BallArea(int x, int y, int width, int height, Color colorFilled, Color colorBorder) {
		this.minX = x;
		this.minY = y;
		
		this.maxX = x + width - 1;
		this.maxY = y + height - 1;
		
		this.colorFilled = colorFilled;
		this.colorBorder = colorBorder;
	}
	
	public void set(int x, int y, int width, int height) {
		minX = x;
		minY = y;
		maxX = x + width - 1;
		maxY = y + height - 1;
	}
	
	public void draw(Graphics g) {
		g.setColor(this.colorFilled);
		
		g.fillRect(
				minX, 
				minY, 
				maxX - minX - 1, 
				maxY - minY - 1
		);
		
		g.setColor(colorBorder);
		
		g.drawRect(
				minX, 
				minY, 
				maxX - minX - 1, 
				maxY - minY - 1
		);
	}
}	


