package logic;

import java.util.Vector;

public class Game {
	
	private Table table;
	private Player p1;
	private Player p2;

	
	// -----------
	// constructor
	// -----------
	
	public Game(String player1Name, String player2Name) {
		this.table = new Table(20, 10, BallColor.BLACK, 20, 2.54, 1.27);;
		this.setP1(new Player(player1Name));
		this.setP2(new Player(player2Name));
	}
	
	
	
	// ---------------
	// getters / setters
	// ---------------
	
	public Player getP1() { return p1; }

	public void setP1(Player p1) { this.p1 = p1; }

	public Player getP2() { return p2; }

	public void setP2(Player p2) { this.p2 = p2; }
	
	public Table getTable() { return table; }
		
	public Cue getCue() { return table.getCue(); }

	
	
	// -----
	// other
	// -----
	
	public void cueHit() {
		double forceMod = getCue().getOffset();
		V2D forceToApply = V2D.subtract(table.getWhiteBall().getPosition(),getCue().getPosition());
		forceToApply.normalize();
		forceToApply.multiply(forceMod);
		
		table.getWhiteBall().setForce(forceToApply);
	}
	
	public void updatePhysics(float dt) {
		
		// local variables
		Vector<V2D> holes = table.getCloth().getHoles();
		Vector<Ball> balls = table.getBallSet();
		double frictionMod = table.getCloth().getFriction();
		
		
		// handling collisions
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
		
		// updating force, velocity and position
		for (Ball a : balls) {
			
			a.updateForce(dt, frictionMod);
			a.updateVelocity(dt);
			a.updatePosition(dt);
		}	
	}
	
	
}
