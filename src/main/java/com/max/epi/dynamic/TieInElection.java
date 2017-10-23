package com.max.epi.dynamic;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkNotNull;

final class TieInElection {


    static final class StateVote {
        final String state;
        final int votes;

        StateVote(String state, int votes) {
            this.state = state;
            this.votes = votes;
        }

        int getVotes() {
            return votes;
        }

        @Override
        public String toString() {
            return state + ": " + votes;
        }
    }

    /**
     * Check if tier in president election is possible.
     * <p>
     * K - sum of all votes
     * N - states.length
     * <p>
     * time: O(N*K)
     * space: O(N*K)
     */
    static boolean isTiePossible(StateVote[] states) {
        checkNotNull(states);

        if (states.length < 2) {
            return false;
        }

        final int totalVotesCnt = sumOfVotes(states);

        // odd value, tie is not possible
        if ((totalVotesCnt & 1) == 1) {
            return false;
        }

        final int rows = (totalVotesCnt / 2) + 1;
        final int cols = states.length + 1;

        boolean[][] sol = new boolean[rows][cols];
        for (int row = 0; row < rows; ++row) {
            sol[row] = new boolean[cols];
        }

        // 0 row should be all 'true'
        Arrays.fill(sol[0], true);

        for (int row = 1; row < rows; ++row) {
            for (int col = 1; col < cols; ++col) {
                sol[row][col] = sol[row][col - 1];

                final int curVote = states[col - 1].votes;

                if (!sol[row][col] && curVote <= row) {
                    sol[row][col] = sol[row - curVote][col - 1];
                }
            }
        }

        return sol[rows - 1][cols - 1];
    }

    private static int sumOfVotes(StateVote[] states) {
        return Arrays.stream(states).mapToInt(StateVote::getVotes).sum();
    }

}
