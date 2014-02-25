package biz.pavonis.golservice.api;

import biz.pavonis.golservice.api.exception.InvalidBuilderDataException;
import biz.pavonis.golservice.internal.GameOfLifeServiceImpl;

/**
 * This builder can be used to create a {@link GameOfLifeService}.
 */
public final class GameOfLifeServiceBuilder {

    private int width;
    private int height;
    private int tickInterval;
    private boolean[][] initialState;

    /**
     * Creates a {@link GameOfLifeService} using this builder.
     * 
     * @return
     */
    public GameOfLifeService build() {
        if (tickInterval < 1) {
            throw new InvalidBuilderDataException("Tick interval must be greater than 0!");
        }
        if (width < 1) {
            throw new InvalidBuilderDataException("Width must be greater than 0!");
        }
        if (height < 1) {
            throw new InvalidBuilderDataException("Height must be greater than 0!");
        }
        if (initialState != null && (initialState.length < 1 || initialState[0].length < 1)) {
            throw new InvalidBuilderDataException("The given initial state is too small (min 1 * 1 requried).");
        }
        if (initialState != null && initialState.length != height) {
            throw new InvalidBuilderDataException("The give n initial state has different height than the provided height.");
        }
        if (initialState != null && initialState[0].length != width) {
            throw new InvalidBuilderDataException("The give n initial state has different width than the provided height.");
        }
        return new GameOfLifeServiceImpl(this);
    }

    public int getWidth() {
        return width;
    }

    /**
     * <p>Sets the <em>width</em> of the universe. Each cell in a {@link Tick} has
     * the size of <code>1 * 1</code>. Minimum width is <code>1</code>.</p>
     * <strong><em>This is a mandatory parameter.</em></strong>
     * 
     * @param width
     * @return
     */
    public GameOfLifeServiceBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    /**
     * <p>Sets the <em>height</em> of the universe. Each cell in a {@link Tick} has
     * the size of <code>1 * 1</code>. Minimum height is <code>1</code>.</p>
     * <strong><em>This is a mandatory parameter.</em></strong>
     * 
     * @param height
     * @return
     */
    public GameOfLifeServiceBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getTickInterval() {
        return tickInterval;
    }

    /**
     * <p>Sets how often a {@link Tick} should be calculated in life in
     * <em>milliseconds</em>. <strong>Note:</strong> there is no guarantee
     * that a {@link Tick} will be calculated within the given interval
     * in case a too narrow interval is given. Use this with a reasonable
     * amount. Minimum tick interval is <code>1</code>.</p>
     * <strong><em>This is a mandatory parameter.</em></strong>
     * 
     * @param tickInterval
     * @return
     */
    public GameOfLifeServiceBuilder setTickInterval(int tickInterval) {
        this.tickInterval = tickInterval;
        return this;
    }

    public boolean[][] getInitialState() {
        return initialState;
    }

    /**
     * <p>Sets an initial state for this life.</p>
     * <strong><em>This is an optional parameter.</em></strong>
     * 
     * @param initialState
     * @return
     */
    public GameOfLifeServiceBuilder setInitialState(boolean[][] initialState) {
        this.initialState = initialState;
        return this;
    }

}
