package biz.pavonis.golservice.internal;

/**
 * Utility class for matrix operations.
 */
public final class MatrixUtils {

    private MatrixUtils() {
    }

    public static boolean[][] rotateClockWise(boolean[][] matrix) {
        final int height = matrix.length;
        final int width = matrix[0].length;
        boolean[][] ret = new boolean[width][height];
        for (int verticalIndex = 0; verticalIndex < height; verticalIndex++) {
            for (int horizontalIndex = 0; horizontalIndex < width; horizontalIndex++) {
                ret[horizontalIndex][height - 1 - verticalIndex] = matrix[verticalIndex][horizontalIndex];
            }
        }
        return ret;
    }

    public static boolean[][] rotateCounterClockWise(boolean[][] matrix) {
        final int height = matrix.length;
        final int width = matrix[0].length;
        boolean[][] ret = new boolean[width][height];
        for (int verticalIndex = 0; verticalIndex < height; verticalIndex++) {
            for (int horizontalIndex = 0; horizontalIndex < width; horizontalIndex++) {
                ret[width - 1 - horizontalIndex][verticalIndex] = matrix[verticalIndex][horizontalIndex];
            }
        }
        return ret;
    }

    public static boolean[][] rotate180degrees(boolean[][] matrix) {
        final int height = matrix.length;
        final int width = matrix[0].length;
        boolean[][] ret = new boolean[height][width];
        for (int verticalIndex = 0; verticalIndex < height; verticalIndex++) {
            for (int horizontalIndex = 0; horizontalIndex < width; horizontalIndex++) {
                ret[height - 1 - verticalIndex][width - 1 - horizontalIndex] = matrix[verticalIndex][horizontalIndex];
            }
        }
        return ret;
    }
}
