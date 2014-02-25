package biz.pavonis.golservice.internal;

import biz.pavonis.golservice.api.Generation;
import biz.pavonis.golservice.api.Tick;

public class TickImpl implements Tick {

    private final Generation generation;
    private final boolean[][] universeState;

    public TickImpl(Generation generation, boolean[][] universeState) {
        this.generation = generation;
        this.universeState = universeState;
    }

    @Override
    public Generation getGeneration() {
        return generation;
    }

    @Override
    public boolean[][] getUniverseState() {
        return universeState;
    }

}
