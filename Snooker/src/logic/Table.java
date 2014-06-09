package logic;

import java.util.Vector;

public class Table {

	private Cloth cloth;
	private Cue cue;
	private Vector<Ball> ballSet = new Vector <Ball>();

	//

	public Table(double radius, double friction, int holeRadius, double width, double height) {

		cloth = new Cloth(friction, width, height, holeRadius);

		// adding white ball to vector
		ballSet.addElement(new Ball(BallColor.WHITE));

		// adding red balls to vector
		int i = 0;
		for(; i < 15; i++) {
			ballSet.addElement(new Ball(BallColor.RED));
			//ballSet.get(i+1).setPotted(true);
		}
		
		// adding color balls
		ballSet.addElement(new Ball(BallColor.YELLOW));
		//ballSet.get(++i).setPotted(true);
		ballSet.addElement(new Ball(BallColor.GREEN));
		//ballSet.get(++i).setPotted(true);
		ballSet.addElement(new Ball(BallColor.BROWN));
		//ballSet.get(++i).setPotted(true);
		ballSet.addElement(new Ball(BallColor.BLUE));
		//ballSet.get(++i).setPotted(true);
		ballSet.addElement(new Ball(BallColor.PINK));
		//ballSet.get(++i).setPotted(true);
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
	
	public Cue getCue() {
		return cue;
	}
}