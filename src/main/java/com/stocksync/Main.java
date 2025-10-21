package com.stocksync;

public class Main {
    public static void main(String[] args) {
        String os = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");

        String message = """
            ================================
            StockSync Database Project
            ================================
            OS: %s
            Java Version: %s
            Status: Ready to Code!
            ================================
            """.formatted(os, javaVersion);

        System.out.println(message);
    }
}