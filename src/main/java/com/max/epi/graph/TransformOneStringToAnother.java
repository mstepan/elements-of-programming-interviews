package com.max.epi.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 19.7. Transform one string to another.
 * <p>
 * Let 's' and 't' be strings and 'D' a dictionary, i.e., a set of strings. Define s to produce t if
 * there exists a sequence of strings from the dictionary P = (So, Si,...,Sn ) such that the
 * first string is 's', the last string is 't', and adjacent strings have the same length and differ
 * in exactly one character. The sequence P is called a production sequence.
 * <p>
 * For example:
 * if the dictionary is {bat, cot,dog,dag,dot, cat), then (cat, cot, dot,dog) is production sequence.
 * <p>
 * V = dic.size()
 * E  = up to V^2
 * time: O(V + E)
 * space: O(V)
 */
final class TransformOneStringToAnother {

    private TransformOneStringToAnother() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    static List<String> productionSequence(String s, String t, Set<String> dic) {
        checkNotNull(s);
        checkNotNull(t);
        checkNotNull(dic);

        if (s.length() != t.length() || !dic.contains(s) || !dic.contains(t)) {
            return Collections.emptyList();
        }

        if (s.equals(t)) {
            return Collections.singletonList(s);
        }

        Map<String, List<String>> graph = buildGraph(dic);

        Queue<Sol> q = new ArrayDeque<>();
        q.add(new Sol(s));

        Set<String> marked = new HashSet<>();
        marked.add(s);

        while (!q.isEmpty()) {
            Sol cur = q.poll();

            if (cur.str.equals(t)) {
                return cur.toReverseList();
            }

            for (String adj : graph.get(cur.str)) {
                if (!marked.contains(adj)) {
                    q.add(new Sol(adj, cur));
                    marked.add(adj);
                }
            }
        }

        return Collections.emptyList();
    }

    private static Map<String, List<String>> buildGraph(Set<String> dic) {
        assert dic != null;

        Map<String, List<String>> res = new LinkedHashMap<>();

        for (String ver : dic) {
            res.put(ver, new ArrayList<>());
        }

        for (String str : res.keySet()) {
            for (String other : res.keySet()) {
                if (!str.equals(other) && hasOneCharDifference(str, other)) {
                    res.get(str).add(other);
                    res.get(other).add(str);
                }
            }
        }

        return res;
    }

    private static boolean hasOneCharDifference(String str, String other) {
        assert str != null && other != null;

        if (str.length() != other.length()) {
            return false;
        }

        int diffsCnt = 0;

        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) != other.charAt(i)) {
                ++diffsCnt;
            }
        }

        return diffsCnt == 1;
    }

    private static final class Sol {
        final String str;
        final Sol next;

        Sol(String str, Sol next) {
            this.str = str;
            this.next = next;
        }

        Sol(String str) {
            this(str, null);
        }

        List<String> toReverseList() {
            List<String> res = new ArrayList<>();

            Sol cur = this;

            while (cur != null) {
                res.add(cur.str);
                cur = cur.next;
            }

            Collections.reverse(res);

            return res;
        }
    }

}
