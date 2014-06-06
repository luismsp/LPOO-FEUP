package logic;

public enum BallColor {
	
	WHITE,RED,YELLOW,GREEN,BROWN,BLUE,PINK,BLACK;
	
	public static BallColor getColorFromValue(int n) { return BallColor.values()[n]; }

	public static int getValueFromColor(BallColor c) { return c.ordinal(); }
};