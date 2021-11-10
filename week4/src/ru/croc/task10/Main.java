package ru.croc.task10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    
    public static class Tell_log { // Tell_log хранит в себе время и сигнал, обозначающий начало(1) и конец(-1) разговора
        int time;
        int  signal;
    }

    public static void main(String[] args) {
        Path path;
        List<String> lines_log = new ArrayList<>();
        try {
            path = Paths.get(args[0]); // считываем путь/названия лога и содержимое
            lines_log = Files.readAllLines(path);
        }
        catch (IOException e) {
            System.out.printf("Could not open and read the file: %s\n", e.getMessage());
        }
        ArrayList<Tell_log> timeList = new ArrayList<>();
        for(String s : lines_log) { // парсим содержимое лога
            Tell_log a = new Tell_log(); // структура для начала разговора
            a.time = Integer.parseInt(s.split(",")[0]); 
            a.signal = 1;
            timeList.add(a);
            Tell_log b = new Tell_log(); // структура для конца разговора
            b.time = Integer.parseInt(s.split(",")[1]);
            b.signal = -1;
            timeList.add(b);
        }
        // создаем компаратор, чтобы отсортировать по времении ArrayList
        Comparator<Tell_log> comparator = Comparator.comparing(obj -> obj.time); 
        timeList.sort(comparator);
        /*  for(Tell_log i : timeList) {
                System.out.println(i.time + " " + i.signal);
        } */
        int cnt = 0;
        int max = 0;
        for (Tell_log i: timeList) {
            cnt+=i.signal; // складываем сигналы и ищем максимальный пик в моменте
            if (cnt > max) {
                max = cnt;
            }
        }
        System.out.println(max);
    }
}