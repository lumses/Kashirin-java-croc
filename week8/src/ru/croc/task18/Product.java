package ru.croc.task18;

public class Product {
    private String article;
    private String name;
    private int price;

    public Product(String article, String name, int price) {
        this.article = article;
        this.name = name;
        this.price = price;
    }
    public String getArticle() {
        return article;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return article + " - " + name + " - " + price;
    }

}
