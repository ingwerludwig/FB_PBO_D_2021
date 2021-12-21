package com.util;

//class untuk menghitung score pemain
public class Score {
	private int score;
	private int multiplier;
	private int highScore;
	
	//constructor
	public Score() {
		this.score = 0;
		this.multiplier = 1;
		this.highScore = -1;
	}
	
	//dapatkan score sekarang (atau untuk game over)
	public int getScore() {
		return this.score;
	}
	
	//saat ability dinyalakan, setMultiplier membuat score bertambah sebanyak m
	public void setMultiplier(int m) {
		this.multiplier = m;
	}
	
	//setiap bola diclick di GamePanel, increase score nya
	public void increaseScore() {
		this.score += multiplier;
	}
	
	//optional, untuk mengreset konfigurasi kembali ke awal
	public void resetScore() {
		this.multiplier = 1;
		this.score = 0;
	}
	
	public void setHighScoreBeforeReset(int s) {
		if(highScore == -1) {
			highScore = s;
		}
		
		else {
			if(highScore <= s) {
				highScore = s;
			}
		}
	}
	
	public int getHighScore() {
		return highScore;
	}
}
