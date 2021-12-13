package ru.croc.task18;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private String login;
    private List<Product> product;

    public Order(int id, String login, List<Product> product) {
        this.id = id;
        this.login = login;
        this.product = product;
    }
}