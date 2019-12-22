package com.max.epi.graph;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UndirectedGraphTest {

    @Test
    public void addEdgesAndCheckAdjacent() {
        UndirectedGraph g = new UndirectedGraph();

        // 1-st connected component
        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("C", "D");
        g.addEdge("B", "D");

        // 2-nd connected component
        g.addEdge("E", "F");
        g.addEdge("E", "G");

        assertThat(g.adjacent("A")).contains("B", "C");
        assertThat(g.adjacent("B")).contains("A", "D");
        assertThat(g.adjacent("C")).contains("A", "D");
        assertThat(g.adjacent("D")).contains("B", "C");

        assertThat(g.adjacent("E")).contains("F", "G");
        assertThat(g.adjacent("F")).contains("E");
        assertThat(g.adjacent("G")).contains("E");
    }
}
