package ru.croc.task10;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class Call {
        private int start, end;
        public Call(int since, int till) {
            this.start = since;
            this.end = till;
        }
        public boolean isBetween(Call call) {
            return ((this.start <= call.end) && (this.end >= call.start));
        }
        public int betweenCount(Call[] calls) {
            int count = 0;
            for (Call call : calls) {
                if(this.isBetween(call)) {
                    count++;
                }
            }
            return count;
        }
    }
class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new File(args[0]))) {
              List<Call> calls = new ArrayList<Call>();
        while (scanner.hasNextLine()) {
            String[] arr = scanner.nextLine().split(",");
            calls.add(new Call(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])));
        }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        int max = 0;
        for (Call call : calls) {
            int count = call.betweenCount(calls.toArray(new Call[0])) - 1;
            max = Math.max(max, count);
        }

        System.out.printf("Пиковое количество звонков: %d", max);
    }
}