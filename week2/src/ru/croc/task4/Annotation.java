package ru.croc.task4;

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
}