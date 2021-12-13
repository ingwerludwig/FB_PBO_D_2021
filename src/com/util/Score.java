package com.util;

//class untuk menghitung score pemain
public class Score {
	private int score;
	
	public Score() {
		this.score = 0;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void increaseScore() {
		this.score++;
		System.out.println("Score : " + getScore());
	}
}
