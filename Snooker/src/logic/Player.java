package logic;

public class Player {
	private String name;
	private int score;
	private Cue cue;
	
	public Player(String name, int MAX_CUE_OFFSET) {
		this.name = name;
		cue = new Cue(MAX_CUE_OFFSET);
		this.score = 0;
	}
	
	public void setScored(int value) {
		score += value;
	}
	
	public int getScore() {
		return score;
	}
	
	public Cue getCue() {
		return cue;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCueRotation() {
		return cue.getRotation();
	}
	
	public void setCueRotation(int newRotation) {
		cue.setRotation(newRotation);
	}
}
