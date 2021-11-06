package ru.croc.task7;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            ChessPosition[] a = new ChessPosition[args.length];
            for (int i = 0; i < a.length; i++) {
                a[i] = ChessPosition.parse(args[i]);
            }
            if(ChessPosition.checker(a)){
                System.out.println("OK");
            }
        }
        catch (IllegalPositionException e) {
            System.out.println(e.getMessage());
        }
        catch (IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
    }
}