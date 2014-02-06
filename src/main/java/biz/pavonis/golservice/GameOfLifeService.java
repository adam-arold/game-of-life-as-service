package biz.pavonis.golservice;

public interface GameOfLifeService {

    /**
     * Adds a pattern to the game of life universe at the
     * given coordinates. Throws an {@link IllegalArgumentException} if the
     * pattern does not fit.
     * 
     * @param pattern
     */
    void addPattern(Pattern pattern, int x, int y);

}
