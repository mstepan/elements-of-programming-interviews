package com.max;

public class Main {

    private Main() throws Exception {


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
