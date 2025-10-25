package com.stocksync.practice;

import java.io.*;

public class MultipleResourcesDemo {
    public static void main(String[] args) {
        copyfile();
    }
    public static void copyfile(){
        System.out.println("=== Copying file with multiple resources ===");

        try(
                FileReader reader = new FileReader("new-way.txt");
                BufferedReader bufferedreader = new BufferedReader(reader);
                FileWriter writer = new FileWriter("copy.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(writer)
                ){
            String line;
            while ((line = bufferedreader.readLine()) != null){
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                System.out.println("Copied: " + line);
            }
            System.out.println("✅ File copied successfully");
        } catch (IOException e){
            System.err.println("❌ Error: " + e.getMessage());
        } 
    }
}
