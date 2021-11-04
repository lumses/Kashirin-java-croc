package ru.croc.task4.struct;

public class Rectangle extends Figure{

    private int x,y,x1,y1;

    public Rectangle(int x,int y,int x1,int y1){
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
    }

    @Override
    public String toString(){
        return "R " + "(" + this.x + "," + this.y + "), " + "(" + this.x1 + "," + this.y1 + ")";
    }

    public int get_x() {
        return x;
    }

    public void set_x(int x) {
        this.x = x;
    }

    public int get_y() {
        return y;
    }

    public void set_y(int y) {
        this.y = y;
    }

    public int get_x1() {
        return x1;
    }

    public void set_x1(int x1) {
        this.x1 = x1;
    }

    public int get_y1() {
        return y1;
    }

    public void set_y1(int y1) {
        this.y1 = y1;
    }
    
}