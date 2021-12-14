package ru.croc.task18;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        String connectionUrl = "jdbc:h2:tcp://localhost/~/test";
        Class.forName("org.h2.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl, "sa", "")) {
            DAO dao = new DAO(connection);
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Введите команду:");
                String[] request = sc.nextLine().split(" ");
                if ((request[0] == "ТОВАР")) {
                        dao.createProduct(new Product(request[1], request[2], Integer.parseInt(request[3])));
                        System.out.println("Команда успешно выполнена!");
                } else if (request[0] == "ИЗМЕНИТЬ") {
                        dao.updateProduct(new Product(request[1], request[2], Integer.parseInt(request[3])));
                        System.out.println("Команда успешно выполнена!");
                } else if (request[0] == "УДАЛИТЬ") {
                        dao.deleteProduct(request[1]);
                        System.out.println("Команда успешно выполнена!");

                } else if (request[0] == "ЗАКАЗ") {
                    List<Product> products = new ArrayList<>();
                    for (int i = 2; i < request.length; ++i) {
                        Product product = dao.findProduct(request[i]);
                        if (product == null) {
                            System.out.println("Нет такого товара для добавления в заказ, article - " + request[i]);
                        }
                        products.add(product);
                    }
                    dao.createOrder(request[1], products);
                } else if (request[0] == "КОНЕЦ") {
                    System.out.println("ОК!");
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
