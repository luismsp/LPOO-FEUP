package logic;

public class Ball {

	private final ColorL color;
	private final int value;
	private MathVector position;
	private MathVector velocity = new MathVector();
	private boolean potted = false;
	private static double radius = 15;
	private double weight;
	
	//
	
	public Ball(ColorL color, MathVector position, double weight) {
		this.color = color;
		this.value = Utils.getValueFromColor(color);
		this.position = new MathVector(position);
		this.weight = weight;
	}
  
	public ColorL getColor() {
		return color;
	}
	
	public MathVector getPosition() {
		return position;
	}
	
	public void setPosition(MathVector newPosition) {
		this.position = newPosition;
	}
	
	public MathVector getVelocity() {
		return velocity;
	}
	
	public void setVelocity(MathVector newVelocity) {
		this.velocity = newVelocity;
	}
	
	public int getValue() {
		return value;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double newWeight) {
		this.weight = newWeight;
	}
	
	public boolean isPotted() {
		return potted;
	}
	
	public double getX() {
		return position.getX();
	}
	
	public double getY() {
		return position.getY();
	}
}