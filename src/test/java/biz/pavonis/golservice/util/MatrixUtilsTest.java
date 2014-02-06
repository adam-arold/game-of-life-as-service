package biz.pavonis.golservice.util;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MatrixUtilsTest {

    @Test
    public void testRotateClockWise() {
        boolean[][] matrix = new boolean[][] { { true, false }, { true, false }, { false, false } };
        boolean[][] expected = new boolean[][] { { false, true, true }, { false, false, false } };
        boolean[][] actual = MatrixUtils.rotateClockWise(matrix);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testRotateCounterClockWise() {
        boolean[][] matrix = new boolean[][] { { true, false }, { true, false }, { false, false } };
        boolean[][] expected = new boolean[][] { { false, false, false }, { true, true, false } };
        boolean[][] actual = MatrixUtils.rotateCounterClockWise(matrix);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testRotate180Degrees() {
        boolean[][] matrix = new boolean[][] { { true, false }, { true, false }, { false, false } };
        boolean[][] expected = new boolean[][] { { false, false }, { false, true }, { false, true } };
        boolean[][] actual = MatrixUtils.rotate180degrees(matrix);
        assertArrayEquals(expected, actual);
    }

}
