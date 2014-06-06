package logic;

import java.util.Vector;

public class Table {

	private Cloth cloth = null;
	private Vector<Ball> ballSet = new Vector <Ball>();
	private MoveState stateMove = MoveState.WAITING_HIT;

	//

	/*public void setBallSet(Vector<Ball> ballSet) {
		this.ballSet = ballSet;
	}*/

	public Table(double radius, double ballWeight, double friction, ColorL color, int holeRadius, double width, double height) {

		cloth = new Cloth(friction, color, width, height, holeRadius);

		// adding white ball to vector
		ballSet.addElement(new Ball(ColorL.WHITE, new MathVector(), ballWeight));

		// adding red balls to vector
		for(int i = 0; i < 15; i++)
			ballSet.addElement(new Ball(ColorL.RED, new MathVector(), ballWeight));
		
		// adding color balls
		ballSet.addElement(new Ball(ColorL.GREEN, new MathVector(), ballWeight));
		ballSet.addElement(new Ball(ColorL.BROWN, new MathVector(), ballWeight));
		ballSet.addElement(new Ball(ColorL.YELLOW, new MathVector(), ballWeight));
		ballSet.addElement(new Ball(ColorL.BLUE, new MathVector(), ballWeight));
		ballSet.addElement(new Ball(ColorL.PINK, new MathVector(), ballWeight));
		ballSet.addElement(new Ball(ColorL.BLACK, new MathVector(), ballWeight));
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
		return (int) getWhiteBall().getRadius();
	}
	
	public void setMoveState(MoveState newState) {
		stateMove = newState;
	}
	
	public MoveState getMoveState() {
		return stateMove;
	}
}