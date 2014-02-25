package biz.pavonis.golservice.api;

import org.junit.Before;
import org.junit.Test;

import biz.pavonis.golservice.api.GameOfLifeServiceBuilder;
import biz.pavonis.golservice.api.exception.InvalidBuilderDataException;

public class GameOfLifeServiceBuilderTest {

    private GameOfLifeServiceBuilder target;

    @Before
    public void setUp() throws Exception {
        target = new GameOfLifeServiceBuilder();
    }

    @Test(expected = InvalidBuilderDataException.class)
    public void testInvalidWidth() {
        target.setWidth(0);
        target.build();
    }

    @Test(expected = InvalidBuilderDataException.class)
    public void testInvalidHeight() {
        target.setHeight(0);
        target.build();
    }

    @Test(expected = InvalidBuilderDataException.class)
    public void testInvalidInitialState() {
        target.setInitialState(new boolean[][] {});
        target.build();
    }

    @Test(expected = InvalidBuilderDataException.class)
    public void testInvalidInitialStateAndSizes() {
        target.setHeight(1);
        target.setWidth(1);
        target.setInitialState(new boolean[][] {});
        target.build();
    }

    @Test(expected = InvalidBuilderDataException.class)
    public void testInvalidTickInterval() {
        target.setTickInterval(0);
        target.build();
    }

}
