package biz.pavonis.golservice.internal;

import org.junit.Before;
import org.junit.Test;

import biz.pavonis.golservice.api.GameOfLifeService;
import biz.pavonis.golservice.api.GameOfLifeServiceBuilder;

public class GameOfLifeServiceImplTest {

    private GameOfLifeService target;

    @Before
    public void setUp() throws Exception {
        target = new GameOfLifeServiceBuilder().setHeight(5000).setWidth(5000).setTickInterval(50).build();
    }

    @Test
    public void test() throws InterruptedException {
        target.start();
        Thread.sleep(5000);
        System.out.println(target.getLatestGeneration().getCount());
    }

}
