package com.stocksync.practice;

import java.io.*;

public class TryWithResourcesDemo {
    public static void main(String[] args) {
        demoOldWay();
        System.out.println("\n" + "=".repeat(60) + "\n");
        demoNewWay();
    }
    public static void demoOldWay(){
        System.out.println("==== OLD WAY (Don't Use This!) =======");

        FileWriter writer = null;
        try {
            writer = new FileWriter("old-way.txt");
            writer.write("This is the Old Way - manual closing \n");
            System.out.println("✅ File written successfully");
        } catch (IOException e){
            System.err.println("❌ Error: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    System.out.println("✅ File closed manually");
                } catch (IOException e) {
                    System.err.println("❌ Error closing file: " + e.getMessage());
                }
            }
        }
    }
    public static void demoNewWay(){
        System.out.println("==== NEW WAY (Always use this!) ====");

        try (FileWriter writer = new FileWriter("new-way.txt")){
            writer.write("This is the new way - automatic closing");
            System.out.println("✅ File written successfully");
        } catch (IOException e){
            System.err.println("❌ Error: " + e.getMessage());
        }
        System.out.println("✅ File closed automatically");
    }
}
