package com.component;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	int x, y;
	int radius;
	private Color color;
	private String colorName;

	public Ball(int x, int y, int radius, Color color, String colorName) {
		this.x = x;
		this.y = y;
		
		this.radius = radius;
		this.color = color;
		
		this.colorName = colorName;
		
	}
	
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
	}

	public boolean isInClickableArea(int eventX, int eventY) {
		
		boolean isInProgram = eventX >= 0 && eventY >= 0;
		
		if(isInProgram) {
			int pointX = Math.abs(this.x - eventX);
			int pointY = Math.abs(this.y - eventY);
			
			boolean isInArea = Math.pow(pointX, 2) + Math.pow(pointY, 2) <= Math.pow(this.radius, 2);
			
			if(isInArea) {
				return true;
			}
			

		}

		return false;
	}
	
	public String getColorName() {
		return this.colorName;
	}
	
}