package com.max.epi.graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class UndirectedGraph {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private final Map<String, List<String>> edges = new LinkedHashMap<>();

    void addEdge(String src, String dest) {
        addEdgeSafely(src, dest);
        addEdgeSafely(dest, src);
    }

    List<String> adjacent(String vertex) {
        return edges.get(vertex);
    }

    Set<String> allVertixes(){
        return edges.keySet();
    }

    private void addEdgeSafely(String src, String dest) {
        edges.compute(src, (notUsed, adjList) -> adjList == null ? new ArrayList<>() : adjList).add(dest);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();

        for (Map.Entry<String, List<String>> entry : edges.entrySet()) {
            buf.append(entry.getKey()).append(" -> ");

            for (String adj : entry.getValue()) {
                buf.append(adj).append(", ");
            }

            buf.append(LINE_SEPARATOR);

        }

        return buf.toString();
    }
}
