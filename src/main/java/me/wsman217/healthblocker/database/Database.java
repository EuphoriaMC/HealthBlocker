package me.wsman217.healthblocker.database;

import me.wsman217.healthblocker.HealthBlocker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    public Database openDatabaseConnection() {
        try {

            connection = DriverManager.getConnection("jdbc:mysql://" + "178.63.127.184" + ":" + "3306" + "/"
                    + "s1142_healthblocker", "u1142_8VojxLVoaY", "SglTHznY1f9LXTHaRGBQPT94");

        } catch (SQLException sQLException) {
            System.out.println("ERROR CONNECTING TO DATABASE!");
            sQLException.printStackTrace();
        }
        return this;
    }

    /*public Database closeConnection() {
        try {
            connection.close();
        } catch (SQLException sQLException) {
            System.out.println("COULD NOT CLOSE CONNECTION!");
            sQLException.printStackTrace();
        }
        return this;
    }*/

    Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                this.openDatabaseConnection();
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return connection;
    }
}