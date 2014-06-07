package logic;

import java.util.Vector;

public class Game {

	private Table table;
	private Player p1;
	private Player p2;
	private GameState gameState = GameState.WAITING_FOR_HIT;


	// -----------
	// constructor
	// -----------

	public Game(String player1Name, String player2Name) {
		this.table = new Table(20, 0.99, BallColor.BLACK, 20, 2.54, 1.27);;
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

	public GameState getGameState() { return gameState; }

	public void setGameState(GameState gameState) { this.gameState = gameState; }



	// -----
	// other
	// -----

	public void cueHit() {

		Cue cue = getCue();
		double forceMod = cue.getOffset()*cue.getStrength()/Cue.getMaxOffset();

		//System.out.println("offset:  " + cue.getOffset());

		V2D forceToApply = new V2D(table.getWhiteBall().getPosition());
		forceToApply.subtract(getCue().getPosition());

		//System.out.println(table.getWhiteBall().getPosition() + "   " + getCue().getPosition());
		//V2D.invertY(forceToApply);
		forceToApply.normalize();

		//System.out.println("normalized " + forceToApply);
		forceToApply.multiply(forceMod);

		//System.out.println("DEBUG: " + forceToApply + " force mod " + forceMod);

		table.getWhiteBall().setForce(forceToApply);
	}

	public void updatePhysics(double dt, V2D initialClothPosition, V2D finalClothPosition) {

		// local variables
		Vector<Ball> balls = table.getBallSet();
		Vector<V2D> holes = table.getCloth().getHoles();
		double frictionMod = table.getCloth().getFriction();


		if (!allStopped()) {

			// handling collisions
			for (int i = 0; i < balls.size(); ++i) {

				Ball a = balls.get(i);
				if (a.isPotted())
					continue;

				Collisions.handleBorderCollision(a,initialClothPosition,finalClothPosition);
				Collisions.handlePotting(a,holes);


				for (int j = 0; j < balls.size(); ++j) {
					if (i >= j)
						continue;

					Ball b = balls.get(j);
					if (b.isPotted())
						continue;

					Collisions.handleBallCollision(a,b);
				}
			}
			
			// updating force, velocity and position
			for (Ball a : balls) {
				if (a.isPotted())
					continue;

				a.updateForce(frictionMod);
				a.updateVelocity();
				a.updatePosition(dt);
			}	
		}
		else 
			gameState = GameState.WAITING_FOR_HIT;
		
		System.out.println("fim updatephysics");
	}

	public boolean allStopped() {

		Vector<Ball> balls = table.getBallSet();

		for (Ball a : balls)
			if (a.isMoving())
				return false;

		return true;
	}

}
