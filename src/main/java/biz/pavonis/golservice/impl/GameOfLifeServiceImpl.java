package biz.pavonis.golservice.impl;

import biz.pavonis.golservice.GameOfLifeService;
import biz.pavonis.golservice.GameOfLifeServiceBuilder;
import biz.pavonis.golservice.Generation;
import biz.pavonis.golservice.LifeTickListener;
import biz.pavonis.golservice.Pattern;

public final class GameOfLifeServiceImpl implements GameOfLifeService {

    private boolean[][] universe;

    /**
     * Creates a new {@link GameOfLifeService} using the given
     * {@link GameOfLifeServiceBuilder} object.
     * 
     * @param builder
     */
    public GameOfLifeServiceImpl(GameOfLifeServiceBuilder builder) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void addPattern(Pattern pattern, int x, int y) {
        // TODO Auto-generated method stub

    }

    @Override
    public void start() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

    @Override
    public Generation getLatestGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addTickListener(LifeTickListener listener) {
        // TODO Auto-generated method stub

    }

}
