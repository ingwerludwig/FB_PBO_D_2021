package com.util;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
	private static Timer timer;
	private static int interval;
	static int seconds;
	
	private int delay = 500;
	private int period = 1000;
	
	public CountdownTimer(int intervalInput, int secondsInput) {
		interval = intervalInput;
		seconds = secondsInput;
		timer = new Timer();
		
	    timer.scheduleAtFixedRate(new TimerTask() {

	        public void run() {
	            setInterval();

	        }
	    }, delay, period);
	}
	
	private static final int setInterval() {
	    if (interval == 1)
	        timer.cancel();
	    return --interval;
	}
	
	public void stopTime() {
		timer.cancel();
	}
	
	public int getInterval() {
		return interval;
	}
}

