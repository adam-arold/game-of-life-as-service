package biz.pavonis.golservice;

public interface Tick {

    /**
     * Gets the generation of this {@link Tick}.
     * 
     * @return
     */
    Generation getGeneration();

    /**
     * Returns the universe state in this {@link Tick}.
     * 
     * @return
     */
    boolean[][] getUniverseState();
}
