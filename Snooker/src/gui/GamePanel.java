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
import logic.ColorL;
import logic.MathVector;
import logic.MoveState;
import logic.Player;
import logic.Table;

public class GamePanel extends JPanel implements ActionListener, Runnable,
		MouseListener, MouseMotionListener, KeyListener {
	private static final long serialVersionUID = 1L;

	private Image cue;
	
	private Timer timer;
	private static final int DESIRED_FPS = 50;

	boolean showGame = false;

	private Table table;
	private Player[] players = new Player[2];
	private static int MAX_CUE_OFFSET;

	private MathVector initialWoodPosition;
	private MathVector finalWoodPosition;
	private MathVector initialClothPosition;
	private MathVector finalClothPosition;
	private MathVector buttomVertexTriangle;
	private MathVector upVertexTriangle;
	private MathVector mediumVertexTriangle;
	private MathVector blueBallPoint;
	private MathVector blackBallPoint;
	private MathVector brownBallPoint;
	private MathVector greenBallPoint;
	private MathVector yellowBallPoint;
	private MathVector pinkBallPoint;

	
	public void loadImage() throws IOException {
		cue = ImageIO.read(this.getClass().getResource("res/cue.png"));
	}
	
	public GamePanel() {
		setLocation((Utilities.dimScreen.width - getWidth()) / 2,
				(Utilities.dimScreen.height - getHeight()) / 2);
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
		table = new Table(20, 150, 10, ColorL.BLACK, 20, 2.54, 1.27);
		InitializeDrawingPoints();
		InitializeBallsPoints();
		MAX_CUE_OFFSET = (int) (6 * table.getWhiteBall().getRadius());
		players[0] = new Player("Botas", MAX_CUE_OFFSET);
		timer = new Timer(1000 / DESIRED_FPS, (ActionListener) this);
		timer.start();
		setVisible(true);
	}

	public void InitializeBallsPoints() {
		double ballRadius = table.getWhiteBall().getRadius();

		// White Ball
		table.getBallSet()
				.get(0)
				.setPosition(
						new MathVector(initialClothPosition.getX() + 200
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
				table.getBallSet().get(indexBall)
						.setPosition(new MathVector(ballX, ballY));
				ballY += 2 * ballRadius;
				indexBall++;
			}
			ballsPerColumns--;
		}

		// Color Balls
		// Green Ball
		table.getBallSet()
				.get(16)
				.setPosition(
						(new MathVector(greenBallPoint.getX() - ballRadius,
								greenBallPoint.getY() - ballRadius)));
		// Brown Ball
		table.getBallSet()
				.get(17)
				.setPosition(
						(new MathVector(brownBallPoint.getX() - ballRadius,
								brownBallPoint.getY() - ballRadius)));
		// Yellor Ball
		table.getBallSet()
				.get(18)
				.setPosition(
						(new MathVector(yellowBallPoint.getX() - ballRadius,
								yellowBallPoint.getY() - ballRadius)));
		// Blue Ball
		table.getBallSet()
				.get(19)
				.setPosition(
						(new MathVector(blueBallPoint.getX() - ballRadius,
								blueBallPoint.getY() - ballRadius)));
		// Pink Ball
		table.getBallSet()
				.get(20)
				.setPosition(
						new MathVector(pinkBallPoint.getX() - ballRadius,
								pinkBallPoint.getY() - ballRadius));
		// Black Ball
		table.getBallSet()
				.get(21)
				.setPosition(
						(new MathVector(blackBallPoint.getX() - ballRadius,
								blackBallPoint.getY() - ballRadius)));
	}

	public void InitializeDrawingPoints() {
		// Wood Points
		initialWoodPosition = new MathVector(100, 20);
		finalWoodPosition = new MathVector(table.getCloth().getFinalPosition()
				.getX() - 52, table.getCloth().getFinalPosition().getY() - 35);

		// Cloth Points
		initialClothPosition = new MathVector(table.getCloth()
				.getInitialPosition().getX() + 40, table.getCloth()
				.getInitialPosition().getY() - 30);
		finalClothPosition = new MathVector(table.getCloth().getFinalPosition()
				.getX()
				- table.getCloth().getInitialPosition().getX() - 25, table
				.getCloth().getFinalPosition().getY()
				- table.getCloth().getInitialPosition().getY() - 25);

		// Triangle Vertexs
		buttomVertexTriangle = new MathVector(table.getCloth()
				.getFinalPosition().getX() - 100, table.getCloth().getHeight());
		upVertexTriangle = new MathVector(table.getCloth().getFinalPosition()
				.getX() - 100, table.getCloth().getHeight() / 3 + 80);
		mediumVertexTriangle = new MathVector(table.getCloth().getWidth() + 40,
				table.getCloth().getHeight()
						- ((table.getCloth().getHeight() - (table.getCloth()
								.getHeight() / 3 + 80)) / 2));

		// Point on Center Table
		blueBallPoint = new MathVector((finalClothPosition.getX() / 2)
				+ initialClothPosition.getX(), (finalClothPosition.getY() / 2)
				+ initialClothPosition.getY());

		// Point before triangle
		pinkBallPoint = new MathVector(mediumVertexTriangle.getX() - 20,
				blueBallPoint.getY());

		// Point to Black Ball
		blackBallPoint = new MathVector(finalClothPosition.getX() + 70,
				(finalClothPosition.getY() / 2) + initialClothPosition.getY());

		// Big Line Points
		brownBallPoint = new MathVector(initialClothPosition.getX()
				+ (blueBallPoint.getX() - initialClothPosition.getX()) / 2,
				blueBallPoint.getY());
		greenBallPoint = new MathVector(brownBallPoint.getX(),
				(brownBallPoint.getY() / 2) + initialWoodPosition.getY() + 10);
		yellowBallPoint = new MathVector(brownBallPoint.getX(),
				brownBallPoint.getY()
						+ (greenBallPoint.getY() - initialClothPosition.getY()));

		// Cloth Holes
		// Left Up Hole
		table.getCloth().addHole(
				new MathVector(initialClothPosition.getX()
						- table.getCloth().getHoleRadius(),
						initialClothPosition.getY()
								- table.getCloth().getHoleRadius()));
		// Left Buttom Hole
		table.getCloth().addHole(
				new MathVector(initialClothPosition.getX()
						- table.getCloth().getHoleRadius(), finalClothPosition
						.getY() + table.getCloth().getHoleRadius() + 20));
		// Up Center Hole
		table.getCloth().addHole(
				new MathVector(blueBallPoint.getX()
						- table.getCloth().getHoleRadius() + 2,
						initialClothPosition.getY()
								- table.getCloth().getHoleRadius() - 10));
		// Buttom Center Hole
		table.getCloth()
				.addHole(
						new MathVector(
								blueBallPoint.getX()
										- table.getCloth().getHoleRadius() + 2,
								finalClothPosition.getY()
										+ ((finalWoodPosition.getY() - finalClothPosition
												.getY()) / 2)
										+ (30 - table.getCloth()
												.getHoleRadius())));
		// Right Up Hole
		table.getCloth()
				.addHole(
						new MathVector(
								finalClothPosition.getX()
										+ 98
										- table.getCloth().getHoleRadius()
										+ (finalWoodPosition.getX() - finalClothPosition
												.getX()) / 2,
								initialClothPosition.getY()
										- table.getCloth().getHoleRadius()));
		// Right Buttom Hole
		table.getCloth()
				.addHole(
						new MathVector(
								finalClothPosition.getX()
										+ 98
										- table.getCloth().getHoleRadius()
										+ (finalWoodPosition.getX() - finalClothPosition
												.getX()) / 2,
								finalClothPosition.getY()
										+ table.getCloth().getHoleRadius() + 20));

	}

	public void start() {
		(new Thread(this)).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (mouseDown) {
			System.out.println("TESTE");
			players[0].getCue().updateOffset();
		}

		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//try {
			Draw(g);
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
	}

	private void Draw(Graphics g) {
		// TODO Auto-generated method stub
		DrawTable(g);
		DrawBalls(g, table.getWhiteBall().getRadius());
		DrawStick(g);
	}

	private void DrawTable(Graphics g) {
		// TODO Auto-generated method stub
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
		g.fillArc((int) table.getCloth().getCoordHoles().get(0).getX() - 7,
				(int) table.getCloth().getCoordHoles().get(0).getY() - 7,
				2 * 27, 2 * 27, 0, 270);
		g.fillArc((int) table.getCloth().getCoordHoles().get(1).getX() - 7,
				(int) table.getCloth().getCoordHoles().get(1).getY() - 7,
				2 * 27, 2 * 27, 90, 270);
		g.fillArc((int) table.getCloth().getCoordHoles().get(2).getX() - 7,
				(int) table.getCloth().getCoordHoles().get(2).getY() - 10,
				2 * 27, 2 * 40, 0, 180);
		g.fillArc((int) table.getCloth().getCoordHoles().get(3).getX() - 7,
				(int) table.getCloth().getCoordHoles().get(3).getY() - 30,
				2 * 27, 2 * 40, 180, 180);
		g.fillArc((int) table.getCloth().getCoordHoles().get(4).getX() - 7,
				(int) table.getCloth().getCoordHoles().get(4).getY() - 7,
				2 * 27, 2 * 27, 270, 270);
		g.fillArc((int) table.getCloth().getCoordHoles().get(5).getX() - 7,
				(int) table.getCloth().getCoordHoles().get(5).getY() - 7,
				2 * 27, 2 * 27, 180, 270);

		// Drawing Holes
		g.setColor(new Color(122, 139, 139));
		for (int i = 0; i < table.getCloth().getCoordHoles().size(); i++)
			g.fillOval((int) table.getCloth().getCoordHoles().get(i).getX(),
					(int) table.getCloth().getCoordHoles().get(i).getY(),
					2 * table.getCloth().getHoleRadius(), 2 * table.getCloth()
							.getHoleRadius());

	}

	private void DrawStick(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		double rotRad, xCorrection, yCorrection;
		int cx = 0, cy = 0;

		Ball wb = table.getWhiteBall();
		MathVector wbCoords = new MathVector(wb.getX() + wb.getRadius(),
				wb.getY() + wb.getRadius());

		rotRad = Math.toRadians(players[0].getCueRotation());

		xCorrection = Math.cos(rotRad)
				* (2 * wb.getRadius() + players[0].getCue().getCueOffset());
		yCorrection = Math.sin(rotRad)
				* (2 * wb.getRadius() + players[0].getCue().getCueOffset());

		cx = (int) (wbCoords.getX());
		cy = (int) (wbCoords.getY() - cue.getHeight(null) / 2);

		g2d.translate(xCorrection, yCorrection);
		g2d.rotate(rotRad, wbCoords.getX(), wbCoords.getY());

		g2d.drawImage(cue, cx, cy, cue.getWidth(null), cue.getHeight(null),
				null);
	}

	private void DrawBalls(Graphics g, double radiusBall) {
		// TODO Auto-generated method stub
		for (int i = 0; i < table.getBallSet().size(); i++) {
			g.setColor(Utilities.ballsColors[table.getBallSet().get(i)
					.getColor().ordinal()]);
			g.fillOval((int) table.getBallSet().get(i).getX(), (int) table
					.getBallSet().get(i).getY(), 2 * (int) radiusBall,
					2 * (int) radiusBall);
		}
	}

	public double getAlpha(double dx, double dy) {
		return Math.toDegrees(Math.atan(dy / dx));
	}

	public void calculateRotation(int mX, int mY) {
		Ball wb = table.getWhiteBall();
		int radiusBall = (int) wb.getRadius();
		MathVector wbC = new MathVector(wb.getX() + radiusBall, wb.getY()
				+ radiusBall);

		double dx, dy, alpha;

		if (mX > wbC.getX()) {
			dx = mX - wbC.getX();

			if (mY < wbC.getY()) {
				dy = wbC.getY() - mY;
				alpha = getAlpha(dx, dy);
				players[0].setCueRotation((int) (180 - alpha));
			} else {
				dy = mY - wbC.getY();
				alpha = getAlpha(dx, dy);
				players[0].setCueRotation((int) (180 + alpha));
			}
		} else {
			dx = wbC.getX() - mX;

			if (mY < wbC.getY()) {
				dy = wbC.getY() - mY;
				alpha = getAlpha(dx, dy);
				players[0].setCueRotation((int) alpha);
			} else {
				dy = mY - wbC.getY();
				alpha = getAlpha(dx, dy);
				players[0].setCueRotation((int) (360 - alpha));
			}
		}
	}

	private boolean mouseDown = false;

	@Override
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			mouseDown = true;
			table.setMoveState(MoveState.START_HIT);
			//initThread();
			break;
		/*
		 * case MouseEvent.BUTTON3: table.setMoveState(MoveState.WAITING_HIT);
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
		table.setMoveState(MoveState.WAITING_HIT);
		players[0].getCue().resetOffset();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (table.getMoveState() == MoveState.WAITING_HIT) {
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
			players[0].getCue().incRotation();
			if (players[0].getCueRotation() > 360)
				players[0].setCueRotation(0);
			break;
		case KeyEvent.VK_A:
			players[0].getCue().decRotation();
			if (players[0].getCueRotation() < 0)
				players[0].setCueRotation(360);
			break;
		/*
		 * case KeyEvent.VK_S: table.getWhiteBall().setPosition(newPosition);
		 */}
		repaint();
		System.out.println("Cue alpha: " + players[0].getCueRotation());
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
