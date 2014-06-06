package logic;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Collisions {
	
	private static final double RESTITUTION_COEFF = 0.8;

	public static boolean ballsColliding(Ball a, Ball b) {
		
		double r = 2*Ball.getRadius();
		r *= r;
		
		double p1 = a.getX() + b.getX(); p1 *= p1;
		double p2 = a.getY() + b.getY(); p2 *= p2;
		
		return r < p1+p2;
	}
	
	public static void handleBallCollision(Ball a, Ball b) {
		
		if (!ballsColliding(a,b))
			return;

		// we need to know the inclination of the force vector in relation to the vector 
		// formed between the two radii to know the amount of force transmitted
		V2D radii_vector = V2D.subtract(b.getPosition(),a.getPosition());

		V2D fb = V2D.multiply(a.getForce(), V2D.cos(a.getForce(),radii_vector));
		fb.multiply(RESTITUTION_COEFF);
		
		// new vector for Fa
		a.getForce().add(V2D.invertXY(fb));
		
		// add the force that b already had
		fb.add(b.getForce());

	}
	
	public static void handleBorderCollision(Ball a) {

		// TODO: atribuir valores das coordenadas do pano
		// para definir correctamente os limites
		int uplimit = 1;
		int leftlimit = 1;
		int rightlimit = 40;
		int downlimit = 40;
		double r = Ball.getRadius();
		
		if (a.getY()-r <= uplimit || a.getY()+r >= downlimit)
			a.setForce(V2D.invertY(a.getForce()));
						
		else if (a.getX()-r <= leftlimit || a.getX()+r >= rightlimit)
			a.setForce(V2D.invertX(a.getForce()));
	
	}
	
	public static void handlePotting(Vector<Ball> balls, Vector<V2D> holes) /* "collision with holes" */ {
		
		double r = Ball.getRadius()+Cloth.getHoleRadius();
		r *= r;
		
		for (Ball a : balls) {
			
			if (a.isPotted())
				continue;
			
			for (V2D v : holes) {
				
				double p1 = a.getX() + v.getX(); p1 *= p1;
				double p2 = a.getY() + v.getY(); p2 *= p2;
				
				if (r < p1+p2) {
					a.setPotted(true);
					a.setVelocity(new V2D());
				}
			}	
		}
	}
	
}


//public void run() {
//	final float fps = 100;
//	final float dt = 1 / fps;
//	float accumulator = 0;
//
//	
//	// In units seconds
//	float frameStart = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
//
//	// main loop
//	while(true) {
//		final float currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
//
//		// Store the time elapsed since the last frame began
//		accumulator += currentTime - frameStart;
//
//		// Record the starting of this frame
//		frameStart = currentTime;
//
//		// Avoid spiral of death and clamp dt, thus clamping
//		// how many times the UpdatePhysics can be called in
//		// a single game loop.
//		if(accumulator > 0.2f) 
//			accumulator = 0.2f;
//
//			while(accumulator > dt) {
//				updatePhysics(dt);
//				accumulator -= dt;
//			}
//
//		final float alpha = accumulator / dt;
//
//		renderGame(alpha);
//	}
//}
//
//public void renderGame(float alpha) {
//	/*for shape in game do
//		// calculate an interpolated transform for rendering
//		Transform i = shape.previous * alpha + shape.current * (1.0f - alpha)
//		shape.previous = shape.current
//		shape.Render( i );*/
//}
//
//public void updatePhysics(float dt) {
//	
//	// for every ball
//	// handleBallCollision(current_ball,next_ball);
//	
//	updateForce(dt);
//	updateVelocity(dt);
//	updatePosition(dt);
//}
//
//public void updateForce(float dt) {
//	// TODO F -= Fatrito;	
//}
//
//public void updateVelocity(float dt) {
//	// v += (1/m * F) * dt;
//	/*
//	 * MVector temp = MVector.multiply(inv_mass,force);
//	 * temp.multiply(dt);
//	 * velocity.add(temp)
//	 * 
//	 */1
//}
//
//public void updatePosition(float dt) {
//	// x += v * dt;
//}
