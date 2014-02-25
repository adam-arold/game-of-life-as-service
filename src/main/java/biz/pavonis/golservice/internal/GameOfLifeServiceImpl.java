package biz.pavonis.golservice.internal;

import java.sql.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import biz.pavonis.golservice.api.GameOfLifeService;
import biz.pavonis.golservice.api.GameOfLifeServiceBuilder;
import biz.pavonis.golservice.api.Generation;
import biz.pavonis.golservice.api.LifeTickListener;
import biz.pavonis.golservice.api.Pattern;
import biz.pavonis.golservice.api.PatternOrientation;

public final class GameOfLifeServiceImpl implements GameOfLifeService {

    private final int tickInterval;
    private final Universe universe;
    private final List<LifeTickListener> lifeTickListeners = new CopyOnWriteArrayList<>();
    private final Timer timer = new Timer();

    private AtomicLong generation = new AtomicLong();
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
        pause();
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
        return new Generation(generation.get(), new Date(System.currentTimeMillis()));
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
                generation.incrementAndGet();
                fireLifeTickListeners(newState);
            }
        };
    }

    private void fireLifeTickListeners(boolean[][] newState) {
        for (LifeTickListener listener : lifeTickListeners) {
            listener.tick(new TickImpl(getLatestGeneration(), newState));
        }
    }

}
