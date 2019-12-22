package com.max.epi.graph;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 19.6 Making wired connections (check if a graph is bipartite).
 */
final class WiredConnections {

    private enum VertexColor {
        BLACK {
            @Override
            VertexColor inverse() {
                return VertexColor.WHITE;
            }
        },
        WHITE {
            @Override
            VertexColor inverse() {
                return VertexColor.BLACK;
            }
        };

        abstract VertexColor inverse();

    }

    /**
     * time: O(V + 2E), visit all vertexes once and all edges twice
     * space: O(V), store all vertexes colors
     *
     * V = vertexes count
     * E = edges count
     */
    static boolean canBeWired(UndirectedGraph graph) {
        checkArgument(graph != null, "null 'graph' parameter passed");

        Map<String, VertexColor> visited = new HashMap<>();

        for (String vertex : graph.allVertixes()) {
            if (!(visited.containsKey(vertex) || isBipartite(vertex, graph, visited))) {
                return false;
            }
        }

        return true;
    }

    private static boolean isBipartite(String start, UndirectedGraph graph, Map<String, VertexColor> visited) {

        visited.put(start, VertexColor.WHITE);

        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {

            String cur = queue.poll();

            VertexColor expectedColor = visited.get(cur).inverse();

            for (String adj : graph.adjacent(cur)) {

                if (visited.containsKey(adj)) {
                    if (visited.get(adj) != expectedColor) {
                        return false;
                    }
                }
                else {
                    visited.put(adj, expectedColor);
                    queue.add(adj);
                }
            }
        }

        return true;
    }

}
