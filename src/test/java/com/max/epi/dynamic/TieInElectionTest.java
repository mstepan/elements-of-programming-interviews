package com.max.epi.dynamic;

import org.junit.Test;

import static com.max.epi.dynamic.TieInElection.StateVote;
import static com.max.epi.dynamic.TieInElection.isTiePossible;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TieInElectionTest {

    @Test
    public void tiedExistsSituation() {
        assertTrue(isTiePossible(new StateVote[]{
                new StateVote("Alabama", 9),
                new StateVote("Alaska", 6),
                new StateVote("arizona", 3)

        }));
    }

    @Test
    public void tiedIsNotPossible() {
        assertFalse(isTiePossible(new StateVote[]{
                new StateVote("Alabama", 9),
                new StateVote("Alaska", 5),
                new StateVote("arizona", 2)

        }));
    }
}
