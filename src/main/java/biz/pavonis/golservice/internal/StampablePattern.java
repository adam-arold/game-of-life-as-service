package biz.pavonis.golservice.internal;

/**
 * Represents a pattern to be stamped.
 */
public class StampablePattern {

	private final int startX;
	private final int startY;
	private final boolean[][] pattern;

	public StampablePattern(int startX, int startY, boolean[][] pattern) {
		this.startX = startX;
		this.startY = startY;
		this.pattern = pattern;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public boolean[][] getPattern() {
		return pattern;
	}

}
