package biz.pavonis.golservice.internal;

import static biz.pavonis.golservice.internal.BitUtils.toBinaryArray;
import static biz.pavonis.golservice.internal.MatrixUtils.rotate180degrees;
import static biz.pavonis.golservice.internal.MatrixUtils.rotateClockWise;
import static biz.pavonis.golservice.internal.MatrixUtils.rotateCounterClockWise;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import biz.pavonis.golservice.api.Pattern;
import biz.pavonis.golservice.api.PatternOrientation;
import biz.pavonis.golservice.api.Tick;

/**
 * Represents a game of life universe.
 */
public class Universe {

	private static final int BORDER_OFFSET = 1;
	private static final int BORDER_SIZE = 1;
	private static final int BORDER_COUNT = 2;
	private static final int FLIP_INDEX = 0;
	private static final int FLOP_INDEX = 1;
	private final Queue<StampablePattern> patternQueue = new LinkedBlockingQueue<>();
	private final boolean[][][] universeDoubleBuffer;
	private final int height;
	private final int width;
	private final boolean[] lookupTable = new boolean[512];
	{
		generateLookupTable();
	}
	private int flipFlopIndex = FLIP_INDEX;

	public Universe(boolean[][] universeState) {
		height = universeState.length;
		width = universeState[0].length;
		universeDoubleBuffer = new boolean[2][height + BORDER_COUNT * BORDER_SIZE][width + BORDER_COUNT * BORDER_SIZE];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				universeDoubleBuffer[flipFlopIndex][BORDER_OFFSET + y][BORDER_OFFSET + x] = universeState[y][x];
			}
		}
	}

	/**
	 * Resets the universe state to an empty one.
	 */
	public void resetUniverseState() {
		universeDoubleBuffer[FLIP_INDEX] = new boolean[height + BORDER_COUNT * BORDER_SIZE][width + BORDER_COUNT * BORDER_SIZE];
		universeDoubleBuffer[FLOP_INDEX] = new boolean[height + BORDER_COUNT * BORDER_SIZE][width + BORDER_COUNT * BORDER_SIZE];
	}

	/**
	 * Recalculates the universe state and returns the new one.
	 * 
	 * @return
	 */
	public boolean[][] recalculateUniverseState() {
		stampPatterns();
		int newFlipFlopIndex = (flipFlopIndex == FLIP_INDEX ? FLOP_INDEX : FLIP_INDEX);
		boolean[][] newBuffer = universeDoubleBuffer[newFlipFlopIndex];
		boolean[][] oldBuffer = universeDoubleBuffer[flipFlopIndex];
		for (int y = BORDER_OFFSET; y <= height; y++) {
			int environment = (oldBuffer[y - 1][0] ? 32 : 0) + (oldBuffer[y - 1][1] ? 4 : 0) + (oldBuffer[y][0] ? 16 : 0)
							+ (oldBuffer[y][1] ? 2 : 0) + (oldBuffer[y + 1][0] ? 8 : 0) + (oldBuffer[y + 1][1] ? 1 : 0);
			for (int x = BORDER_OFFSET; x <= width; x++) {
				environment = ((environment % 64) * 8) + (oldBuffer[y - 1][x + 1] ? 4 : 0) + (oldBuffer[y][x + 1] ? 2 : 0)
								+ (oldBuffer[y + 1][x + 1] ? 1 : 0);
				newBuffer[y][x] = lookupTable[environment];
			}
		}
		universeDoubleBuffer[newFlipFlopIndex] = newBuffer;
		flipFlopIndex = newFlipFlopIndex;
		return universeDoubleBuffer[flipFlopIndex];
	}

	private void stampPatterns() {
		Iterator<StampablePattern> patternIter = patternQueue.iterator();
		while (patternIter.hasNext()) {
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

	/**
	 * Stamps a pattern to this {@link Universe}. Any existing values will be overwritten. Please note that stamping only takes
	 * effect at the next {@link Tick}.
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

	private void generateLookupTable() {
		for (int i = 0; i < 512; i++) {
			boolean[] bits = toBinaryArray(i, 9);
			int liveNeighbors = 0;
			for (int j = 0; j < 9; j++) {
				if (bits[j] && j != 4) {
					liveNeighbors++;
				}
			}
			boolean isLiving = bits[4];
			if (!isLiving && liveNeighbors == 3) {
				lookupTable[i] = true;
			} else if (isLiving && (liveNeighbors == 2 || liveNeighbors == 3)) {
				lookupTable[i] = true;
			} else {
				lookupTable[i] = false;
			}
		}
	}

}
