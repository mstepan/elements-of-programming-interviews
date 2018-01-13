package com.max.epi.graph;

import com.max.util.IntPair;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchMazeTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void findPath() {

        boolean[][] maze = {
                {true, true, true, true, false, true, true, true},
                {true, false, true, true, true, true, false, true},
                {true, false, false, true, false, false, false, true},
                {true, false, true, true, true, true, true, true},
                {true, true, true, false, false, true, false, false},
                {true, true, false, true, true, false, true, true},
                {false, true, false, false, false, false, true, true},
                {true, true, true, true, true, true, true, true}
        };

        List<IntPair> path = SearchMaze.findPath(maze, IntPair.of(7, 0), IntPair.of(0, 7));

        List<IntPair> expectedPath = new ArrayList<>();
        expectedPath.add(IntPair.of(7, 0));
        expectedPath.add(IntPair.of(7, 1));
        expectedPath.add(IntPair.of(6, 1));
        expectedPath.add(IntPair.of(5, 1));
        expectedPath.add(IntPair.of(4, 1));
        expectedPath.add(IntPair.of(4, 2));
        expectedPath.add(IntPair.of(3, 2));
        expectedPath.add(IntPair.of(3, 3));
        expectedPath.add(IntPair.of(3, 4));
        expectedPath.add(IntPair.of(3, 5));
        expectedPath.add(IntPair.of(3, 6));
        expectedPath.add(IntPair.of(3, 7));
        expectedPath.add(IntPair.of(2, 7));
        expectedPath.add(IntPair.of(1, 7));
        expectedPath.add(IntPair.of(0, 7));

        assertEquals("Incorrect path found", expectedPath, path);

    }

    @Test
    public void findPathNoPathBetweenLocations() {
        boolean[][] maze = {
                {true, false, true},
                {true, false, true},
                {true, true, false},
        };

        List<IntPair> noPath = SearchMaze.findPath(maze, IntPair.of(2, 0), IntPair.of(0, 2));

        assertEquals("No path between locations, should return empty 'path'", Collections.emptyList(), noPath);
    }

    @Test
    public void findPathSameLocation() {
        boolean[][] maze = {
                {true, true, true},
                {true, false, true},
                {true, false, false},
        };

        List<IntPair> sameLocationPath = SearchMaze.findPath(maze, IntPair.of(2, 0), IntPair.of(2, 0));

        assertEquals("Same location should return empty path", Collections.emptyList(), sameLocationPath);
    }

    @Test
    public void findPathNullMazeThrowsNPE() {
        expectedException.expect(NullPointerException.class);
        SearchMaze.findPath(null, IntPair.of(0, 0), IntPair.of(1, 1));
    }

    @Test
    public void findPathNullStartThrowsNPE() {
        expectedException.expect(NullPointerException.class);
        SearchMaze.findPath(new boolean[][]{}, null, IntPair.of(1, 1));
    }

    @Test
    public void findPathNullEndThrowsNPE() {
        expectedException.expect(NullPointerException.class);
        SearchMaze.findPath(new boolean[][]{}, IntPair.of(1, 1), null);
    }

    @Test
    public void findPathStartLocationIsNegative() {

        expectedException.expect(IllegalArgumentException.class);

        boolean[][] maze = {
                {true, true, true},
                {true, false, true},
                {true, false, false},
        };

        SearchMaze.findPath(maze, IntPair.of(-2, 0), IntPair.of(2, 0));
    }

    @Test
    public void findPathStartOutOfBound() {

        expectedException.expect(IllegalArgumentException.class);

        boolean[][] maze = {
                {true, true, true},
                {true, false, true},
                {true, false, false},
        };

        SearchMaze.findPath(maze, IntPair.of(0, 333), IntPair.of(2, 0));
    }

    @Test
    public void findPathEndLocationIsNegative() {

        expectedException.expect(IllegalArgumentException.class);

        boolean[][] maze = {
                {true, true, true},
                {true, false, true},
                {true, false, false},
        };

        SearchMaze.findPath(maze, IntPair.of(2, 0), IntPair.of(2, -1));
    }

    @Test
    public void findPathEndOutOfBound() {

        expectedException.expect(IllegalArgumentException.class);

        boolean[][] maze = {
                {true, true, true},
                {true, false, true},
                {true, false, false},
        };

        SearchMaze.findPath(maze, IntPair.of(0, 2), IntPair.of(555, 0));
    }
}
