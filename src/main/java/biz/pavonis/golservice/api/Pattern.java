package biz.pavonis.golservice.api;

/**
 * Represents a game of life object pattern.
 */
public class Pattern {

    private final int id;
    private final String name;
    private final boolean[][] descriptor;

    public Pattern(int id, String name, boolean[][] descriptor) {
        this.id = id;
        this.name = name;
        this.descriptor = descriptor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean[][] getDescriptor() {
        return descriptor;
    }
}
