package ru.croc.task19;

import java.io.IOException;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        try (FileWriter writing = new FileWriter("task19.txt", false)) {
            writing.write("Hello, world!");
            writing.flush();
        }
    }
}