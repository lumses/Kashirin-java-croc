package ru.croc.task14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class Main {
    public static void main(String[] args) {
        List<String> firstFile = new ArrayList<>();
        List<String> secondFile = new ArrayList<>();
        HashMap<Integer, String> IDAndFilms = new HashMap<>();
        Path path,path1;
        String f1 = "AvailableMovies.txt";
        String f2 = "BrowsingHistory.txt";
        try {
            path = Paths.get(f1); // считываем путь/названия файлов и содержимое
            path1 = Paths.get(f2);
            firstFile = Files.readAllLines(path);
            secondFile = Files.readAllLines(path1);
        }
        catch (IOException e) {
            System.out.printf("Could not open and read the file: %s\n", e.getMessage());
        }
        for(String s : firstFile) { // файл с фильмами парсим в HashMap, где ключ идентификатор фильма, а значение - название фильма 
            int ID = Integer.parseInt(s.split(",")[0]);
            String NameFilm = s.split(",")[1];
            IDAndFilms.put(ID, NameFilm);
        }

        Scanner sc = new Scanner(System.in);  //считываем спискок просмотров конкретного пользователя
        String a = sc.nextLine();
        Set<Integer> input = new HashSet<>(); //эти данные кладем в множество
        String[] t = a.split(",");
        HashMap<Integer, Integer> films = new HashMap<>();
        for (int i = 0;i<t.length;i++){
            input.add(Integer.parseInt(t[i]));
        }

        for (String s: secondFile) { // проходимся построчно по файлу и кладем числа в массив
            int cnt = 0;
            String[] words = s.split(",");
            for (String w : words) { 
               if (input.contains(Integer.parseInt(w))){ // считаем количество совпадений истории конкретного пользователя и истории из файла
                   cnt++;
               }   
            }
            if (cnt*2 >= input.size()) { // проверяем в итоге исполняется первый пункт алгоритма 
                for (String w: words) { //тогда уже кладем в словарь фильмов те фильмы, которые не смотрел конкретный пользователь
                    if (!(input.contains(Integer.parseInt(w)))){ // в словаре ключ - идентификатор фильма, значение - кол-во просмотров
                        if (films.containsKey(Integer.parseInt(w))){
                            films.put(Integer.parseInt(w), films.get(Integer.parseInt(w))+1);
                        } else {
                            films.put(Integer.parseInt(w),1);
                        }
                    }  
                }
            }
        }
        Iterator<Integer> it = films.keySet().iterator();
        if (it.hasNext()) {
        Integer maxk = it.next();
        Integer maxv = films.get(maxk); 
        while(it.hasNext()) { // с помощью итераторов ищем первый фильм с максимальным кол-вом просмотром
            Integer k = it.next();
            Integer val = films.get(k);
            if (val > maxv){
                maxv = val;
                maxk = k;
            }
        }
        System.out.println(IDAndFilms.get(maxk));
        } else System.out.println("No such films");
    }
}


