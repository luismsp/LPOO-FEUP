package logic;
import logic.ColorL;

public class Utils {

	public static ColorL getColorFromValue(int n) {
		return ColorL.values()[n];
	}
	
	public static int getValueFromColor(ColorL c) {
		return c.ordinal();
	}
}


