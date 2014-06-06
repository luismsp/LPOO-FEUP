package logic;

import java.util.Vector;

public class Cloth {

	private double friction;
	private ColorL color;
	private static int width;
	private static int height;
	private MathVector initialPosition;
	private MathVector finalPosition;
	private static int holeRadius;
	
	private Vector<MathVector> coordHoles = new Vector<MathVector>();
	
	public Cloth(double friction, ColorL color, double width, double height, int holeRadius) {
		this.friction = friction;
		this.color = color;
		Cloth.width = (int) width * 400;
		Cloth.height = (int) height * 400;
		Cloth.holeRadius = holeRadius;
		this.initialPosition = new MathVector(92, 92);
		this.finalPosition = new MathVector((width * 400) + initialPosition.getX(), (height * 400) + initialPosition.getY());
	}
	
	public double getFriction() {
		return friction;
	}
	
	public ColorL getColor() {
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
	
	public Vector<MathVector> getCoordHoles() {
		return coordHoles;
	}
	
	public void addHole(MathVector coord) {
		coordHoles.addElement(coord);
	}
	
	public void setFriction(double newFriction) {
		this.friction = newFriction;
	}
	
	public void setColor(ColorL newColor) {
		this.color = newColor;
	}
	
	public MathVector getInitialPosition() {
		return initialPosition;
	}
	
	public MathVector getFinalPosition() {
		return finalPosition;
	}
}
