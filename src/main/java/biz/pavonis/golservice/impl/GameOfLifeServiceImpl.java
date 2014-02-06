package biz.pavonis.golservice.impl;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import biz.pavonis.golservice.GameOfLifeService;
import biz.pavonis.golservice.GameOfLifeServiceBuilder;
import biz.pavonis.golservice.Generation;
import biz.pavonis.golservice.LifeTickListener;
import biz.pavonis.golservice.Pattern;
import biz.pavonis.golservice.PatternOrientation;

public final class GameOfLifeServiceImpl implements GameOfLifeService {

    private final int tickInterval;
    private final Universe universe;
    private final List<LifeTickListener> lifeTickListeners = new CopyOnWriteArrayList<>();
    private final Timer timer = new Timer();

    private TimerTask timerTask = createTimerTask();

    public GameOfLifeServiceImpl(GameOfLifeServiceBuilder builder) {
        tickInterval = builder.getTickInterval();
        if (builder.getInitialState() != null) {
            universe = new Universe(builder.getInitialState());
        } else {
            universe = new Universe(new boolean[builder.getHeight()][builder.getWidth()]);
        }
    }

    @Override
    public void start() {
        stop();
        timer.scheduleAtFixedRate(timerTask, 0, tickInterval);
    }

    @Override
    public void pause() {
        timerTask.cancel();
        timerTask = createTimerTask();
    }

    @Override
    public void stop() {
        pause();
        universe.resetUniverseState();
    }

    @Override
    public Generation getLatestGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void stampPattern(Pattern pattern, int x, int y, PatternOrientation orientation) {
        universe.stampPattern(pattern, x, y, orientation);
    }

    @Override
    public boolean addTickListener(LifeTickListener listener) {
        return lifeTickListeners.add(listener);
    }

    private TimerTask createTimerTask() {
        return new TimerTask() {

            @Override
            public void run() {
                boolean[][] newState = universe.recalculateUniverseState();
                fireLifeTickListeners(newState);
            }
        };
    }

    private void fireLifeTickListeners(boolean[][] newState) {
        for (LifeTickListener listener : lifeTickListeners) {
            // TODO: generate Tick
        }
    }

}
