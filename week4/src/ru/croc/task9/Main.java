package ru.croc.task9;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("The path with logs(if current path, press Enter):");
        String dir = sc.nextLine();
        Path logs = Paths.get("./" + dir);

        LogsFileVisitor logsVisitor = new LogsFileVisitor();
        try {
            Files.walkFileTree(logs, logsVisitor); // обход дерева файлов с сохранением всех логов
        }
        catch (Exception e) {
            System.out.println("Directory" + dir + "is not found");
            return;
        }
    
        int n = logsVisitor.sj.toString().split("\n").length; // кол-во файлов с логами

        BufferedReader[] threads = new BufferedReader[n];
        for (int i = 0; i < n; i++) { 
            threads[i] = new BufferedReader(new FileReader(logsVisitor.sj.toString().split("\n")[i]));
        }
        String[] messageThreads = new String[n];
        Long[] timeThreads = new Long[n]; 
        for (int i = 0; i < n; i++) { 
            try {
                String[] sTempMas = threads[i].readLine().split(" ");
                timeThreads[i] = Long.parseLong(sTempMas[0]);
                messageThreads[i] = String.join(" ", Arrays.copyOfRange(sTempMas, 1, sTempMas.length));
            } catch (Exception e) {
                timeThreads[i] = null;
                messageThreads[i] = null;
            }
        }
        while (true) {
            Long time = null;
            String message = null;
            int pos=0;

            for (int i = 0; i < n; i++) { 
                if (timeThreads[i] == null)
                    continue;
                if (time == null) {
                    time = timeThreads[i];
                    message = messageThreads[i];
                    pos = i;
                    continue;
                }
                if (timeThreads[i] < time) { 
                    time = timeThreads[i];
                    message = messageThreads[i];
                    pos = i;
                }
            }

            if (time == null) 
                break;
            System.out.println(time + " " + message);
            try {
                String[] sTempMas = threads[pos].readLine().split(" ");
                timeThreads[pos] = Long.parseLong(sTempMas[0]);
                messageThreads[pos] = String.join(" ", Arrays.copyOfRange(sTempMas, 1, sTempMas.length));
            } catch (Exception e) {
                timeThreads[pos] = null;
                messageThreads[pos] = null;
            }
        }
    }
}
class LogsFileVisitor extends SimpleFileVisitor<Path> {
    public StringJoiner sj; // для сохранения файлов .log .trace

    public LogsFileVisitor() {
        sj = new StringJoiner("\n");
    }

    @Override //делаем обход, отбирая файлы оканчивающие на log или trace
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (file.getFileName().toString().endsWith(".log") || file.getFileName().toString().endsWith(".trace")) {
            sj.add(file.toString()); 
        }
        return FileVisitResult.CONTINUE; 
    }
}