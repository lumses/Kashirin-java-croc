package ru.croc.task4;

public class Main {
    public static void main(String[] args) {

        Figure rectangle = new Rectangle(1, 1, 4, 5);
        Figure circle = new Circle(0, 0, 5);
        Annotation[] a = new Annotation[] {new Annotation(circle, "one_annotation"), new Annotation(rectangle, "two_annotation")};
        for (Annotation annotation: a) {
            System.out.println(annotation.toString());
        }

    }
}