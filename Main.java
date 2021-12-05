package ru.croc.task17;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner sc;
        try {
            sc = new Scanner(Paths.get(args[0]));
        } catch (IOException e) {
            System.out.printf("Не удалось открыть CSV-файл с заказами, ошибка: %s\n", e.getMessage());
            return;
        }
        String connectionUrl = "jdbc:h2:tcp://localhost/~/test";
        Class.forName("org.h2.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl, "sa", ""); Statement stmt = connection.createStatement()) {
            //Создаем таблицы
            String BUYS = "CREATE TABLE IF NOT EXISTS BUYS" +
                    "(ID INT, " +
                    "log VARCHAR(255), " +
                    "article VARCHAR(255));\n";
            String ITEMS = "CREATE TABLE IF NOT EXISTS ITEMS" +
                    "(art_id VARCHAR(255) PRIMARY KEY, " +
                    "item VARCHAR(255), " +
                    "price INTEGER);";
            stmt.execute(BUYS);
            stmt.execute(ITEMS);
            while (sc.hasNextLine()) {
                String a = sc.nextLine();
                String[] data = a.split(",");
                String Update = "INSERT INTO BUYS" + " (ID, log, article) " + "VALUES " + "(" + data[0] + ", '" + data[1] + "', '" + data[2] + "');";
                String Update1 = "INSERT INTO ITEMS" + " (ART_ID, ITEM, PRICE) " + "VALUES " + "('" + data[2] + "', '" + data[3] + "', " + data[4] + ");";
                stmt.execute(Update);
                String SQL = "SELECT * FROM ITEMS "+"WHERE ART_ID = '"+data[2]+ "';";
                boolean isUserExists = false;;
                try (ResultSet result = stmt.executeQuery(SQL)) {
                    if (result.next()) {
                        isUserExists = true;
                    }
                }
                if (isUserExists != true) stmt.execute(Update1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}