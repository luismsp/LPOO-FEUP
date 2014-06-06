package logic;

public class Game {
	
	private Table table;
	private Player p1;
	private Player p2;

	
	public Game(String player1Name, String player2Name) {
		this.table = new Table(20, 10, BallColor.BLACK, 20, 2.54, 1.27);;
		this.p1 = new Player(player1Name);
		this.p2 = new Player(player2Name);
	}
	
	public Table getTable() {
		return table;
	}
		
	public Cue getCue() {
		return table.getCue();
	}

	public void cueHit() {
		double forceMod = getCue().getOffset();
		V2D forceToApply = V2D.subtract(table.getWhiteBall().getPosition(),getCue().getPosition());
		forceToApply.normalize();
		forceToApply.multiply(forceMod);
		
		// TODO
		table.getWhiteBall().setForce(forceToApply);
		
		
		
	}
}
