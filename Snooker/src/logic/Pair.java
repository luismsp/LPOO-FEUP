package logic;

public class Pair implements Comparable<Pair>{
	String name;
	int score;

	public Pair(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	@Override
	public int compareTo(Pair other) {
		if(this.score > other.score)
			return 1;
		else if(this.score == other.score) {
			if(this.name.compareTo(other.name) > -1)
				return 1;
			else
				return -1;
		}
		else
			return -1;
	}
}
