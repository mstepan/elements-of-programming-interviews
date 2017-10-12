package com.max;

import java.util.HashSet;
import java.util.Set;


public class Main {

    private Main() throws Exception {

        Set<Integer> set1 = Set.of(1, 2, 3, 4, 5);
        Set<Integer> set2 = Set.of(10, 20, 3, 5, 60);

        Set<Integer> temp = new HashSet<>(set1);
        temp.retainAll(set2);

        Set<Integer> intersection = Set.of(temp.toArray(new Integer[0]));
        System.out.println(intersection);

        System.out.println("java version: " + System.getProperty("java.version"));
    }

    public static void main(String[] args) {
        try {
            new Main();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
