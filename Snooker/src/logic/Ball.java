package logic;

public class Ball {

	private final BallColor color;
	private final int value;
	private static double mass = 0.170;
	private static double inv_mass = 1/mass;
	private static double radius = 15;
	private V2D position = new V2D();
	private V2D velocity = new V2D();
	private V2D force = new V2D();
	private boolean potted = false;


	// ------------
	// constructors
	// ------------

	public Ball(BallColor color) {
		this.color = color;
		this.value = BallColor.getValueFromColor(color);
	}

	
	// -----------------
	// getters / setters
	// -----------------

	public BallColor getColor() { return color; }
	
	public int getValue() { return value; }

	public V2D getPosition() { return position; }

	public double getX() { return position.getX(); }

	public double getY() { return position.getY(); }

	public void setPosition(V2D position) { this.position = position; }

	public V2D getVelocity() { return velocity; }

	public void setVelocity(V2D velocity) { this.velocity = velocity; }

	public V2D getForce() { return force; }

	public void setForce(V2D force) { this.force = force; }

	public static double getMass() { return mass; }

	public static double getInvMass() { return inv_mass; }

	public static void setMass(double mass) { Ball.mass = mass; Ball.inv_mass = 1/mass; }

	public static double getRadius() { return radius; }

	public static void setRadius(double radius) { Ball.radius = radius; }

	public boolean isPotted() { return potted; }

	public void setPotted(boolean potted) { this.potted = potted; }

	public boolean isMoving() { return !velocity.isNull(); }
}