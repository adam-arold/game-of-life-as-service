package biz.pavonis.golservice.impl;

import java.util.concurrent.atomic.AtomicReference;

import biz.pavonis.golservice.Pattern;
import biz.pavonis.golservice.PatternOrientation;
import biz.pavonis.golservice.util.MatrixUtils;

/**
 * Represents a game of life universe.
 */
public class Universe {

    private AtomicReference<boolean[][]> universeState = new AtomicReference<>();
    private final int height;
    private final int width;

    public Universe(boolean[][] universeState) {
        height = universeState.length;
        width = universeState[0].length;
        this.universeState.getAndSet(universeState);
    }

    /**
     * Resets the universe state to an empty one.
     */
    public void resetUniverseState() {
        universeState.getAndSet(new boolean[height][width]);
    }

    /**
     * Recalculates the universe state and returns the new one.
     * 
     * @return
     */
    public boolean[][] recalculateUniverseState() {
        boolean[][] newState = new boolean[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int liveNeighbors = countLiveNeighbors(x, y);
                boolean isLiving = universeState.get()[y][x];
                if (isLiving && liveNeighbors < 2) {
                    newState[y][x] = false;
                }
                if (isLiving && (liveNeighbors == 2 || liveNeighbors == 3)) {
                    newState[y][x] = true;
                }
                if (isLiving && liveNeighbors > 3) {
                    newState[y][x] = false;
                }
                if (!isLiving && liveNeighbors == 3) {
                    newState[y][x] = true;
                }
            }
        }
        universeState.getAndSet(newState);
        return universeState.get();
    }

    private int countLiveNeighbors(int x, int y) {
        int result = 0;
        for (CellNeighbor neighbor : CellNeighbor.values()) {
            try {
                boolean isLiving = universeState.get()[y + neighbor.getYOffset()][x + neighbor.getXOffset()];
                if (isLiving) {
                    result++;
                }
            } catch (IndexOutOfBoundsException e) {
                // TODO: logging here
            }
        }
        return result;
    }

    /**
     * Stamps a pattern to this {@link Universe}. Any existing values
     * will be overwritten.
     * 
     * @param pattern
     * @param startX
     * @param startY
     * @param orientation
     */
    public void stampPattern(Pattern pattern, int startX, int startY, PatternOrientation orientation) {
        boolean[][] transformedPattern = transformPattern(pattern, orientation);
        int width = transformedPattern[0].length;
        int height = transformedPattern.length;
        boolean[][] newState = universeState.get();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newState[y + startY][x + startX] = transformedPattern[y][x];
            }
        }
        universeState.getAndSet(newState);
    }

    private boolean[][] transformPattern(Pattern pattern, PatternOrientation orientation) {
        boolean[][] transformedPattern = null;
        switch (orientation) {
        case TOP:
            transformedPattern = pattern.getDescriptor();
            break;
        case RIGHT:
            transformedPattern = MatrixUtils.rotateClockWise(pattern.getDescriptor());
        case BOTTOM:
            transformedPattern = MatrixUtils.rotate180degrees(pattern.getDescriptor());
            break;
        case LEFT:
            transformedPattern = MatrixUtils.rotateCounterClockWise(pattern.getDescriptor());
            break;
        default:
            break;
        }
        return transformedPattern;
    }

}
