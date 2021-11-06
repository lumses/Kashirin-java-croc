package ru.croc.task7;

public class ChessPosition {
    private int x;
    private int y;
    
    public ChessPosition() { //констркутор
    }

    public ChessPosition(int x,int y) throws IllegalPositionException{ //конструктор
         if (x < 1 || x > 8 || y < 1 || y > 8) {
            throw new IllegalPositionException(x,y);
        } else {
            this.x = x;
            this.y = y;
        }
    }

    public int get_x() { //геттер
        return x;
    }

    public void set_x(int x) throws IllegalPositionException { //сеттер
        if (x < 1 || x > 8 ) {
            throw new IllegalPositionException(x, y);
        } else {
            this.x = x;
        }
    }

    public int get_y() { //геттер
        return y;
    }

    public void set_y(int x) throws IllegalPositionException { //сеттер
        if (y < 1 || y > 8 ) {
            throw new IllegalPositionException(x, y);
        } else {
            this.y = y;
        }
    }

    public static ChessPosition parse(String pos) throws IllegalPositionException { //"фабричный метод" конструирования объекта позиции из строкового представления ("b2" -> объект)
        return new ChessPosition(pos.charAt(0)-'a',pos.charAt(1)-'0');
    }

    @Override
    public String toString() {
        return Character.toString('a' + this.x) + Integer.toString(this.y);
    }

    public static boolean checker(ChessPosition[] a) throws IllegalMoveException { //проверка последовательности хода коня буквой Г.  
        for (int i = 1; i < a.length; i++) {
            if (!((Math.abs(a[i].x - a[i-1].x) == 2 && Math.abs(a[i].y - a[i-1].y) == 1 ) || (Math.abs(a[i].x - a[i-1].x) == 1 && Math.abs(a[i].y - a[i-1].y) == 2))) {
                throw new IllegalMoveException(a[i - 1], a[i]);
            } 
        }
        return true;
    }
}