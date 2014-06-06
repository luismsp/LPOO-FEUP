package logic;

public class Cue {
	private static Cue instance;
	
	private static int DEFAULT_CUE_OFFSET_INC = 4;
	
	private final int size = 10;
	private final int strength = 1000;
	private int rotation = 0;
	private static int MAX_CUE_OFFSET = (int) (6 * Ball.getRadius());
	private int cueOffset = 0, cueOffsetInc = DEFAULT_CUE_OFFSET_INC;
	private V2D position = new V2D();
	

	//

	private Cue() {}
	
	public static Cue getInstance() {
		if (instance == null)
			return new Cue();
		return instance;
	}

	public int getStrength() {
		return strength;
	}

	public int getSize() {
		return size;
	}

	public void setRotation(int newRotation) {
		rotation = newRotation;
	}

	public void incRotation() {
		rotation++;
	}

	public void decRotation() {
		rotation--;
	}

	public int getRotation() {
		return rotation;
	}

	public void updateOffset() {
		cueOffset += cueOffsetInc;
		
		if (cueOffset >= MAX_CUE_OFFSET || cueOffset <= 0)
			cueOffsetInc *= -1;
	}

	public double getOffset() {
		return cueOffset;
	}
	
	public static int getMaxOffset() {
		return Cue.MAX_CUE_OFFSET;
	}
	
	public void resetOffset() {
		cueOffset = 0;
		cueOffsetInc = DEFAULT_CUE_OFFSET_INC;
	}
	
	public void setPosition(V2D position) {
		this.position = position;
	}
	
	public V2D getPosition() {
		return position;
	}
	
	public double getCueForce() {
		return (strength * cueOffset) / (Cue.getMaxOffset());
	}
}