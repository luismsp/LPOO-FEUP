package logic;

import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

public class Player {
	private String name;
	private int score;
	private int ballToPot = 1;
	private List<Integer> validBalls = new ArrayList<Integer>();
	private boolean lastBallWasRed = false;
	
	//
	
	public Player(String name) {
		validBalls.add(1);
		this.name = name;
		this.score = 0;
	}
	
	public void setScored(int value) {
		score += value;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}
	
	public int getBallToPot() {
		return ballToPot;
	}

	public void setBallToPot(int ballToPot) {
		this.ballToPot = ballToPot;
	}
	
	public void updateScore(int score) {
		if (this.score - score < 0)
			score = 0;
		else
			this.score += score;
	}
	
	public boolean isLastBallWasRed() {
		return lastBallWasRed;
	}

	public void setLastBallWasRed(boolean lastBallWasRed) {
		this.lastBallWasRed = lastBallWasRed;
	}
	
	public void getNextBall(boolean areRedsOnTable, Vector<Ball> balls) {
		
		if (areRedsOnTable) {
			
			if (lastBallWasRed) {
				validBalls.clear();
				for (int i = 2; i < 8; ++i)
					validBalls.add(i);
				lastBallWasRed = false;
			}
			else {
				validBalls.clear();
				validBalls.add(1);
				lastBallWasRed = true;
			}
		}
		else {
			validBalls.clear();
			for (int i = 16; i < 22; ++i) {
				if (!balls.get(i).isPotted()) {
					validBalls.add(balls.get(i).getValue());
					return;
				}
			}
		}
	}

	
	
	
	
	
	
}
