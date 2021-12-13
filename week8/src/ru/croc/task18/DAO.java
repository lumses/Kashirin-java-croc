package ru.croc.task18;


import java.sql.*;
import java.util.List;

public class DAO {
    private Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    Product findProduct(String productCode) throws SQLException {
        String request = "SELECT * FROM " + Product.class.getName() + " WHERE ART_ID = ? ;";
        try (PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1,  productCode);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Product(result.getString("ART_ID"), result.getString("ITEM"), result.getInt("PRICE"));
                }
                else {
                    return null;
                }
            }
        }
    }

    Product createProduct(Product product) throws SQLException {
        if(this.findProduct(product.getArticle()) == null) {
            String request = "INSERT INTO " + Product.class.getSimpleName() +"(ART_ID, ITEM, PRICE) VALUES" + "('" + product.getArticle() + "', '"
                    + product.getName() + "', " + product.getPrice() + ");";
            try (Statement statement = connection.createStatement()){
                statement.execute(request);
            }
        }
        return product;
    }

    Product updateProduct(Product product) throws SQLException {
        if(this.findProduct(product.getArticle()) == null) {
            return createProduct(product);
        }
        String request = "UPDATE " + Product.class.getSimpleName() +" SET ITEM = '" + product.getName() +
                "', PRICE = " + product.getPrice() + " WHERE ART_ID = '" + product.getArticle() + "';";
        try (Statement statement = connection.createStatement()){
            statement.execute(request);
        }
        return product;
    }

    void deleteProduct(String productCode) throws SQLException {
        String request = "UPDATE " + "\"" + "ORDER" + "\"" +
                "SET ARTICLE = " + null +
                " WHERE ARTICLE = '" + productCode + "';";
        try (Statement statement = connection.createStatement()){
            statement.execute(request);
        }
        String req = "DELETE " + Product.class.getSimpleName() + " WHERE ARTICLE = '" + productCode + "';";
        try (Statement statement = connection.createStatement()){
            statement.execute(req);
        }
    }

    Order createOrder(String userLogin, List<Product> products) throws SQLException {
        int count = 0;
        String request = "SELECT MAX(NUMBER)" + "FROM " + "\"" + "BUYS" + "\"";
        try (Statement statement = connection.createStatement()){
            try (ResultSet result = statement.executeQuery(request)) {
                if (result.next()) {
                    count = result.getInt("MAX(NUMBER)") + 1;
                }
                else {
                    count = 1;
                }
            }
        }
        String req = "INSERT INTO " + "\"" + "BUYS" + "\"" +"(ID, LOG, ARTICLE) VALUES" + "(" + count + ", '" + userLogin +"', ?);";
        for (Product product: products) {
            try (PreparedStatement statement = connection.prepareStatement(req)) {
                statement.setString(1, "" + product.getArticle() + "");
                statement.executeUpdate();
            }
        }
        return new Order(count, userLogin, products);
    }

}