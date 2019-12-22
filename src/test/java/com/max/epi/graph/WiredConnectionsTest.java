package com.max.epi.graph;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WiredConnectionsTest {

    @Test
    public void canBeWiredBipartiteGraph() {
        UndirectedGraph g = new UndirectedGraph();

        assertThat(WiredConnections.canBeWired(g)).isTrue();

        g.addEdge("A", "D");
        assertThat(WiredConnections.canBeWired(g)).isTrue();

        g.addEdge("A", "B");
        assertThat(WiredConnections.canBeWired(g)).isTrue();

        g.addEdge("A", "C");
        assertThat(WiredConnections.canBeWired(g)).isTrue();

        g.addEdge("C", "E");
        assertThat(WiredConnections.canBeWired(g)).isTrue();

        g.addEdge("B", "E");
        assertThat(WiredConnections.canBeWired(g)).isTrue();
    }

    @Test
    public void canBeWiredBipartiteGraphWithFewConnectedComponents() {
        UndirectedGraph g = new UndirectedGraph();

        assertThat(WiredConnections.canBeWired(g)).isTrue();

        // 1-st bipartite component
        g.addEdge("A", "D");
        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("C", "E");
        g.addEdge("B", "E");

        // 2-nd bipartite component
        g.addEdge("F", "G");
        g.addEdge("F", "K");
        g.addEdge("K", "L");

        assertThat(WiredConnections.canBeWired(g)).isTrue();
    }

    @Test
    public void notBipartiteGraphCantBeWiredOneHop() {
        UndirectedGraph g = new UndirectedGraph();

        g.addEdge("A", "D");
        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("C", "E");
        g.addEdge("B", "E");
        assertThat(WiredConnections.canBeWired(g)).isTrue();

        g.addEdge("D", "C");
        assertThat(WiredConnections.canBeWired(g)).isFalse();
    }

    @Test
    public void notBipartiteGraphCantBeWiredDirect() {
        UndirectedGraph g = new UndirectedGraph();

        g.addEdge("A", "D");
        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("C", "E");
        g.addEdge("B", "E");
        assertThat(WiredConnections.canBeWired(g)).isTrue();

        g.addEdge("B", "C");
        assertThat(WiredConnections.canBeWired(g)).isFalse();
    }

    @Test
    public void cantBeWiredIfAtLeastOneComponentIsNotBipartite() {
        UndirectedGraph g = new UndirectedGraph();

        assertThat(WiredConnections.canBeWired(g)).isTrue();

        // 1-st bipartite component
        g.addEdge("A", "D");
        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("C", "E");
        g.addEdge("B", "E");

        // 2-nd NOT bipartite component
        g.addEdge("F", "G");
        g.addEdge("F", "K");
        g.addEdge("K", "L");
        g.addEdge("K", "G");

        assertThat(WiredConnections.canBeWired(g)).isFalse();
    }

    @Test
    public void cantBeWiredFailedWithNullGraph() {

        UndirectedGraph graph = null;

        assertThatThrownBy(()-> WiredConnections.canBeWired(graph)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessage("null 'graph' parameter passed");
    }
}
