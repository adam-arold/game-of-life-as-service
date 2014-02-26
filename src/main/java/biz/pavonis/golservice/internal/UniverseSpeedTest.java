package biz.pavonis.golservice.internal;

import static java.lang.System.nanoTime;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class UniverseSpeedTest {

	private static final int UNIVERSE_SIZE = 5000;
	private static final int LOOPS = 100;
	private Universe target;

	@Before
	public void setUp() throws Exception {
		boolean[][] randomUniverse = new boolean[UNIVERSE_SIZE][UNIVERSE_SIZE];
		Random random = new Random();
		for (int y = 0; y < UNIVERSE_SIZE; y++) {
			for (int x = 0; x < UNIVERSE_SIZE; x++) {
				randomUniverse[y][x] = random.nextBoolean();
			}
		}
		target = new Universe(randomUniverse);
	}

	@Test
	public void test() {
		long start = nanoTime();
		for(int i = 0; i < LOOPS; i++) {
			target.recalculateUniverseState();
		}
		long runtimeMs = (nanoTime() - start)  / 1000 / 1000;
		System.out.println("Runtime was: " + runtimeMs + "ms with UNIVERSE_SIZE: " + UNIVERSE_SIZE + " and " + LOOPS + " loops ("+runtimeMs / LOOPS+"/loop).");
	}

}
