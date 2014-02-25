package biz.pavonis.golservice.api;

import biz.pavonis.golservice.api.exception.PatternDoesNotFitException;

/**
 * Represents a game of life session.
 */
public interface GameOfLifeService {

    /**
     * Starts up the game of life. Has no effect if it is already started.
     */
    void start();

    /**
     * Pauses the game of life. Has no effect if it is already paused.
     */
    void pause();

    /**
     * Stops the game of life and resets to the initial state.
     */
    void stop();

    /**
     * Returns the latest generation (count and timestamp).
     * 
     * @return
     */
    Generation getLatestGeneration();

    /**
     * <p>Adds a pattern to the game of life universe at the
     * given coordinates. Throws an {@link PatternDoesNotFitException} if the
     * pattern does not fit. Overwrites any previous states in life.</p>
     * <em>Note:</em>
     * If the given pattern covers the life universe all state will be
     * overwritten.
     * 
     * @param pattern
     * @param x
     * @param y
     * @param orientation
     */
    void stampPattern(Pattern pattern, int x, int y, PatternOrientation orientation);

    /**
     * Adds a {@link LifeTickListener} to this {@link GameOfLifeService}.
     * It will be called each time a new {@link Tick} is calculted in life.
     * 
     * @param listener
     * @return
     */
    boolean addTickListener(LifeTickListener listener);

}
