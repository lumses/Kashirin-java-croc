package ru.croc.task8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        long count = 0;
        try(Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNext()) {
                count++;
                scanner.next();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(count);
    }
}
