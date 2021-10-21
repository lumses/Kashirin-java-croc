package ru.croc.task5;

class Annotation{
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

    public void setstr(String str){
        this.str = str;
    }

    public String getstr(){
        return str;
    }

    public void set_figure(Figure figure){
        this.figure = figure;
    }

    public Figure get_figure(){
        return figure;
    }
}