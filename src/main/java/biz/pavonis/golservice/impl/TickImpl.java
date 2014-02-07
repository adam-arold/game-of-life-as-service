package biz.pavonis.golservice.impl;

import biz.pavonis.golservice.Generation;
import biz.pavonis.golservice.Tick;

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
