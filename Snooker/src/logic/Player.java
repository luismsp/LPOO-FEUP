package logic;

public class Player {
	private String name;
	private int score;
	
	public Player(String name) {
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
}
