package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import logic.Ball;
import logic.Game;
import logic.MVector;
import logic.MoveState;

public class GamePanel extends JPanel implements ActionListener, Runnable,
		MouseListener, MouseMotionListener, KeyListener {
	private static final long serialVersionUID = 1L;

	private Image cue;
	
	private Timer timer;
	private static final int DESIRED_FPS = 50;

	boolean showGame = false;

	private Game game;

	private MVector initialWoodPosition;
	private MVector finalWoodPosition;
	private MVector initialClothPosition;
	private MVector finalClothPosition;
	private MVector buttomVertexTriangle;
	private MVector upVertexTriangle;
	private MVector mediumVertexTriangle;
	private MVector blueBallPoint;
	private MVector blackBallPoint;
	private MVector brownBallPoint;
	private MVector greenBallPoint;
	private MVector yellowBallPoint;
	private MVector pinkBallPoint;

	
	public void loadImage() throws IOException {
		cue = ImageIO.read(this.getClass().getResource("res/cue.png"));
	}
	
	public GamePanel() {
		setLocation((Utilities.dimScreen.width - getWidth()) / 2,
				(Utilities.dimScreen.height - getHeight()) / 2);
		
		game = new Game("Botas", "Luis");
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
		setLayout(null);
		setDoubleBuffered(true);
		requestFocus();
	}

	public void Init() throws IOException {
		loadImage();
		
		InitializeDrawingPoints();
		InitializeBallsPoints();
		timer = new Timer(1000 / DESIRED_FPS, (ActionListener) this);
		timer.start();
		setVisible(true);
	}

	public void InitializeBallsPoints() {
		double ballRadius = Ball.getRadius();

		// White Ball
		game.getTable().getBallSet()
				.get(0)
				.setPosition(
						new MVector(initialClothPosition.getX() + 200
								- ballRadius, blueBallPoint.getY() + 30));

		// starting red balls position
		int ballsPerColumns = 5;
		int indexBall = 1;
		double ballX;
		double ballY;
		for (int columns = 0; columns < 5; columns++) {
			ballX = (upVertexTriangle.getX() - 2 * ballRadius)
					- (columns * 2 * ballRadius);
			ballY = (upVertexTriangle.getY() + ballRadius + 3)
					+ (columns * ballRadius);

			for (int balls = 0; balls < ballsPerColumns; balls++) {
				game.getTable().getBallSet().get(indexBall)
						.setPosition(new MVector(ballX, ballY));
				ballY += 2 * ballRadius;
				indexBall++;
			}
			ballsPerColumns--;
		}

		// Color Balls
		// Green Ball
		game.getTable().getBallSet()
				.get(16)
				.setPosition(
						(new MVector(greenBallPoint.getX() - ballRadius,
								greenBallPoint.getY() - ballRadius)));
		// Brown Ball
		game.getTable().getBallSet()
				.get(17)
				.setPosition(
						(new MVector(brownBallPoint.getX() - ballRadius,
								brownBallPoint.getY() - ballRadius)));
		// Yellor Ball
		game.getTable().getBallSet()
				.get(18)
				.setPosition(
						(new MVector(yellowBallPoint.getX() - ballRadius,
								yellowBallPoint.getY() - ballRadius)));
		// Blue Ball
		game.getTable().getBallSet()
				.get(19)
				.setPosition(
						(new MVector(blueBallPoint.getX() - ballRadius,
								blueBallPoint.getY() - ballRadius)));
		// Pink Ball
		game.getTable().getBallSet()
				.get(20)
				.setPosition(
						new MVector(pinkBallPoint.getX() - ballRadius,
								pinkBallPoint.getY() - ballRadius));
		// Black Ball
		game.getTable().getBallSet()
				.get(21)
				.setPosition(
						(new MVector(blackBallPoint.getX() - ballRadius,
								blackBallPoint.getY() - ballRadius)));
	}

	public void InitializeDrawingPoints() {
		// Wood Points
		initialWoodPosition = new MVector(100, 20);
		finalWoodPosition = new MVector(game.getTable().getCloth().getFinalPosition()
				.getX() - 52, game.getTable().getCloth().getFinalPosition().getY() - 35);

		// Cloth Points
		initialClothPosition = new MVector(game.getTable().getCloth()
				.getInitialPosition().getX() + 40, game.getTable().getCloth()
				.getInitialPosition().getY() - 30);
		finalClothPosition = new MVector(game.getTable().getCloth().getFinalPosition()
				.getX()
				- game.getTable().getCloth().getInitialPosition().getX() - 25, game.getTable()
				.getCloth().getFinalPosition().getY()
				- game.getTable().getCloth().getInitialPosition().getY() - 25);

		// Triangle Vertexs
		buttomVertexTriangle = new MVector(game.getTable().getCloth()
				.getFinalPosition().getX() - 100, game.getTable().getCloth().getHeight());
		upVertexTriangle = new MVector(game.getTable().getCloth().getFinalPosition()
				.getX() - 100, game.getTable().getCloth().getHeight() / 3 + 80);
		mediumVertexTriangle = new MVector(game.getTable().getCloth().getWidth() + 40,
				game.getTable().getCloth().getHeight()
						- ((game.getTable().getCloth().getHeight() - (game.getTable().getCloth()
								.getHeight() / 3 + 80)) / 2));

		// Point on Center Table
		blueBallPoint = new MVector((finalClothPosition.getX() / 2)
				+ initialClothPosition.getX(), (finalClothPosition.getY() / 2)
				+ initialClothPosition.getY());

		// Point before triangle
		pinkBallPoint = new MVector(mediumVertexTriangle.getX() - 20,
				blueBallPoint.getY());

		// Point to Black Ball
		blackBallPoint = new MVector(finalClothPosition.getX() + 70,
				(finalClothPosition.getY() / 2) + initialClothPosition.getY());

		// Big Line Points
		brownBallPoint = new MVector(initialClothPosition.getX()
				+ (blueBallPoint.getX() - initialClothPosition.getX()) / 2,
				blueBallPoint.getY());
		greenBallPoint = new MVector(brownBallPoint.getX(),
				(brownBallPoint.getY() / 2) + initialWoodPosition.getY() + 10);
		yellowBallPoint = new MVector(brownBallPoint.getX(),
				brownBallPoint.getY()
						+ (greenBallPoint.getY() - initialClothPosition.getY()));

		// Cloth Holes
		// Left Up Hole
		game.getTable().getCloth().addHole(
				new MVector(initialClothPosition.getX()
						- game.getTable().getCloth().getHoleRadius(),
						initialClothPosition.getY()
								- game.getTable().getCloth().getHoleRadius()));
		// Left Buttom Hole
		game.getTable().getCloth().addHole(
				new MVector(initialClothPosition.getX()
						- game.getTable().getCloth().getHoleRadius(), finalClothPosition
						.getY() + game.getTable().getCloth().getHoleRadius() + 20));
		// Up Center Hole
		game.getTable().getCloth().addHole(
				new MVector(blueBallPoint.getX()
						- game.getTable().getCloth().getHoleRadius() + 2,
						initialClothPosition.getY()
								- game.getTable().getCloth().getHoleRadius() - 10));
		// Bottom Center Hole
		game.getTable().getCloth()
				.addHole(
						new MVector(
								blueBallPoint.getX()
										- game.getTable().getCloth().getHoleRadius() + 2,
								finalClothPosition.getY()
										+ ((finalWoodPosition.getY() - finalClothPosition
												.getY()) / 2)
										+ (30 - game.getTable().getCloth()
												.getHoleRadius())));
		// Right Up Hole
		game.getTable().getCloth()
				.addHole(
						new MVector(
								finalClothPosition.getX()
										+ 98
										- game.getTable().getCloth().getHoleRadius()
										+ (finalWoodPosition.getX() - finalClothPosition
												.getX()) / 2,
								initialClothPosition.getY()
										- game.getTable().getCloth().getHoleRadius()));
		// Right Buttom Hole
		game.getTable().getCloth()
				.addHole(
						new MVector(
								finalClothPosition.getX()
										+ 98
										- game.getTable().getCloth().getHoleRadius()
										+ (finalWoodPosition.getX() - finalClothPosition
												.getX()) / 2,
								finalClothPosition.getY()
										+ game.getTable().getCloth().getHoleRadius() + 20));

	}

	public void start() {
		(new Thread(this)).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (mouseDown) {
			game.getCue().updateOffset();
		}

		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Draw(g);
	}

	private void Draw(Graphics g) {
		DrawTable(g);
		DrawBalls(g, Ball.getRadius());
		DrawStick(g);
	}

	private void DrawTable(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		g.setColor(new Color(200, 100, 50).darker().darker());
		g.fill3DRect((int) initialWoodPosition.getX(),
				(int) initialWoodPosition.getY(),
				(int) finalWoodPosition.getX(), (int) finalWoodPosition.getY(),
				true);

		g.setColor(Color.GREEN.darker().darker());
		g.fill3DRect((int) initialClothPosition.getX(),
				(int) initialClothPosition.getY(),
				(int) finalClothPosition.getX(),
				(int) finalClothPosition.getY(), false);

		// Big Line Table
		g.setColor(Color.GRAY);
		g.drawLine((int) brownBallPoint.getX() + 4,
				(int) initialClothPosition.getY(),
				(int) brownBallPoint.getX() + 4,
				(int) finalClothPosition.getY() + 61);

		// Lateral line to triangle
		g.drawLine((int) upVertexTriangle.getX(),
				(int) upVertexTriangle.getY(),
				(int) buttomVertexTriangle.getX(),
				(int) buttomVertexTriangle.getY());

		// Buttom line to triangle
		g.drawLine((int) buttomVertexTriangle.getX(),
				(int) buttomVertexTriangle.getY(),
				(int) mediumVertexTriangle.getX(),
				(int) mediumVertexTriangle.getY());

		// Up line to triangle
		g.drawLine((int) mediumVertexTriangle.getX(),
				(int) mediumVertexTriangle.getY(),
				(int) upVertexTriangle.getX(), (int) upVertexTriangle.getY());

		// Drawing Points
		g.fillOval((int) blueBallPoint.getX(), (int) blueBallPoint.getY(), 8, 8);
		g.fillOval((int) blackBallPoint.getX(), (int) blackBallPoint.getY(), 8,
				8);
		g.fillOval((int) brownBallPoint.getX(), (int) brownBallPoint.getY(), 8,
				8);
		g.fillOval((int) greenBallPoint.getX(), (int) greenBallPoint.getY(), 8,
				8);
		g.fillOval((int) yellowBallPoint.getX(), (int) yellowBallPoint.getY(),
				8, 8);
		g.fillOval((int) pinkBallPoint.getX(), (int) pinkBallPoint.getY(), 8, 8);
		g.drawArc(
				(int) (2 * (greenBallPoint.getX() - (yellowBallPoint.getX() / 1.5))),
				(int) greenBallPoint.getY() + 4,
				(int) (yellowBallPoint.getX() / 1.5),
				(int) (yellowBallPoint.getY() - greenBallPoint.getY()), 90, 180);

		// Drawing borders points
		g.setColor(new Color(200, 100, 50).brighter().brighter());
		g.fillOval((int) (initialWoodPosition.getX() + (initialClothPosition
				.getX() - initialWoodPosition.getX()) / 2) - 4,
				(int) brownBallPoint.getY(), 8, 8);
		g.fillOval((int) (finalClothPosition.getX() + 112 + (finalWoodPosition
				.getX() - finalClothPosition.getX()) / 2), (int) brownBallPoint
				.getY(), 8, 8);
		g.fillOval(
				(int) brownBallPoint.getX(),
				(int) (initialWoodPosition.getY()
						+ ((initialClothPosition.getY() - initialWoodPosition
								.getY()) / 2) - 2), 8, 8);
		g.fillOval((int) brownBallPoint.getX(), (int) (finalClothPosition
				.getY() + ((finalWoodPosition.getY() - finalClothPosition
				.getY()) / 2)) + 40, 8, 8);
		g.fillOval(
				(int) (finalWoodPosition.getX() - (brownBallPoint.getX() - initialWoodPosition
						.getX())) + 85,
				(int) (initialWoodPosition.getY()
						+ ((initialClothPosition.getY() - initialWoodPosition
								.getY()) / 2) - 2), 8, 8);
		g.fillOval(
				(int) (finalWoodPosition.getX() - (brownBallPoint.getX() - initialWoodPosition
						.getX())) + 85,
				(int) (finalClothPosition.getY() + ((finalWoodPosition.getY() - finalClothPosition
						.getY()) / 2)) + 40, 8, 8);

		// Drawing Border holes
		g.setColor(Color.BLACK);
		g.fillArc((int) game.getTable().getCloth().getCoordHoles().get(0).getX() - 7,
				(int) game.getTable().getCloth().getCoordHoles().get(0).getY() - 7,
				2 * 27, 2 * 27, 0, 270);
		g.fillArc((int) game.getTable().getCloth().getCoordHoles().get(1).getX() - 7,
				(int) game.getTable().getCloth().getCoordHoles().get(1).getY() - 7,
				2 * 27, 2 * 27, 90, 270);
		g.fillArc((int) game.getTable().getCloth().getCoordHoles().get(2).getX() - 7,
				(int) game.getTable().getCloth().getCoordHoles().get(2).getY() - 10,
				2 * 27, 2 * 40, 0, 180);
		g.fillArc((int) game.getTable().getCloth().getCoordHoles().get(3).getX() - 7,
				(int) game.getTable().getCloth().getCoordHoles().get(3).getY() - 30,
				2 * 27, 2 * 40, 180, 180);
		g.fillArc((int) game.getTable().getCloth().getCoordHoles().get(4).getX() - 7,
				(int) game.getTable().getCloth().getCoordHoles().get(4).getY() - 7,
				2 * 27, 2 * 27, 270, 270);
		g.fillArc((int) game.getTable().getCloth().getCoordHoles().get(5).getX() - 7,
				(int) game.getTable().getCloth().getCoordHoles().get(5).getY() - 7,
				2 * 27, 2 * 27, 180, 270);

		// Drawing Holes
		g.setColor(new Color(122, 139, 139));
		for (int i = 0; i < game.getTable().getCloth().getCoordHoles().size(); i++)
			g.fillOval((int) game.getTable().getCloth().getCoordHoles().get(i).getX(),
					(int) game.getTable().getCloth().getCoordHoles().get(i).getY(),
					2 * game.getTable().getCloth().getHoleRadius(), 2 * game.getTable().getCloth()
							.getHoleRadius());

	}

	private void DrawStick(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		double rotRad, xCorrection, yCorrection;
		int cx = 0, cy = 0;

		Ball wb = game.getTable().getWhiteBall();
		MVector wbCoords = new MVector(wb.getX() + Ball.getRadius(),
				wb.getY() + Ball.getRadius());

		rotRad = Math.toRadians(game.getCue().getRotation());

		xCorrection = Math.cos(rotRad)
				* (2 * Ball.getRadius() + game.getCue().getCueOffset());
		yCorrection = Math.sin(rotRad)
				* (2 * Ball.getRadius() + game.getCue().getCueOffset());

		cx = (int) (wbCoords.getX());
		cy = (int) (wbCoords.getY() - cue.getHeight(null) / 2);

		g2d.translate(xCorrection, yCorrection);
		g2d.rotate(rotRad, wbCoords.getX(), wbCoords.getY());

		g2d.drawImage(cue, cx, cy, cue.getWidth(null), cue.getHeight(null),
				null);
	}

	private void DrawBalls(Graphics g, double radiusBall) {
		// TODO Auto-generated method stub
		for (int i = 0; i < game.getTable().getBallSet().size(); i++) {
			g.setColor(Utilities.ballsColors[game.getTable().getBallSet().get(i)
					.getColor().ordinal()]);
			g.fillOval((int) game.getTable().getBallSet().get(i).getX(), (int) game.getTable()
					.getBallSet().get(i).getY(), 2 * (int) radiusBall,
					2 * (int) radiusBall);
		}
	}

	public double getAlpha(double dx, double dy) {
		return Math.toDegrees(Math.atan(dy / dx));
	}

	public void calculateRotation(int mX, int mY) {
		Ball wb = game.getTable().getWhiteBall();
		int radiusBall = (int) Ball.getRadius();
		MVector wbC = new MVector(wb.getX() + radiusBall, wb.getY()
				+ radiusBall);

		double dx, dy, alpha;

		if (mX > wbC.getX()) {
			dx = mX - wbC.getX();

			if (mY < wbC.getY()) {
				dy = wbC.getY() - mY;
				alpha = getAlpha(dx, dy);
				game.getCue().setRotation((int) (180 - alpha));
			} else {
				dy = mY - wbC.getY();
				alpha = getAlpha(dx, dy);
				game.getCue().setRotation((int) (180 + alpha));
			}
		} else {
			dx = wbC.getX() - mX;

			if (mY < wbC.getY()) {
				dy = wbC.getY() - mY;
				alpha = getAlpha(dx, dy);
				game.getCue().setRotation((int) alpha);
			} else {
				dy = mY - wbC.getY();
				alpha = getAlpha(dx, dy);
				game.getCue().setRotation((int) (360 - alpha));
			}
		}
	}

	private boolean mouseDown = false;

	@Override
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			mouseDown = true;
			game.getTable().setMoveState(MoveState.START_HIT);
			break;
		/*
		 * case MouseEvent.BUTTON3: game.getTable().setMoveState(MoveState.WAITING_HIT);
		 * break;
		 */
		default:
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseDown = false;
		game.getTable().setMoveState(MoveState.WAITING_HIT);
		game.getCue().resetOffset();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (game.getTable().getMoveState() == MoveState.WAITING_HIT) {
			calculateRotation(e.getX(), e.getY());
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_D:
			game.getCue().incRotation();
			if (game.getCue().getRotation() > 360)
				game.getCue().setRotation(0);
			break;
		case KeyEvent.VK_A:
			game.getCue().decRotation();
			if (game.getCue().getRotation() < 0)
				game.getCue().setRotation(360);
			break;
		/*
		 * case KeyEvent.VK_S: game.getTable().getWhiteBall().setPosition(newPosition);
		 */}
		repaint();
		System.out.println("Cue alpha: " + game.getCue().getRotation());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
