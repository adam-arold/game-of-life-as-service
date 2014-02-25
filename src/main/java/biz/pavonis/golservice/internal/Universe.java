package biz.pavonis.golservice.internal;

import static biz.pavonis.golservice.internal.MatrixUtils.rotate180degrees;
import static biz.pavonis.golservice.internal.MatrixUtils.rotateClockWise;
import static biz.pavonis.golservice.internal.MatrixUtils.rotateCounterClockWise;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;

import biz.pavonis.golservice.api.Pattern;
import biz.pavonis.golservice.api.PatternOrientation;
import biz.pavonis.golservice.api.Tick;

/**
 * Represents a game of life universe.
 */
public class Universe {

	private static final int FLIP_INDEX = 0;
	private static final int FLOP_INDEX = 1;

	private final Logger logger = getLogger(getClass());
	
	private final Queue<StampablePattern> patternQueue = new LinkedBlockingQueue<>();
	private final boolean[][][] universeDoubleBuffer;
	private final int height;
	private final int width;
	private int flipFlopIndex = FLIP_INDEX;
	

	public Universe(boolean[][] universeState) {
		height = universeState.length;
		width = universeState[0].length;
		universeDoubleBuffer = new boolean[2][height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				universeDoubleBuffer[FLIP_INDEX][y][x] = universeState[y][x];
			}
		}
	}

	/**
	 * Resets the universe state to an empty one.
	 */
	public void resetUniverseState() {
		universeDoubleBuffer[FLIP_INDEX] = new boolean[height][width];
		universeDoubleBuffer[FLOP_INDEX] = new boolean[height][width];
	}

	/**
	 * Recalculates the universe state and returns the new one.
	 * 
	 * @return
	 */
	public boolean[][] recalculateUniverseState() {
		int newFlipFlopIndex = flipFlopIndex == FLIP_INDEX ? FLOP_INDEX : FLIP_INDEX;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int liveNeighbors = countLiveNeighbors(x, y);
				boolean isLiving = universeDoubleBuffer[flipFlopIndex][y][x];
				if (isLiving && liveNeighbors < 2) {
					universeDoubleBuffer[newFlipFlopIndex][y][x] = false;
				}
				if (isLiving && (liveNeighbors == 2 || liveNeighbors == 3)) {
					universeDoubleBuffer[newFlipFlopIndex][y][x] = true;
				}
				if (isLiving && liveNeighbors > 3) {
					universeDoubleBuffer[newFlipFlopIndex][y][x] = false;
				}
				if (!isLiving && liveNeighbors == 3) {
					universeDoubleBuffer[newFlipFlopIndex][y][x] = true;
				}
			}
		}
		stampPatterns();
		flipFlopIndex = newFlipFlopIndex; // assignment is atomic, no synchronization needed
		return universeDoubleBuffer[flipFlopIndex];
	}

	private void stampPatterns() {
		Iterator<StampablePattern> patternIter = patternQueue.iterator();
		while(patternIter.hasNext()) {
			StampablePattern pattern = patternIter.next();
			patternIter.remove();
			int patternWidth = pattern.getPattern()[0].length;
			int patternHeight = pattern.getPattern().length;
			for (int y = 0; y < patternHeight; y++) {
				for (int x = 0; x < patternWidth; x++) {
					universeDoubleBuffer[flipFlopIndex][y + pattern.getStartY()][x + pattern.getStartX()] = pattern.getPattern()[y][x];
				}
			}
		}
	}

	private int countLiveNeighbors(int x, int y) {
		int result = 0;
		for (CellNeighbor neighbor : CellNeighbor.values()) {
			try {
				boolean isLiving = universeDoubleBuffer[flipFlopIndex][y + neighbor.getYOffset()][x + neighbor.getXOffset()];
				if (isLiving) {
					result++;
				}
			} catch (IndexOutOfBoundsException e) {
				logger.info("Cell's neighbor was off the grid.", e);
			}
		}
		return result;
	}

	/**
	 * Stamps a pattern to this {@link Universe}. Any existing values will be overwritten.
	 * Please note that stamping only takes effect at the next {@link Tick}.
	 * 
	 * @param pattern
	 * @param startX
	 * @param startY
	 * @param orientation
	 */
	public void stampPattern(Pattern pattern, int startX, int startY, PatternOrientation orientation) {
		patternQueue.add(new StampablePattern(startX, startY, transformPattern(pattern, orientation)));
	}

	private boolean[][] transformPattern(Pattern pattern, PatternOrientation orientation) {
		boolean[][] transformedPattern = null;
		switch (orientation) {
		case TOP:
			transformedPattern = pattern.getDescriptor();
			break;
		case RIGHT:
			transformedPattern = rotateClockWise(pattern.getDescriptor());
		case BOTTOM:
			transformedPattern = rotate180degrees(pattern.getDescriptor());
			break;
		case LEFT:
			transformedPattern = rotateCounterClockWise(pattern.getDescriptor());
			break;
		default:
			break;
		}
		return transformedPattern;
	}

}
