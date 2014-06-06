package logic;

import java.util.Vector;

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
		
		table.getWhiteBall().setForce(forceToApply);
	}
	
	public void updatePhysics(float dt) {
		
		Vector<V2D> holes = table.getCloth().getHoles();
		Vector<Ball> balls = table.getBallSet();
		
		for (int i = 0; i < balls.size(); ++i) {
			
			Ball a = balls.get(i);
			
			Collisions.handleBorderCollision(a);
			Collisions.handlePotting(a,holes);

			for (int j = 0; j < balls.size(); ++j) {
				
				if (i <= j)
					continue;
				
				Ball b = balls.get(j);
				Collisions.handleBallCollision(a,b);
			}
		}
	}
}
