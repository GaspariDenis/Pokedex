package org.example.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private String url = "jdbc:sqlite:C:\\Users\\12999304\\Desktop\\Pokedex\\src\\main\\resources\\database.db";
    private Connection connection;

    private Database() throws SQLException {
        connection = DriverManager.getConnection(url);
    }

}
