package ru.croc.task4.struct;

public class Circle extends Figure{

    private int x,y,r;

    public Circle(int x,int y,int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public String toString(){
        return "C " + "(" + this.x + "," + this.y + "), " + this.r;
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
    public int get_r() {
        return r;
    }

    public void set_r(int r) {
        this.r = r;
    }
}