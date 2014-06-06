package logic;

import java.util.Vector;

public class Table {

	private Cloth cloth;
	private Cue cue;
	private Vector<Ball> ballSet = new Vector <Ball>();
	private MoveState stateMove = MoveState.WAITING_HIT;

	//

	/*public void setBallSet(Vector<Ball> ballSet) {
		this.ballSet = ballSet;
	}*/

	public Table(double radius, double friction, BallColor color, int holeRadius, double width, double height) {

		cloth = new Cloth(friction, color, width, height, holeRadius);

		// adding white ball to vector
		ballSet.addElement(new Ball(BallColor.WHITE));

		// adding red balls to vector
		for(int i = 0; i < 15; i++)
			ballSet.addElement(new Ball(BallColor.RED));
		
		// adding color balls
		ballSet.addElement(new Ball(BallColor.GREEN));
		ballSet.addElement(new Ball(BallColor.BROWN));
		ballSet.addElement(new Ball(BallColor.YELLOW));
		ballSet.addElement(new Ball(BallColor.BLUE));
		ballSet.addElement(new Ball(BallColor.PINK));
		ballSet.addElement(new Ball(BallColor.BLACK));
		
		cue = Cue.getInstance();
	}

	public Cloth getCloth() {
		return cloth;
	}

	public Vector<Ball> getBallSet() {
		return ballSet;
	}
	
	public Ball getWhiteBall() {
		return ballSet.get(0);
	}
	
	public int getBallsRadius() {
		return (int) Ball.getRadius();
	}
	
	public void setMoveState(MoveState newState) {
		stateMove = newState;
	}
	
	public MoveState getMoveState() {
		return stateMove;
	}

	public Cue getCue() {
		return cue;
	}
}