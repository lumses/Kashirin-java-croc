package ru.croc.task5;

public class Main {
    public static void main(String[] args) {

        Figure rectangle = new Rectangle(1, 1, 4, 5);
        Figure circle = new Circle(0, 0, 5);
        Annotation[] a = new Annotation[] {new Annotation(circle, "Circle"), new Annotation(rectangle, "Rectangle")};
        AnnotatedImage annotatedImage = new AnnotatedImage("", a);

        annotatedImage.findByLabel("Circle").get_figure().move(2,2);
        annotatedImage.findByPoint(1, 1).get_figure().move(1, 1);
        for (Annotation annotation : a) {
            System.out.println(annotation);
        }
    }
}
