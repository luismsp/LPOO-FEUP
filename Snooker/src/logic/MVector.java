package logic;

public class MVector {

	private double x;
	private double y;
	
	
	
	// ------------
	// constructors
	// ------------
	
	public MVector() {
		this.x = 0;
		this.y = 0;
	}

	public MVector(double x, double y) {
		this.x = x;
		this.y = y; 
	}

	public MVector(MVector p) {
		this.x = p.getX();
		this.y = p.getY();
	}

	
	
	// -----------------
	// getters / setters
	// -----------------
	
	public double getX() { 	return x; }

	public void setX(double x) { this.x = x; }

	public double getY() { return y; }

	public void setY(double y) { this.y = y; }	
	
	public boolean isNull() { return x == 0 && y == 0; }

	
	// ----------
	// operations
	// ----------
	
	public double norm() { return Math.sqrt(x*x + y*y); }
		
	//public static MathVector add(MathVector v1, MathVector v2) { return new MathVector(v1.x+v2.x,v1.y+v2.y); }
	
	public void add(MVector v1) { x += v1.x; y += v1.y; }
	
	//public static Vector2d add(Vector2d v1, Vector2d v2) { return new Vector2d(v1.x+v2.x,v1.y+v2.y); }
	
	public static MVector subtract(MVector v1, MVector v2) { return new MVector(v1.x-v2.x,v1.y-v2.y); }
	
	//public static MathVector multiply(MathVector v1, MathVector v2) { return new MathVector(v1.x*v2.x,v1.y*v2.y); }
	
	public static MVector multiply(MVector v1, double n) { return new MVector(v1.x*n,v1.y*n); }
	
	public static MVector invertXY(MVector v1) { return new MVector(-v1.x,-v1.y); }
	
	public static MVector invertX(MVector v1) { return new MVector(-v1.x,v1.y); }
	
	public static MVector invertY(MVector v1) { return new MVector(v1.x,-v1.y); }
	
	public void multiply(double n) { x *= n; y *= n; }
	
	public static double dotProduct(MVector v1, MVector v2) { return (v1.x*v2.x + v1.y*v2.y); }
	
	public static double cos(MVector v1, MVector v2) { return dotProduct(v1,v2) / (v1.norm()*v2.norm()); }
	
	public static double angle(MVector v1, MVector v2) { return Math.acos(cos(v1,v2)); }
	
	public static double sin(MVector v1, MVector v2) { return Math.sin(angle(v1,v2)); }
}
