package logic;

import java.util.Vector;

public class Cloth {

	private double friction;
	private BallColor color;
	private static int width;
	private static int height;
	private MVector initialPosition;
	private MVector finalPosition;
	private static int holeRadius;
	
	private Vector<MVector> coordHoles = new Vector<MVector>();
	
	public Cloth(double friction, BallColor color, double width, double height, int holeRadius) {
		this.friction = friction;
		this.color = color;
		Cloth.width = (int) width * 400;
		Cloth.height = (int) height * 400;
		Cloth.holeRadius = holeRadius;
		this.initialPosition = new MVector(92, 92);
		this.finalPosition = new MVector((width * 400) + initialPosition.getX(), (height * 400) + initialPosition.getY());
	}
	
	public double getFriction() {
		return friction;
	}
	
	public BallColor getColor() {
		return color;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getHoleRadius() {
		return holeRadius;
	}
	
	public Vector<MVector> getCoordHoles() {
		return coordHoles;
	}
	
	public void addHole(MVector coord) {
		coordHoles.addElement(coord);
	}
	
	public void setFriction(double newFriction) {
		this.friction = newFriction;
	}
	
	public void setColor(BallColor newColor) {
		this.color = newColor;
	}
	
	public MVector getInitialPosition() {
		return initialPosition;
	}
	
	public MVector getFinalPosition() {
		return finalPosition;
	}
}
