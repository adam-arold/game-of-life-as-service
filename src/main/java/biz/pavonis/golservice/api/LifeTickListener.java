package biz.pavonis.golservice.api;

/**
 * A listener which can be attached to a {@link GameOfLifeService}.
 */
public interface LifeTickListener {

    /**
     * Called when a new {@link Tick} is calculated in
     * the game of life.
     * 
     * @param tick
     */
    void tick(Tick tick);
}
