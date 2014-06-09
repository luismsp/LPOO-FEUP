package logic;

import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

public class Player {
	
	private String name;
	private int score;
	private List<Integer> validBalls = new ArrayList<Integer>();
	private boolean lastBallWasRed = false;
	private Ball ballPotted = null;
	
	
	// -----------
	// constructor
	// -----------
	
	public Player(String name) {
		validBalls.add(1);
		this.name = name;
		this.score = 0;
	}
	
		
	
	// -----------------
	// getters / setters
	// -----------------
	
	public List<Integer> getValidBalls() { return validBalls; }

	public void setValidBalls(List<Integer> validBalls) { this.validBalls = validBalls; }

	public void setName(String name) { this.name = name; }

	public void setScore(int score) { this.score = score; }
	
	public int getScore() { return score; }
	
	public String getName() { return name; }
		
	public boolean getLastBallWasRed() { return lastBallWasRed; }

	public void setLastBallWasRed(boolean lastBallWasRed) { this.lastBallWasRed = lastBallWasRed; }
	
	public Ball getBallPotted() { return ballPotted; }

	public void setBallPotted(Ball ballPotted) { this.ballPotted = ballPotted; }
	
	
	
	// -----
	// other
	// -----
	

	public void updateScore(int score) {
		this.score += score;
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
