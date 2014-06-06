package logic;

public class Ball {

	private final BallColor color;
	private final int value;
	private static double mass = 0.170;
	private static double inv_mass = 1/mass;
	private static double radius = 15;
	private MVector position = new MVector();
	private MVector velocity = new MVector();
	private MVector force = new MVector();
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

	public MVector getPosition() { return position; }

	public double getX() { return position.getX(); }

	public double getY() { return position.getY(); }

	public void setPosition(MVector position) { this.position = position; }

	public MVector getVelocity() { return velocity; }

	public void setVelocity(MVector velocity) { this.velocity = velocity; }

	public MVector getForce() { return force; }

	public void setForce(MVector force) { this.force = force; }

	public static double getMass() { return mass; }

	public static double getInvMass() { return inv_mass; }

	public static void setMass(double mass) { Ball.mass = mass; Ball.inv_mass = 1/mass; }

	public static double getRadius() { return radius; }

	public static void setRadius(double radius) { Ball.radius = radius; }

	public boolean isPotted() { return potted; }

	public void setPotted(boolean potted) { this.potted = potted; }

	public boolean isMoving() { return !velocity.isNull(); }
}