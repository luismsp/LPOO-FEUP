package logic;

import java.util.Vector;

public class Collisions {
	
	private static final double RESTITUTION_COEFF = 0.8;

	public static boolean ballsColliding(Ball a, Ball b) {
		
		double r = 2*Ball.getRadius();
		r *= r;
		
		double p1 = a.getX() - b.getX(); p1 *= p1;
		double p2 = a.getY() - b.getY(); p2 *= p2;
		
		return p1+p2 < r;
	}
	
	public static int handleBallCollision(Ball a, Ball b) {
		
		if (!ballsColliding(a,b) || !a.isMoving())
			return -1;
			
		
		System.out.println("COLLIDED!");
		
		// we need to know the inclination of the force vector in relation to the vector 
		// formed between the two radii to know the amount of force transmitted
		V2D radii_vector = new V2D(b.getPosition());
		radii_vector.subtract(a.getPosition());
		radii_vector.normalize();
		
		//System.out.println("radii vector between "+a.getValue()+" and "+b.getValue()+" : "+radii_vector);
		
		V2D fb = new V2D(radii_vector);
		fb.multiply(a.getForce().norm());
		fb.multiply(V2D.cos(a.getForce(),radii_vector));
		fb.multiply(RESTITUTION_COEFF);
		
		// new vector for Fa
		a.getForce().add(V2D.invertXY(fb));
		
		// add the force that b already had
		b.getForce().add(fb);
		
		
		System.out.println("A "+a.getForce());
		System.out.println("B "+b.getForce());
		
		return b.getValue();
	}
	
	public static void handleBorderCollision(Ball a, V2D initialClothPosition, V2D finalClothPosition) {

		// TODO: atribuir valores das coordenadas do pano
		// para definir correctamente os limites
		double uplimit = initialClothPosition.getY();
		double leftlimit = initialClothPosition.getX();
		double rightlimit = finalClothPosition.getX()+65;
		double downlimit = finalClothPosition.getY()-Ball.getRadius()-5;
		double r = Ball.getRadius();
		
		if (a.getY()-r < uplimit || a.getY()+r > downlimit)
			a.setForce(V2D.invertY(a.getForce()));
						
		else if (a.getX()-r < leftlimit || a.getX()+r > rightlimit)
			a.setForce(V2D.invertX(a.getForce()));
	
	}
	
	public static Ball handlePotting(Ball a, Vector<V2D> holes, boolean alreadyPotted) /* "collision with holes" */ {		
		
		Ball potted = null;
		
		double r = Ball.getRadius()+Cloth.getHoleRadius()-5;
		r *= r;

		for (V2D v : holes) {

			double p1 = a.getX() - v.getX(); p1 *= p1;
			double p2 = a.getY() - v.getY(); p2 *= p2;

			if (p1+p2 < r) {
				
				if (alreadyPotted)
					a.getForce().invertXY();
				
				else {
					a.setPotted(true);
					a.setForce(new V2D());
					potted = a;
					//System.out.println("potted!");
				}
				
				return potted;				
			}
		}
		
		return potted;
	}
	
}
