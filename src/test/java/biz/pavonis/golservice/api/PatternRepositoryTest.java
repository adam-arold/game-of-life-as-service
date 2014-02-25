package biz.pavonis.golservice.api;

import org.junit.Before;
import org.junit.Test;

import biz.pavonis.golservice.api.PatternRepository;

public class PatternRepositoryTest {

    @SuppressWarnings("unused")
    private PatternRepository target;

    @Before
    public void setUp() {
        target = PatternRepository.INSTANCE;
    }

    @Test
    public void patternsLoaded() {

    }

}
