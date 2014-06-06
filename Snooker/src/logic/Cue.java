package logic;

public class Cue {
	private static int DEFAULT_CUE_OFFSET_INC = 4;
	
	private static final int size = 10;
	private static final int strength = 1000;
	private static int rotation = 0;
	private static int MAX_CUE_OFFSET;
	private static int cueOffset = 0, cueOffsetInc = DEFAULT_CUE_OFFSET_INC;

	//

	public Cue(int maxCueOffset) {
		Cue.MAX_CUE_OFFSET = maxCueOffset;
	}

	public int getStrength() {
		return strength;
	}

	public int getSize() {
		return size;
	}

	public void setRotation(int newRotation) {
		Cue.rotation = newRotation;
	}

	public void incRotation() {
		rotation++;
	}

	public void decRotation() {
		rotation--;
	}

	public int getRotation() {
		return Cue.rotation;
	}

	public void updateOffset() {
		cueOffset += cueOffsetInc;
		
		if (cueOffset >= MAX_CUE_OFFSET || cueOffset <= 0)
			cueOffsetInc *= -1;
	}

	public double getCueOffset() {
		return cueOffset;
	}
	
	public void resetOffset() {
		cueOffset = 0;
		cueOffsetInc = DEFAULT_CUE_OFFSET_INC;
	}
}