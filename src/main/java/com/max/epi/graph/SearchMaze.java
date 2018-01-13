package com.max.epi.graph;

import com.max.util.IntPair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 19.1. Search the maze.
 * <p>
 * Given a 2D array of entries finr the path between start and end entries.
 * <p>
 * <p>
 * time: O(N^2)
 * space: O(N^2)
 */
final class SearchMaze {

    private SearchMaze() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }


    static List<IntPair> findPath(boolean[][] maze, IntPair start, IntPair end) {
        checkNotNull(maze, "null 'maze'");
        checkNotNull(start, "null 'start'");
        checkNotNull(end, "null 'end'");

        checkBelongsToMaze(start, maze);
        checkBelongsToMaze(end, maze);

        if (start.equals(end)) {
            return Collections.emptyList();
        }


        Map<IntPair, IntPair> marked = new HashMap<>();
        Queue<IntPair> q = new ArrayDeque<>();

        q.add(start);
        marked.put(start, start);

        while (!q.isEmpty()) {
            IntPair cur = q.poll();

            if (cur.equals(end)) {
                return buildPath(marked, start, end);
            }

            for (IntPair adj : getAdj(cur, maze)) {
                if (!marked.containsKey(adj)) {
                    marked.put(adj, cur);
                    q.add(adj);
                }
            }
        }

        return Collections.emptyList();
    }

    private static final int[][] ADJ_CELLS_OFFSETS = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };

    private static List<IntPair> getAdj(IntPair location, boolean[][] maze) {

        int row = location.getFirst();
        int col = location.getSecond();

        List<IntPair> adj = new ArrayList<>(4);

        for (int[] offset : ADJ_CELLS_OFFSETS) {
            addIfAvailable(row + offset[0], col + offset[1], maze, adj);
        }

        return adj;
    }

    private static void addIfAvailable(int row, int col, boolean[][] maze, List<IntPair> adj) {
        if (row >= 0 && row < maze.length && col >= 0 && col < maze[row].length && maze[row][col])

            adj.add(IntPair.of(row, col));
    }

    private static List<IntPair> buildPath(Map<IntPair, IntPair> parents, IntPair start, IntPair end) {

        List<IntPair> path = new ArrayList<>();

        IntPair cur = end;

        while (!cur.equals(start)) {
            path.add(cur);
            cur = parents.get(cur);
        }
        path.add(start);

        Collections.reverse(path);

        return path;
    }


    private static void checkBelongsToMaze(IntPair location, boolean[][] maze) {

        int row = location.getFirst();
        int col = location.getSecond();

        checkArgument(row >= 0 && row < maze.length && col >= 0 && col < maze[row].length);
    }
}
