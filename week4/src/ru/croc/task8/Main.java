package ru.croc.task8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

class Main {
    public static void main(String[] args) {
        int count = 0; // счетчик слов
        int k = 0; // нужна для состояний 1-внутри слова, 0 - за пределами
        String s ="./"+args[0];
        BufferedReader reader; 
        try { 
            reader = new BufferedReader(new FileReader(s));
            int c = reader.read();
            while (c != -1) { // если выходим за пределы слова увеличиваем счетчик и делаем состояние за пределом
                if (((char)c  >= 'а' && (char)c <= 'я') || ((char) c >= 'А' && (char) c <='Я')) {
                    k = 1;
                } else {
                    if (k == 1) { 
                        count++;
                        k = 0;
                    }
                }
                c = reader.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(count);
    }
}
