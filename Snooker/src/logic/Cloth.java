package logic;

import java.util.Vector;

public class Cloth {

	private double friction;
	private static int width;
	private static int height;
	private V2D initialPosition;
	private V2D finalPosition;
	private static int holeRadius;
	
	private Vector<V2D> holes = new Vector<V2D>();
	
	public Cloth(double friction, double width, double height, int holeRadius) {
		this.friction = friction;
		Cloth.width = (int) width * 400;
		Cloth.height = (int) height * 400;
		Cloth.holeRadius = holeRadius;
		this.initialPosition = new V2D(92, 92);
		this.finalPosition = new V2D((width * 400) + initialPosition.getX(), (height * 400) + initialPosition.getY());
	}
	
	public double getFriction() {
		return friction;
	}
		
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public static int getHoleRadius() {
		return holeRadius;
	}
	
	public Vector<V2D> getHoles() {
		return holes;
	}
	
	public void addHole(V2D coord) {
		holes.addElement(coord);
	}
	
	public void setFriction(double newFriction) {
		this.friction = newFriction;
	}
		
	public V2D getInitialPosition() {
		return initialPosition;
	}
	
	public V2D getFinalPosition() {
		return finalPosition;
	}
}
