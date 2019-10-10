package com.max.epi.dynamic;

import org.junit.Test;

import static com.max.epi.dynamic.TieInElection.StateVote;
import static com.max.epi.dynamic.TieInElection.isTiePossible;
import static org.assertj.core.api.Assertions.assertThat;

public class TieInElectionTest {

    @Test
    public void tiedExistsSituation() {

        assertThat(isTiePossible(new StateVote[]{
                new StateVote("Alabama", 9),
                new StateVote("Alaska", 6),
                new StateVote("arizona", 3)

        })).isTrue();
    }

    @Test
    public void tiedIsNotPossible() {
        assertThat(isTiePossible(new StateVote[]{
                new StateVote("Alabama", 9),
                new StateVote("Alaska", 5),
                new StateVote("arizona", 2)

        })).isFalse();
    }
}
