package biz.pavonis.golservice.internal;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public class UniverseTest {

	private static final boolean[][] GLIDER_UNIVERSE_0 = new boolean[][] {
		            { false, false, true, false},
					{ true, false, true, false},
					{ false, true, true, false},
					{ false, false, false, false}};
	private static final boolean[][] GLIDER_UNIVERSE_1 = new boolean[][] {
					{ false, false, false, false, false, false },
					{ false, false, true, false, false, false },
					{ false, false, false, true, true, false },
					{ false, false, true, true, false, false },
					{ false, false, false, false, false, false },
					{ false, false, false, false, false, false }};
	private static final boolean[][] GLIDER_UNIVERSE_2 = new boolean[][] {
					{ false, false, false, false, false, false },
					{ false, false, false, true, false, false },
					{ false, false, false, false, true, false },
					{ false, false, true, true, true, false },
					{ false, false, false, false, false, false },
					{ false, false, false, false, false, false }};

	private Universe target;

	@Before
	public void setUp() {
		target = new Universe(GLIDER_UNIVERSE_0);
	}

	@Test
	public void testResetUniverseState() {
		target.resetUniverseState();
		assertArrayEquals(new boolean[6][6], target.recalculateUniverseState());
	}

	@Test
	public void testRecalculateUniverseState() {
		assertArrayEquals(GLIDER_UNIVERSE_1, target.recalculateUniverseState());
		assertArrayEquals(GLIDER_UNIVERSE_2, target.recalculateUniverseState());
	}

}
