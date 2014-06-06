package logic;

public class MathVector {

	private double x;
	private double y;
	
	//
	
	public MathVector() {
		this.x = 0;
		this.y = 0;
	}
	
	public MathVector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public MathVector(MathVector p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
