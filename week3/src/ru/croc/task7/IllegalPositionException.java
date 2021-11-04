package ru.croc.task7;

public class IllegalPositionException extends Exception {
    private int x;
    private int y;

    public IllegalPositionException(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String getMessage() {
        return "Выход за границы шахматной доски: " + this.x + " " +  this.y;
    }
}