package ru.croc.task4.struct;

public class Annotation{
    private Figure figure;
    private String str;
    public Annotation(Figure figure,String str){
        this.figure = figure;
        this.str = str;
    }

    @Override
    public String toString(){
        return figure + ": " + str;
    }
    public String getstr(){
        return str;
    }
    public void setstr(String str) {
        this.str = str;
    }
    public Figure getfigure() {
        return figure;
    }
    public void setfigure(Figure figure){
        this.figure = figure;
    }
}